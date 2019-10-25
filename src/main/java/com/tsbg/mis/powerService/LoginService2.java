package com.tsbg.mis.powerService;

import com.alibaba.fastjson.JSONObject;
import com.tsbg.mis.util.ResultUtils;

import javax.servlet.http.HttpServletRequest;

/**
 *  登录Service
 */
public interface LoginService2 {
	/**
	 * 登录表单提交
	 */
	ResultUtils authLogin(JSONObject jsonObject);

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
	ResultUtils getMyInfo();

	/**
	 * 根据项目编号来返回权限信息
	 */
	ResultUtils getMyInfo2(Integer projId);

	/**
	 * 退出登录
	 */
	ResultUtils logout(HttpServletRequest req);
}
