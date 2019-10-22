package com.tsbg.ecosys.ecoController;

import com.tsbg.ecosys.annotation.UserLoginToken;
import com.tsbg.ecosys.util.ResultUtils;
import com.tsbg.ecosys.ecoModel.UserInfo;
import com.tsbg.ecosys.ecoService.UserInfoService;
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
    public ResultUtils searchUser(){
        //初始化构造器
        ResultUtils resultUtils = null;
        //查询出用户列表给前端进行渲染
        List<UserInfo> elist = userInfoService.selectEuserList();
        if (elist != null){
            //如果查询出的用户列表不为空则返回给前端进行数据渲染
            resultUtils = new ResultUtils(0,"提示信息：成功返回用户列表信息",elist);
            return resultUtils;
        }
        //若未查询到用户信息则返回提示
        resultUtils = new ResultUtils(501,"提示信息：未查询到用户列表信息");
        return resultUtils;
    }

    /**
     * 停用/启用用户
     */
    @RequestMapping(value = "/setecoUser", method = { RequestMethod.GET, RequestMethod.POST })
    @UserLoginToken
    @ResponseBody
    public ResultUtils setUser(@RequestBody UserInfo userInfo){
        //初始化传参构造器
        ResultUtils resultUtils = null;
        //需要从前台获取工号和状态，状态0为启用用户，1为停用用户
        int status = userInfo.getStatus();
        String userCode = userInfo.getUserCode();
        //如果状态为0和1以外的值则会提醒输入有效状态值
        if (status!=0 && status!=1){
            resultUtils = new ResultUtils(501,"提示信息：请输入有效状态值");
            return resultUtils;
        }
        //在工号存在的情况下才可成功调用方法
        if (userCode!=null){
            int num = userInfoService.setEcoUserByUserCode(status,userCode);
            if (num>0 && status == 1){
                resultUtils = new ResultUtils(1,"提示信息：停用成功！");
                return resultUtils;
            }else if(num>0){
                resultUtils = new ResultUtils(0,"提示信息：启用成功！");
                return resultUtils;
            }else {
                resultUtils = new ResultUtils(501,"提示信息：启用或停用失败！");
                return resultUtils;
            }
        }
        resultUtils = new ResultUtils(502,"提示信息：未获取到工号！");
        return resultUtils;
    }
    /**
     * 查询个人信息
     */
    @RequestMapping(value = "/searchUserMsg", method = { RequestMethod.GET, RequestMethod.POST })
    @UserLoginToken
    @ResponseBody
    public ResultUtils lookUser(@RequestBody UserInfo userInfo) {
        ResultUtils resultUtils = null;
        //通过前端传来的用户工号查询用户信息
        String userCode = userInfo.getUserCode();
        if (userCode!=null){
           UserInfo info = userInfoService.selectUserMsgbyUserCode(userCode);
           if (info!=null){
               resultUtils = new ResultUtils(0,"提示信息：成功查询到用户个人信息",info);
               return resultUtils;
           }
            resultUtils = new ResultUtils(501,"提示信息：未查询到用户个人信息");
            return resultUtils;
        }
        resultUtils = new ResultUtils(502,"提示信息：未收到工号信息");
        return resultUtils;
    }

    /**
     * 修改个人信息
     */
    @RequestMapping(value = "/modifyUserMsg", method = { RequestMethod.GET, RequestMethod.POST })
    @UserLoginToken
    @ResponseBody
    public ResultUtils modifyUser(@RequestBody UserInfo userInfo){
        ResultUtils resultUtils = null;
        //根据前端传来的工号修改用户信息
        if (userInfo.getUserCode()!=null){
           int num = userInfoService.updateByUserCodeSelective(userInfo);
           //通过工号查询当前用户的用户名
           String userName = userInfoService.selectUserNameByUserCode(userInfo.getUserCode());
           if (num>0 && userName!=null){
               resultUtils = new ResultUtils(0,"提示信息：修改用户信息成功！",userName);
               return resultUtils;
           }else if (num>0){
               resultUtils = new ResultUtils(0,"提示信息：修改用户信息成功但用户名不存在！");
               return resultUtils;
           }
            resultUtils = new ResultUtils(501,"提示信息：修改用户信息失败！");
            return resultUtils;
        }
        resultUtils = new ResultUtils(502,"提示信息：未获取到工号！");
        return resultUtils;
    }

    /**
     * 管理员重置用户密码
     */
    @RequestMapping(value = "/reSetUserPwd", method = { RequestMethod.GET, RequestMethod.POST })
    @UserLoginToken
    @ResponseBody
    public ResultUtils reSetUserPwd(@RequestBody UserInfo userInfo){
        //重置用户的密码为：工号+"123",用户需要在被重置密码后进行密码的修改
        //通过接受前端传来的工号进行修改
        String userCode = userInfo.getUserCode();
        if (userCode!=null){
            String userPwd = userCode+"123";
            int num = userInfoService.reSetPwdByUserCode(userPwd,userCode);
            if(num>0){
                return new ResultUtils(0,"重置密码成功！");
            }
            return new ResultUtils(501,"重置密码失败！");
        }
        return new ResultUtils(503,"未收到工号！");
    }
}
