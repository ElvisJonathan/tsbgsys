package com.tsbg.ecosys.controller;

import com.tsbg.ecosys.config.ResultResponse;
import com.tsbg.ecosys.model.UserInfo;
import com.tsbg.ecosys.service.UserInfoService;
import com.tsbg.ecosys.service.StaffInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 注册
 */
@RestController
@RequestMapping("/tsbg/register")
public class RegisterController {

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private StaffInfoService staffInfoService;

    /**
     *生态员工注册成为用户
     */
    @RequestMapping(value = "/ecosign", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResultResponse register(@RequestBody UserInfo userInfo) {
        //初始化构造器
        ResultResponse resultResponse = null;
        //根据用户输入的工号去查询员工表是否存在该工号，存在并且为生态部门员工，则可注册，注册成功可以在用户表插入数据
        String userCode = userInfo.getUserCode();
        //通过工号查询员工表是否存在该工号 存在返回1 可以进行下一步注册
        int number = staffInfoService.selectisExistStaffCodeByuserCode(userCode);
        //通过工号查询员工表对应的部门代码编号，必须是生态部门编号才可进行下一步注册
        String deptCode = staffInfoService.selectDepartCodeByuserCode(userCode);
        //创建之前去用户表判断该用户是否已经注册，如果已经注册过提示错误信息不能重复注册
        int count = userInfoService.selectisExistUserCodeByStaffCode(userCode);
        if (count>0){
            resultResponse = new ResultResponse(502,"提示信息：已经注册，不可重复注册！");
            return resultResponse;
        }
        //如果存在该工号并且部门代码为生态部门代码则成功创建该用户
        if (number>0 && (deptCode.trim().equals("GE11000600") ||  deptCode.trim().equals("GE110006000") || deptCode.trim().equals("GE110006100") || deptCode.trim().equals("GE1100S6000")
        || deptCode.trim().equals("GE1100S6100") || deptCode.trim().equals("GE1100S6200") || deptCode.trim().equals("GE110006110")
        || deptCode.trim().equals("GE110006111") || deptCode.trim().equals("GE110006112") || deptCode.trim().equals("GE1100T6000")
        || deptCode.trim().equals("GE1100T6100") || deptCode.trim().equals("GE1100T6200") || deptCode.trim().equals("GE1100T6300")
        || deptCode.trim().equals("GE110006200") || deptCode.trim().equals("GE110006210") || deptCode.trim().equals("GE110006211")
        || deptCode.trim().equals("GE1100H6000") || deptCode.trim().equals("GE110006213") || deptCode.trim().equals("GE110006220")
        || deptCode.trim().equals("GE110006221") || deptCode.trim().equals("GE110006222") || deptCode.trim().equals("GE11200800")
        || deptCode.trim().equals("BCDCJ81000"))){
            //可以注册
            //设置创建人和创建时间
            userInfo.setCreater(userInfo.getUserName());
            userInfo.setCreateTime(new Date());
            //调用业务逻辑存储注册信息
            int num = userInfoService.insertSelective(userInfo);
            //成功注册则会增加一条记录
            if (num>0){
                //同步存储对应信息至euser_area和user_role
                //将user_info表的user_id查询出来
                int uid = userInfoService.selectuidbyuserCode(userCode);
                String name = userInfo.getUserName()+"是生态员工";
                //将此user_id增加至euser_area
                //userInfoService.insertDatatoEuserArea(uid,name);
                //将此uid和rid增加至user_role
                userInfoService.insertDatatoEuserRole(uid,2,name);
                //返回成功码0并且提示成功注册
                resultResponse = new ResultResponse(0,"提示信息：成功注册！");
                return resultResponse;
            }
        }
        //不是生态员工返回错误码
        resultResponse = new ResultResponse(501,"提示信息：用户不是生态员工不能注册！");
        return resultResponse;
    }
}
