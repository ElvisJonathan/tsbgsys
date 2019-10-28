package com.tsbg.mis.powerService;

import com.alibaba.fastjson.JSONObject;
import com.tsbg.mis.ecoService.base.RedisService;
import com.tsbg.mis.powerMapper.LoginMapper2;
import com.tsbg.mis.powerMapper.UserRoleMapper2;
import com.tsbg.mis.powerModel.UserInfo;
import com.tsbg.mis.powerModel.powerBag.RoleAndProJPackage;
import com.tsbg.mis.powerVo.LoginVo;
import com.tsbg.mis.util.ResultUtils;
import com.tsbg.mis.util.constants.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 *  登录service实现类
 */
@Service
public class LoginServiceImpl2 implements LoginService2 {

	@Autowired
	private LoginMapper2 loginMapper;
	@Autowired
	private UserRoleMapper2 userRoleMapper2;
	@Autowired
	private PermService2 permService;
	@Autowired
	private UserInfoService2 userInfoService;
	@Autowired
	private RedisService redisService;
	@Autowired
	private TokenService2 tokenService;
	@Autowired
	private TokenBlacklistService2 tokenBlacklistService;
	@Value("${server.servlet.session.timeout}")
	private long sessionExpire;
	@Autowired
	private RoleAndProJPackageService2 roleAndProJPackageService;
	@Autowired
	private UserRoleService2 userRoleService;

	@Override
	public ResultUtils authLogin(JSONObject jsonObject) {
		String userCode = jsonObject.getString("userCode");
		String userPwd = jsonObject.getString("userPwd");
		//根据用户工号查询用户名
		String userName = userInfoService.selectUserNameByUserCode(userCode);
		//根据UserCode查询对应的userId
		Integer userId = userInfoService.selectuidbyuserCode(userCode);
		if (userId==null){
			return new ResultUtils(500,"未查询到该用户");
		}
		UserInfo userForBase = new UserInfo();
		userForBase.setUserId(userId);
		userForBase.setUserPwd(userPwd);
		userForBase.setUserCode(userCode);
		//添加其他所需业务逻辑
		if (userCode!=null){
			//查询工号是否存在
			Integer count = userInfoService.selectCountByUserCode(userCode);
			if (count==0){
				return new ResultUtils(501,"该工号未注册或不存在");
			}
			Integer status = userInfoService.selectStatusByUserCode(userCode);
			if (status==1){
				return new ResultUtils(502,"用户被管理员停用！");
			}
		}
		//登录时需要进行密码的判断：如果密码为工号+"123"的形式则提示用户修改密码
		if (userPwd!=null){
			if (userPwd.equals(userCode+"123")){
				return new ResultUtils(503,"用户密码被管理员重置需要修改密码！");
			}
		}
		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(userCode, userPwd);
		try {
			currentUser.login(token);
			//获取token
			String token2 = tokenService.getToken(userForBase);
			//将密码存到redis
			redisService.setCacheObject("pwd"+userId,userPwd,3600);
			//适配统一登录 将角色和项目信息返给前台
			//List<RoleAndProJPackage> roleAndProJPackages = roleAndProJPackageService.selectRoleAndProj();
			//当前用户的项目编号返给前端
			List<RoleAndProJPackage> roleAndProJPackages = userRoleService.selectProJMsgByUid(userId);
			///return new ResultUtils(100,"成功登录",userCode,token2,userRoles);
			return new ResultUtils(100,"成功登录",new LoginVo(userCode,userName,token2,roleAndProJPackages));
		} catch (AuthenticationException e) {
			return new ResultUtils(504,"用户名或密码错误登录失败");
		}
	}

	@Override
	public JSONObject getMyUser(String userCode, String userPwd) {
		return loginMapper.getMyUser(userCode,userPwd);
	}

	@Override
	public ResultUtils getMyInfo() {
		JSONObject userInfo2 = redisService.getCacheObject(Constants.SESSION_USER_INFO);
		String userCode2 = userInfo2.getString("userCode");
		JSONObject userPermission2 = permService.getMyUserPermission2(userCode2);//此处暂时改成个人的权限
		redisService.setCacheObject(Constants.SESSION_USER_PERMISSION, userPermission2);
		//此处多加一步 将String转为array返回
		String aStr = userPermission2.getString("permissionCode");
		if (aStr!=null){
			String newStr = aStr.substring(1,aStr.length()-1);
			String Str = newStr.replaceAll(", ",",").trim();
			String[]arr = Str.split(",");
			userPermission2.put("permissionList",arr);
			return new ResultUtils(100,"成功",userPermission2);
		}
		return new ResultUtils(500,"该用户权限尚待分配");
	}

	@Override
	public ResultUtils getMyInfo2(Integer projId) {
		JSONObject userInfo2 = redisService.getCacheObject(Constants.SESSION_USER_INFO);
		String userCode2 = userInfo2.getString("userCode");
		//事先判断是否需要因为项目id加角色
		//可能原因是user_role表中信息不全
		int uid = userInfoService.selectuidbyuserCode(userCode2);
		System.out.println("user_id:"+uid);
		List<RoleAndProJPackage> roleAndProJPackages = roleAndProJPackageService.selectRoleAndProj2();
		//根据uid/roleid/projId查找是否已插入，如果user_role表中不存在则插入
		//并且需要判断当前角色的项目编号只有一个
		List<Integer> projList = userRoleMapper2.selectProJIdByUserId(uid);
		System.out.println("项目编号列表："+projList);
		for (int i=0;i<roleAndProJPackages.size();i++){
			int i1 = userRoleMapper2.selectRoleCountByCondition(uid, roleAndProJPackages.get(i).getRoleid(), roleAndProJPackages.get(i).getProjId());
			System.out.println("是否存在:"+i1);
			if (i1==0 && !projList.contains(roleAndProJPackages.get(i).getProjId())){
				userRoleService.insertData(uid,roleAndProJPackages.get(i).getRoleid(),userCode2,new Date(),roleAndProJPackages.get(i).getProjId());
			}
		}
		JSONObject userPermission2 = permService.getMyUserPermission(userCode2,projId);
		if (userPermission2!=null){
			System.out.println("权限："+userPermission2.toJSONString());
			redisService.setCacheObject(Constants.SESSION_USER_PERMISSION, userPermission2);
			return new ResultUtils(100,"成功",userPermission2);
		}
		return new ResultUtils(201,"未能查到该系统权限");
	}

	@Override
	public ResultUtils logout(HttpServletRequest req) {
		try {
			Subject currentUser = SecurityUtils.getSubject();
			String token = req.getHeader("token");
			//在token黑名单中更改token状态
			tokenBlacklistService.updateStatusByTokenCode(token);
			currentUser.logout();
			return new ResultUtils(100,"注销成功");
		} catch (Exception e) {
			return new ResultUtils(500,"注销异常");
		}
	}
}
