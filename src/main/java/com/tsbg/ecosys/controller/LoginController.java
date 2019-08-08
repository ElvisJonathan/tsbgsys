package com.tsbg.ecosys.controller;

import com.tsbg.ecosys.config.ResultResponse;
import com.tsbg.ecosys.model.EuserInfo;
import com.tsbg.ecosys.service.EuserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * 登录
 */
@RestController
@RequestMapping("/tsbg/login")
public class LoginController {

    @Autowired
    private EuserInfoService euserInfoService;

    @RequestMapping(value = "/ecologin", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResultResponse login(@RequestBody EuserInfo euserInfo, HttpSession session){
        //初始化构造器
        ResultResponse resultResponse = null;
        //获取用户在前台输入的用户名和密码
        String userCode = euserInfo.getUserCode();
        String userPwd = euserInfo.getUserPwd();
        //如果用户名和密码正确则成功登录
        if (userCode !=null && userPwd !=null){
            //如果用户名和密码存在会返回一条数据
            int num = euserInfoService.selectUserByPwd(userCode,userPwd);
            //存在且只有一条数据意味着登录成功
            if (num==1){
                //成功登录返回用户名给前端
                String userName = euserInfoService.selectUserNameByUserCode(userCode);
                //成功登录返回成功码0并且提示成功
                resultResponse = new ResultResponse(0,"用户名和密码存在成功登录！",userName);
                //设置当前用户的登录session
                session.setAttribute("User",userName);
                return resultResponse;
            }
        }
        //如果用户名或密码为空或是不存在此用户提示登录失败
        //返回错误状态码500和登录失败消息
        resultResponse = new ResultResponse(500,"用户名或密码错误登录失败！");
        return resultResponse;
    }
}
