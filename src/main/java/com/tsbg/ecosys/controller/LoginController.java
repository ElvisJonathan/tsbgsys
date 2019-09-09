package com.tsbg.ecosys.controller;

import com.tsbg.ecosys.config.ResultResponse;
import com.tsbg.ecosys.model.Permission;
import com.tsbg.ecosys.model.Role;
import com.tsbg.ecosys.model.UserInfo;
import com.tsbg.ecosys.service.PermissionService;
import com.tsbg.ecosys.service.RoleService;
import com.tsbg.ecosys.service.UserInfoService;
import com.tsbg.ecosys.vo.LoginResultVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 登录
 */
@RestController
@RequestMapping("/tsbg/login")
public class LoginController {

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    @PostMapping("loginTest")
    public UserInfo loginTest(@RequestBody UserInfo userInfo,HttpSession session){
        HttpServletRequest request = null;
        //UserInfo userInfo = new UserInfo();
        //userInfo.setUserName(userName);
        session.setAttribute("session_user",userInfo);
        //获取用户的信息
        userInfo = (UserInfo) request.getSession().getAttribute("session_user");
        //输出当前的userInfo看下
        System.out.println(userInfo);
        return userInfo;
    }


    @RequestMapping(value = "/ecologin", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResultResponse login(@RequestBody UserInfo userInfo, HttpSession session, HttpServletRequest request){
        //初始化构造器
        ResultResponse resultResponse = null;
        //获取用户在前台输入的用户名和密码
        String userCode = userInfo.getUserCode();
        String userPwd = userInfo.getUserPwd();
        //如果用户被停用则阻止其登录
        if (userCode!=null){
            //查询工号是否存在
            Integer count = userInfoService.selectCountByUserCode(userCode);
            if (count==0){
                resultResponse = new ResultResponse(501,"该工号未注册或不存在！");
                return resultResponse;
            }
            Integer status = userInfoService.selectStatusByUserCode(userCode);
            if (status==1){
                resultResponse = new ResultResponse(502,"用户被管理员停用！");
                return resultResponse;
            }
        }
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
            int num = userInfoService.selectUserByPwd(userCode,userPwd);
            //存在且只有一条数据意味着登录成功
            if (num==1){
                //成功登录返回用户名给前端
                String userName = userInfoService.selectUserNameByUserCode(userCode);
                //成功登录返回成功码0并且提示成功
                //返回权限列表
                List<Role> roleList = roleService.findRoleByUserCode(userCode);
                if (roleList.size()!=0){
                    int arr[] = new int[1];//当前用户为单角色只给一个长度
                    for (int i=0;i<=arr.length-1;i++){
                        arr[i]=roleList.get(i).getRoleid();
                    }
                    List<Permission> plist = permissionService.findPermissionByRoleId(arr[0]);
                    if (plist!=null){
                        String arr2[] = new String[plist.size()];
                        for (int i=0;i<=plist.size()-1;i++){
                            arr2[i]=plist.get(i).getName();
                        }
                        //设置当前用户的登录session
                        userInfo.setUserName(userName);
                        //session.setAttribute("userName",userName);
                        //session.setAttribute("userCode",userCode);
                        //session.setMaxInactiveInterval(1000);
                        //session.setAttribute("session_user",userInfo);
                        //request.getSession().setAttribute("users",userName);//用户名存入该用户的session 中
                        return new ResultResponse(0,"成功登录并且获取了权限！",userName,userCode,arr2);
                    }
                    //session.setAttribute("userName",userName);
                    //session.setAttribute("userCode",userCode);
                    return new ResultResponse(0,"成功登录但未获取到对应权限信息！",userName,userCode);
                }
                //设置当前用户的登录session
                //session.setAttribute("userName",userName);
                //session.setAttribute("userCode",userCode);
                return new ResultResponse(0,"成功登录但是未找到对应角色信息！",userName,userCode);
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
    public ResultResponse preModifyPwd(@RequestBody UserInfo userInfo){
        //通过从前端接收的工号和密码来判断是否存在此用户
        String userCode = userInfo.getUserCode();
        String userPwd = userInfo.getUserPwd();
        if(userCode!=null && userPwd!=null){
            //调用查询逻辑查找用户是否存在
            int num = userInfoService.judgeIfExistUserByUserPwd(userCode,userPwd);
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
    public ResultResponse modifyPassword(@RequestBody UserInfo userInfo) {
        //初始化构造器
        ResultResponse resultResponse = null;
        //获取输入的新密码和工号
        String userCode = userInfo.getUserCode();
        String userPwd = userInfo.getUserPwd();
        if (userCode != null && userPwd != null) {
            //修改成功将uid传给页面
            int num = userInfoService.modifyPasswordByUsername(userPwd, userCode);
            if (num>0){
                UserInfo ei = userInfoService.selectByUserCode(userCode);
                resultResponse = new ResultResponse(0, "修改成功！", new LoginResultVo(ei.getUserName(),ei.getUserCode(),ei.getUserPwd(), ei.getUserId()));
                return  resultResponse;
            }
            resultResponse = new ResultResponse(501,"修改失败！");
            return resultResponse;
        }
        resultResponse = new ResultResponse(500,"工号或密码为空");
        return resultResponse;
    }

    /**
     * 登出
     */
    @RequestMapping(value = "/ecologout", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResultResponse Logout(HttpSession session){
        session.invalidate();
        return new ResultResponse(0,"用户成功登出！");
    }
}
