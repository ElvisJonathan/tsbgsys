package com.tsbg.ecosys.mapper;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;

/**
 * 登录相关dao
 */
public interface LoginMapper {
	/**
	 * 根据用户名和密码查询对应的用户
	 */
	JSONObject getUser(@Param("username") String username, @Param("password") String password);

	/**
	 * 根据工号和密码查询对应的用户
	 */
	JSONObject getMyUser(@Param("userCode") String userCode, @Param("userPwd") String userPwd);
}
