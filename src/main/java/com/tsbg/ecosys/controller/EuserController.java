package com.tsbg.ecosys.controller;

import com.tsbg.ecosys.config.ResultResponse;
import com.tsbg.ecosys.model.UserInfo;
import com.tsbg.ecosys.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户相关
 */
@RestController
@RequestMapping("/tsbg/euser")
public class EuserController {

    @Autowired
    private UserInfoService userInfoService;
    /**
     * 查询用户列表
     */
    @RequestMapping(value = "/ecoUser", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResultResponse searchUser(){
        //初始化构造器
        ResultResponse resultResponse = null;
        //查询出用户列表给前端进行渲染
        List<UserInfo> elist = userInfoService.selectEuserList();
        if (elist != null){
            //如果查询出的用户列表不为空则返回给前端进行数据渲染
            resultResponse = new ResultResponse(0,"提示信息：成功返回用户列表信息",elist);
            return resultResponse;
        }
        //若未查询到用户信息则返回提示
        resultResponse = new ResultResponse(501,"提示信息：未查询到用户列表信息");
        return resultResponse;
    }

    /**
     * 停用/启用用户
     */
    @RequestMapping(value = "/setecoUser", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResultResponse setUser(@RequestBody UserInfo userInfo){
        //初始化传参构造器
        ResultResponse resultResponse = null;
        //需要从前台获取工号和状态，状态0为启用用户，1为停用用户
        int status = userInfo.getStatus();
        String userCode = userInfo.getUserCode();
        //如果状态为0和1以外的值则会提醒输入有效状态值
        if (status!=0 && status!=1){
            resultResponse = new ResultResponse(501,"提示信息：请输入有效状态值");
            return resultResponse;
        }
        //在工号存在的情况下才可成功调用方法
        if (userCode!=null){
            int num = userInfoService.setEcoUserByUserCode(status,userCode);
            if (num>0 && status == 1){
                resultResponse = new ResultResponse(1,"提示信息：停用成功！");
                return resultResponse;
            }else if(num>0){
                resultResponse = new ResultResponse(0,"提示信息：启用成功！");
                return resultResponse;
            }else {
                resultResponse = new ResultResponse(501,"提示信息：启用或停用失败！");
                return resultResponse;
            }
        }
        resultResponse = new ResultResponse(502,"提示信息：未获取到工号！");
        return resultResponse;
    }


    /**
     * 查询个人信息
     */
    @RequestMapping(value = "/searchUserMsg", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResultResponse  lookUser(@RequestBody UserInfo userInfo) {
        ResultResponse resultResponse = null;
        //通过前端传来的用户工号查询用户信息
        String userCode = userInfo.getUserCode();
        if (userCode!=null){
           UserInfo info = userInfoService.selectUserMsgbyUserCode(userCode);
           if (info!=null){
               resultResponse = new ResultResponse(0,"提示信息：成功查询到用户个人信息",info);
               return resultResponse;
           }
            resultResponse = new ResultResponse(501,"提示信息：未查询到用户个人信息");
            return resultResponse;
        }
        resultResponse = new ResultResponse(502,"提示信息：未收到工号信息");
        return resultResponse;
    }

    /**
     * 修改个人信息
     */
    @RequestMapping(value = "/modifyUserMsg", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResultResponse modifyUser(@RequestBody UserInfo userInfo){
        ResultResponse resultResponse = null;
        //根据前端传来的工号修改用户信息
        if (userInfo.getUserCode()!=null){
           int num = userInfoService.updateByUserCodeSelective(userInfo);
           if (num>0){
               resultResponse = new ResultResponse(0,"提示信息：修改用户信息成功！");
               return resultResponse;
           }
            resultResponse = new ResultResponse(501,"提示信息：修改用户信息失败！");
            return resultResponse;
        }
        resultResponse = new ResultResponse(502,"提示信息：未获取到工号！");
        return resultResponse;
    }

    /**
     * 管理员重置用户密码
     */
    @RequestMapping(value = "/reSetUserPwd", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResultResponse reSetUserPwd(@RequestBody UserInfo userInfo){
        //重置用户的密码为：工号+"123",用户需要在被重置密码后进行密码的修改
        //通过接受前端传来的工号进行修改
        String userCode = userInfo.getUserCode();
        if (userCode!=null){
            String userPwd = userCode+"123";
            int num = userInfoService.reSetPwdByUserCode(userPwd,userCode);
            if(num>0){
                return new ResultResponse(0,"重置密码成功！");
            }
            return new ResultResponse(501,"重置密码失败！");
        }
        return new ResultResponse(503,"未收到工号！");
    }
}
