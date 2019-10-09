package com.tsbg.ecosys.controller;

import com.alibaba.fastjson.JSONObject;

import com.tsbg.ecosys.service.LoginService;
import com.tsbg.ecosys.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 *  登录相关Controller
 */
@RestController
@RequestMapping("/login")
public class ShiroLoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 登录
     */
    @PostMapping("/auth")
    public JSONObject authLogin(@RequestBody JSONObject requestJson) {
        CommonUtil.hasAllRequired(requestJson, "userCode,userPwd");
        return loginService.authLogin(requestJson);
    }

    /**
     * 查询当前登录用户的信息
     */
    @PostMapping("/getInfo")
    public JSONObject getInfo() {
        return loginService.getInfo();
    }

    /**
     * 查询当前登录用户的信息
     */
    @RequestMapping(value = "/getMyInfo", method = { RequestMethod.GET, RequestMethod.POST })
    public JSONObject getMyInfo() {
        return loginService.getMyInfo();
    }

    /**
     * 登出
     */
    @PostMapping("/logout")
    public JSONObject logout(HttpServletRequest req) {
        return loginService.logout(req);
    }
}
