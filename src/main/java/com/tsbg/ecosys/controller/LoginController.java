package com.tsbg.ecosys.controller;

import com.tsbg.ecosys.config.ResultResponse;
import com.tsbg.ecosys.model.EuserInfo;
import com.tsbg.ecosys.service.EuserInfoService;
import com.tsbg.ecosys.vo.LoginResultVo;
import io.swagger.annotations.ApiOperation;
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
        //登录时需要进行密码的判断：如果密码为工号+"123"的形式则提示用户修改密码
        if (userPwd!=null){
            if (userPwd.equals(userCode+"123")){
                resultResponse = new ResultResponse(503,"用户密码被管理员重置需要修改密码！");
                return resultResponse;
            }
        }
        //如果用户名和密码正确则成功登录
        if (userCode !=null && userPwd !=null){
            //如果用户名和密码存在会返回一条数据
            int num = euserInfoService.selectUserByPwd(userCode,userPwd);
            //存在且只有一条数据意味着登录成功
            if (num==1){
                //成功登录返回用户名给前端
                String userName = euserInfoService.selectUserNameByUserCode(userCode);
                //成功登录返回成功码0并且提示成功
                resultResponse = new ResultResponse(0,"用户名和密码存在成功登录！",userName,userCode);
                //设置当前用户的登录session
                session.setAttribute("userName",userName);
                session.setAttribute("userCode",userCode);
                return resultResponse;
            }
        }
        //如果用户名或密码为空或是不存在此用户提示登录失败
        //返回错误状态码500和登录失败消息
        resultResponse = new ResultResponse(500,"用户名或密码错误登录失败！");
        return resultResponse;
    }

    /**
     *判断原密码是否正确
     */
    @RequestMapping(value = "/premodifyPwd", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResultResponse preModifyPwd(@RequestBody EuserInfo euserInfo){
        //通过从前端接收的工号和密码来判断是否存在此用户
        String userCode = euserInfo.getUserCode();
        String userPwd = euserInfo.getUserPwd();
        if(userCode!=null && userPwd!=null){
            //调用查询逻辑查找用户是否存在
            int num = euserInfoService.judgeIfExistUserByUserPwd(userCode,userPwd);
            if (num==1){
                return new ResultResponse(0,"此用户存在且可以修改密码");
            }
            return new ResultResponse(501,"用户不存在或密码错误请重试");
        }
        return new ResultResponse(502,"工号或密码为空异常");
    }


    /**
     * 修改密码
     */
    @ApiOperation(value = "修改密码", notes = "修改密码")
    @RequestMapping(value = "/modifyPassword", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResultResponse modifyPassword(@RequestBody EuserInfo euserInfo) {
        //初始化构造器
        ResultResponse resultResponse = null;
        //获取输入的新密码和工号
        String userCode = euserInfo.getUserCode();
        String userPwd = euserInfo.getUserPwd();
        if (userCode != null && userPwd != null) {
            //修改成功将uid传给页面
            int num = euserInfoService.modifyPasswordByUsername(userPwd, userCode);
            if (num>0){
                EuserInfo ei = euserInfoService.selectByUserCode(userCode);
                resultResponse = new ResultResponse(0, "修改成功！", new LoginResultVo(ei.getUserName(),ei.getUserCode(),ei.getUserPwd(), ei.getUid()));
                return  resultResponse;
            }
            resultResponse = new ResultResponse(501,"修改失败！");
            return resultResponse;
        }
        resultResponse = new ResultResponse(500,"工号或密码为空");
        return resultResponse;
    }
}
