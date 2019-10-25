package com.tsbg.mis.powerMapper;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface LoginMapper2 {

    /**
     * 根据工号和密码查询对应的用户
     */
    JSONObject getMyUser(@Param("userCode") String userCode, @Param("userPwd") String userPwd);
}
