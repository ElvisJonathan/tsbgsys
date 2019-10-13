package com.tsbg.ecosys.service;

import com.alibaba.fastjson.JSONObject;

/**
 * 权限相关service
 */
public interface PermService {
	/**
	 * 查询某用户的 角色  菜单列表   权限列表
	 */
	JSONObject getUserPermission(String username);

	/**
	 * 查询某角色  菜单列表   权限列表
	 */
	JSONObject getMyUserPermission(String userCode);

	/**
	 * 查询用户个人的角色 菜单 权限
	 */
	JSONObject getMyUserPermission2(String userCode);
}
