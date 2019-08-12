package com.tsbg.ecosys.controller;

import com.tsbg.ecosys.config.ResultResponse;
import com.tsbg.ecosys.model.EuserInfo;
import com.tsbg.ecosys.service.EuserInfoService;
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
    private EuserInfoService euserInfoService;
    /**
     * 查询用户列表
     */
    @RequestMapping(value = "/ecoUser", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResultResponse searchUser(){
        //初始化构造器
        ResultResponse resultResponse = null;
        //查询出用户列表给前端进行渲染
        List<EuserInfo> elist = euserInfoService.selectEuserList();
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
    public ResultResponse setUser(@RequestBody EuserInfo euserInfo){
        //初始化传参构造器
        ResultResponse resultResponse = null;
        //需要从前台获取工号和状态，状态0为启用用户，1为停用用户
        int status = euserInfo.getStatus();
        String userCode = euserInfo.getUserCode();
        //如果状态为0和1以外的值则会提醒输入有效状态值
        if (status!=0 && status!=1){
            resultResponse = new ResultResponse(501,"提示信息：请输入有效状态值");
            return resultResponse;
        }
        //在工号存在的情况下才可成功调用方法
        if (userCode!=null){
            int num = euserInfoService.setEcoUserByUserCode(status,userCode);
            if (num>0 && status == 1){
                resultResponse = new ResultResponse(1,"提示信息：停用成功！");
                return resultResponse;
            }else if(num>0 && status == 0){
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
}
