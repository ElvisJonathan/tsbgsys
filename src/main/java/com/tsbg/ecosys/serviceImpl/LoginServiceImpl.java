package com.tsbg.ecosys.serviceImpl;

import com.alibaba.fastjson.JSONObject;

import com.tsbg.ecosys.mapper.LoginMapper;
import com.tsbg.ecosys.model.UserInfo;
import com.tsbg.ecosys.model.UserRole;
import com.tsbg.ecosys.model.bag.RoleAndProJPackage;
import com.tsbg.ecosys.service.*;
import com.tsbg.ecosys.service.base.RedisService;
import com.tsbg.ecosys.service.base.TokenService;
import com.tsbg.ecosys.util.CommonUtil;
import com.tsbg.ecosys.util.constants.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *  登录service实现类
 */
@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginMapper loginMapper;
	@Autowired
	private PermService permService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private RedisService redisService;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private TokenBlacklistService tokenBlacklistService;
	@Value("${server.servlet.session.timeout}")
	private long sessionExpire;
	@Autowired
	private RoleAndProJPackageService roleAndProJPackageService;
	@Autowired
	private UserRoleService userRoleService;

	/**
	 * 登录表单提交
	 */
	@Override
	public JSONObject authLogin(JSONObject jsonObject) {
		String userCode = jsonObject.getString("userCode");
		String userPwd = jsonObject.getString("userPwd");
		JSONObject info = new JSONObject();
		//根据UserCode查询对应的userId
		Integer userId = userInfoService.selectuidbyuserCode(userCode);
		if (userId==null){
			jsonObject.put("result","未查询到该用户");
			return jsonObject;
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
				info.put("result", "该工号未注册或不存在");
				return CommonUtil.failJson(info);
			}
			Integer status = userInfoService.selectStatusByUserCode(userCode);
			if (status==1){
				info.put("result", "用户被管理员停用！");
				return CommonUtil.failJson(info);
			}
		}
		//登录时需要进行密码的判断：如果密码为工号+"123"的形式则提示用户修改密码
		if (userPwd!=null){
			if (userPwd.equals(userCode+"123")){
				info.put("result", "用户密码被管理员重置需要修改密码！");
				return CommonUtil.failJson(info);
			}
		}
		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(userCode, userPwd);
		try {
			currentUser.login(token);
			//获取token
			/*String token2 = String.valueOf(SnowflakeIdWorker.getSnowflakeId());
			//注册token到redis设置超时时间为5分钟
			redisService.setCacheObject(token2,"1", sessionExpire);*/
			String token2 = tokenService.getToken(userForBase);
			//将密码存到redis
			redisService.setCacheObject("pwd"+userId,userPwd,3600);
			//适配统一登录 将角色和项目信息返给前台
			//List<RoleAndProJPackage> roleAndProJPackages = roleAndProJPackageService.selectRoleAndProj();
			//当前用户的项目编号返给前端
			List<UserRole> userRoles = userRoleService.selectProJMsgByUid(userId);
			info.put("token",token2);
			info.put("userCode",userCode);
			info.put("result", "成功登录");
			info.put("RoleAndProject",userRoles);
		} catch (AuthenticationException e) {
			info.put("result", "用户名或密码错误登录失败");
		}
		return CommonUtil.successJson(info);
	}

	/**
	 * 根据用户名和密码查询对应的用户
	 */
	@Override
	public JSONObject getUser(String username, String password) {
		return loginMapper.getUser(username, password);
	}

	/**
	 * 根据工号和密码查询对应的用户
	 */
	@Override
	public JSONObject getMyUser(String userCode, String userPwd) {
		return loginMapper.getMyUser(userCode,userPwd);
	}

	/**
	 * 查询当前登录用户的权限等信息
	 */
	@Override
	public JSONObject getInfo() {
		//从session获取用户信息
		Session session = SecurityUtils.getSubject().getSession();
		JSONObject userInfo = (JSONObject) session.getAttribute(Constants.SESSION_USER_INFO);
		String username = userInfo.getString("username");
		JSONObject info = new JSONObject();
		JSONObject userPermission = permService.getUserPermission(username);
		session.setAttribute(Constants.SESSION_USER_PERMISSION, userPermission);
		info.put("userPermission", userPermission);
		return CommonUtil.successJson(info);
	}

	@Override
	public JSONObject getMyInfo() {
		//从session获取用户信息
		//Session session = SecurityUtils.getSubject().getSession();
		//JSONObject userInfo = (JSONObject) session.getAttribute(Constants.SESSION_USER_INFO);//跨域此处为空
		JSONObject userInfo2 = redisService.getCacheObject(Constants.SESSION_USER_INFO);
		//String userCode = userInfo.getString("userCode");
		String userCode2 = userInfo2.getString("userCode");
		JSONObject info = new JSONObject();
		//JSONObject userPermission = permissionService.getMyUserPermission2(userCode);//此处暂时改成个人的权限
		JSONObject userPermission2 = permService.getMyUserPermission2(userCode2);//此处暂时改成个人的权限
		//session.setAttribute(Constants.SESSION_USER_PERMISSION, userPermission);//先将查询结果注入Session
		redisService.setCacheObject(Constants.SESSION_USER_PERMISSION, userPermission2);
		//此处多加一步 将String转为array返回
		String aStr = userPermission2.getString("permissionCode");
		if (aStr!=null){
			String newStr = aStr.substring(1,aStr.length()-1);
			String Str = newStr.replaceAll(", ",",").trim();
			String[]arr = Str.split(",");
			userPermission2.put("permissionList",arr);
			info.put("userPermission", userPermission2);
			return CommonUtil.successJson(info);
		}
		info.put("result","该用户权限尚待分配");
		return CommonUtil.failJson(info);
	}

	@Override
	public JSONObject getMyInfo2(Integer projId) {
		JSONObject userInfo2 = redisService.getCacheObject(Constants.SESSION_USER_INFO);
		String userCode2 = userInfo2.getString("userCode");
		JSONObject info = new JSONObject();
		JSONObject userPermission2 = permService.getMyUserPermission(userCode2,projId);
		redisService.setCacheObject(Constants.SESSION_USER_PERMISSION, userPermission2);
		info.put("userPermission",userPermission2);
		return CommonUtil.successJson(info);
	}

	/**
	 * 退出登录
	 */
	@Override
	public JSONObject logout(HttpServletRequest req) {
		try {
			Subject currentUser = SecurityUtils.getSubject();
			String token = req.getHeader("token");
			System.out.println("token:"+token);
			//在token黑名单中更改token状态
			tokenBlacklistService.updateStatusByTokenCode(token);
			currentUser.logout();
			JSONObject info = new JSONObject();
			info.put("message","注销成功");
			return CommonUtil.successJson(info);
		} catch (Exception e) {
			return CommonUtil.failJson("注销失败");
		}
	}
}
