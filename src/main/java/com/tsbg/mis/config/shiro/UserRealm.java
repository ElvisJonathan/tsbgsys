package com.tsbg.mis.config.shiro;

import com.alibaba.fastjson.JSONObject;

import com.tsbg.mis.ecoService.LoginService;
import com.tsbg.mis.ecoService.UserInfoService;
import com.tsbg.mis.ecoService.base.RedisService;
import com.tsbg.mis.powerService.LoginService2;
import com.tsbg.mis.powerService.UserInfoService2;
import com.tsbg.mis.util.MD5Util2;
import com.tsbg.mis.util.constants.Constants;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义Realm
 */
public class UserRealm extends AuthorizingRealm {
	private Logger logger = LoggerFactory.getLogger(UserRealm.class);

	@Autowired
	/*private LoginService loginService;*/
	private LoginService2 loginService;//loginService2是挪出来之后用的登录
	@Autowired
	/*private UserInfoService userInfoService;*/
	private UserInfoService2 userInfoService;//UserInfoService2是挪出来之后用的登录
	@Autowired
	private RedisService redisService;

	@Override
	@SuppressWarnings("unchecked")
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//Session session = SecurityUtils.getSubject().getSession();
		//Object cacheObject = redisService.getCacheObject(Constants.SESSION_USER_PERMISSION);
		//查询用户的权限
		//JSONObject permission = (JSONObject) session.getAttribute(Constants.SESSION_USER_PERMISSION);
		JSONObject permission = redisService.getCacheObject(Constants.SESSION_USER_PERMISSION);
		logger.info("permission的值为:" + permission);
		logger.info("本用户权限为:" + permission.get("permissionCode"));//原先是遍历前的permissionList
		//此处需要将string转为list
		String aStr = permission.get("permissionCode").toString();
		String newStr = aStr.substring(1,aStr.length()-1);
		String Str = newStr.replaceAll(", ",",").trim();
		String[]arr = Str.split(",");
		List<String> aList = new ArrayList<>();
		for (int i=0;i<arr.length;i++){
				aList.add(arr[i]);
			}
		//为当前用户设置角色和权限
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.addStringPermissions(aList);
		return authorizationInfo;
	}

	/**
	 * 验证当前登录的Subject
	 * LoginController.login()方法中执行Subject.login()时 执行此方法
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		String loginName = (String) authcToken.getPrincipal();
		// 获取用户密码
		String password = new String((char[]) authcToken.getCredentials());
		//根据用户工号查询对应密码盐
		String salt = userInfoService.selectSaltByUserCode(loginName);
		String newPwd = MD5Util2.encode(password+salt);
		JSONObject user = loginService.getMyUser(loginName, newPwd);
		if (user == null) {
			//没找到帐号
			throw new UnknownAccountException();
		}
		//交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				user.getString("userCode"),
				password,
				//ByteSource.Util.bytes("salt"), salt=username+salt,采用明文访问时，不需要此句
				getName()
		);
		//session中不需要保存密码
		user.remove("userPwd");
		//将用户信息放入session中
		//SecurityUtils.getSubject().getSession().setAttribute(Constants.SESSION_USER_INFO, user);
		redisService.setCacheObject(Constants.SESSION_USER_INFO,user,3600);
		return authenticationInfo;
	}
}
