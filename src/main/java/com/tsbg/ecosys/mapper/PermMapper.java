package com.tsbg.ecosys.mapper;

import com.alibaba.fastjson.JSONObject;

import java.util.Set;

/**
 * 获取权限相关dao
 */
public interface PermMapper {
	/**
	 * 查询用户的角色 菜单 权限
	 */
	JSONObject getUserPermission(String username);
	/**
	 * 查询用户的角色 菜单 权限
	 */
	JSONObject getMyUserPermission(String userCode,Integer projId);
	/**
	 * 查询用户个人的角色 菜单 权限
	 */
	JSONObject getMyUserPermission2(String userCode);

	/**
	 * 查询所有的菜单
	 */
	Set<String> getAllMenu();

	/**
	 * 查询所有的权限
	 */
	Set<String> getAllPermission();
}
