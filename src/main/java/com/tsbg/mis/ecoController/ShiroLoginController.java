package com.tsbg.mis.ecoController;

import com.alibaba.fastjson.JSONObject;

import com.tsbg.mis.annotation.PassToken;
import com.tsbg.mis.annotation.UserLoginToken;
import com.tsbg.mis.ecoModel.UserRole;
import com.tsbg.mis.ecoService.LoginService;
import com.tsbg.mis.util.CommonUtil;
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
    @PassToken
    @ResponseBody
    public JSONObject authLogin(@RequestBody JSONObject requestJson) {
        CommonUtil.hasAllRequired(requestJson, "userCode,userPwd");
        return loginService.authLogin(requestJson);
    }

    /**
     * 查询当前登录用户的信息
     */
    @PostMapping("/getInfo")
    @UserLoginToken
    public JSONObject getInfo() {
        return loginService.getInfo();
    }

    /**
     * 查询当前登录用户的信息
     * 生态系统调用该方法返回
     */
    @RequestMapping(value = "/getMyInfo", method = { RequestMethod.GET, RequestMethod.POST })
    @UserLoginToken
    @ResponseBody
    public JSONObject getMyInfo() {
        return loginService.getMyInfo();
    }

    /**
     * 查询当前登录用户的信息
     * 其他系统调用该方法返回
     */
    @RequestMapping(value = "/getMyInfo2", method = { RequestMethod.GET, RequestMethod.POST })
    @UserLoginToken
    @ResponseBody
    public JSONObject getMyInfo2(@RequestBody UserRole userRole) {
        return loginService.getMyInfo2(userRole.getProjId());
    }

    /**
     * 登出
     */
    @PostMapping("/logout")
    @UserLoginToken
    public JSONObject logout(HttpServletRequest req) {
        return loginService.logout(req);
    }
}
