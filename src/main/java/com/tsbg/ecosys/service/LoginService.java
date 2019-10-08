package com.tsbg.ecosys.service;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;

/**
 *  登录Service
 */
public interface LoginService {
	/**
	 * 登录表单提交
	 */
	JSONObject authLogin(JSONObject jsonObject);

	/**
	 * 根据用户名和密码查询对应的用户
	 *
	 * @param username 用户名
	 * @param password 密码
	 */
	JSONObject getUser(String username, String password);

	/**
	 * 根据工号和密码查询对应的用户
	 *
	 * @param userCode 工号
	 * @param userPwd 密码
	 */
	JSONObject getMyUser(String userCode, String userPwd);

	/**
	 * 查询当前登录用户的权限等信息
	 */
	JSONObject getInfo();

	/**
	 * 查询当前登录用户的权限等信息
	 */
	JSONObject getMyInfo();

	/**
	 * 退出登录
	 */
	JSONObject logout(HttpServletRequest req);
}
