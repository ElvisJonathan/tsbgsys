package com.tsbg.mis.powerController;

import com.alibaba.fastjson.JSONObject;
import com.tsbg.mis.annotation.PassToken;
import com.tsbg.mis.annotation.UserLoginToken;
import com.tsbg.mis.powerModel.UserRole;
import com.tsbg.mis.powerService.LoginService2;
import com.tsbg.mis.util.CommonUtil;
import com.tsbg.mis.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 *  登录相关Controller
 */
@RestController
@RequestMapping("/login")
public class ShiroLoginController2 {

    @Autowired
    private LoginService2 loginService;

    /**
     * 登录
     */
    @PostMapping("/auth2")
    @PassToken
    @ResponseBody
    public ResultUtils authLogin(@RequestBody JSONObject requestJson) {
        CommonUtil.hasAllRequired(requestJson, "userCode,userPwd");
        return loginService.authLogin(requestJson);
    }

    /**
     * 查询当前登录用户的信息
     * 生态系统调用该方法返回
     */
   /* @RequestMapping(value = "/newgetMyInfo", method = { RequestMethod.GET, RequestMethod.POST })
    @UserLoginToken
    @ResponseBody
    public ResultUtils getMyInfo() {
        return loginService.getMyInfo();
    }*/

    /**
     * 查询当前登录用户的信息
     * 其他系统调用该方法返回
     */
    @RequestMapping(value = "/newgetMyInfo2", method = { RequestMethod.GET, RequestMethod.POST })
    @UserLoginToken
    @ResponseBody
    public ResultUtils getMyInfo2(@RequestBody UserRole userRole) {
        return loginService.getMyInfo2(userRole.getProjId());
    }

    /**
     * 登出
     */
    @PostMapping("/newlogout")
    @UserLoginToken
    public ResultUtils logout(HttpServletRequest req) {
        return loginService.logout(req);
    }
}
