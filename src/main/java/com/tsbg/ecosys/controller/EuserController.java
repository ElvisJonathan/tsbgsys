package com.tsbg.ecosys.controller;

import com.tsbg.ecosys.config.ResultResponse;
import com.tsbg.ecosys.model.EuserInfo;
import com.tsbg.ecosys.service.EuserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
}
