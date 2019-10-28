package com.tsbg.mis.powerController;

import com.tsbg.mis.annotation.PassToken;
import com.tsbg.mis.annotation.UserLoginToken;
import com.tsbg.mis.common.SnowflakeIdWorker;
import com.tsbg.mis.ecoService.base.RedisService;
import com.tsbg.mis.ecoVo.LoginResultVo;
import com.tsbg.mis.ecoVo.LoginResultVone;
import com.tsbg.mis.powerModel.UserInfo;
import com.tsbg.mis.powerService.UserInfoService2;
import com.tsbg.mis.util.MD5Util2;
import com.tsbg.mis.util.ResultUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 登录
 */
@RestController
@RequestMapping("/tsbg/login")
public class LoginController {
    @Autowired
    private RedisService redisService;
    @Value("${server.servlet.session.timeout}")
    private long sessionExpire;
    @Autowired
    private UserInfoService2 userInfoService;
    /*@Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;*/

    @PostMapping("loginTest")
    public UserInfo loginTest(@RequestBody UserInfo userInfo, HttpSession session){
        HttpServletRequest request = null;
        session.setAttribute("session_user",userInfo);
        //获取用户的信息
        userInfo = (UserInfo) request.getSession().getAttribute("session_user");
        //输出当前的userInfo看下
        System.out.println(userInfo);
        return userInfo;
    }
    //单点登录
    @ApiOperation(value = "用户登录", notes = "用户登录")
    @PostMapping("/loginsso")
    public ResultUtils login(
            @ApiParam(name = "userInfo", value = "userInfo", required = true)
            @RequestBody UserInfo userInfo, HttpSession session) {
        //初始化构造器
        ResultUtils resultUtils = null;
        //获取用户在前台输入的用户名和密码
        String userCode = userInfo.getUserCode();
        String userPwd = userInfo.getUserPwd();
        //如果用户名和密码正确则成功登录
        if (userCode != null && userPwd != null) {
            //如果用户名和密码存在会返回一条数据
            int num = userInfoService.selectUserByPwd(userCode, userPwd);
            //存在且只有一条数据意味着登录成功
            if (num == 1) {
                //获取token
                String token = String.valueOf(SnowflakeIdWorker.getSnowflakeId());
                //注册token到redis设置超时时间为5分钟
                redisService.setCacheObject(token,"1", sessionExpire);
                //成功登录返回用户名给前端
                String userName = userInfoService.selectUserNameByUserCode(userCode);
                //成功登录返回成功码0并且提示成功
                resultUtils = new ResultUtils(0, "用户名和密码存在成功登录！", new LoginResultVone(userName, token));
                //设置当前用户的登录session
                session.setAttribute("User", userName);
                return resultUtils;
            }
        }
        //如果用户名或密码为空或是不存在此用户提示登录失败
        //返回错误状态码500和登录失败消息
        resultUtils = new ResultUtils(500, "用户名或密码错误登录失败！");
        return resultUtils;
    }

    @RequestMapping(value = "/ecologin", method = { RequestMethod.GET, RequestMethod.POST })
    @PassToken
    @ResponseBody
    public ResultUtils login(@RequestBody UserInfo userInfo){
        //初始化构造器
        ResultUtils resultUtils = null;
        //获取用户在前台输入的用户名和密码
        String userCode = userInfo.getUserCode();
        String userPwd = userInfo.getUserPwd();
        //根据用户工号查询对应密码盐
        String salt = userInfoService.selectSaltByUserCode(userCode);
        String newPwd = MD5Util2.encode(userPwd+salt);
        //如果用户被停用则阻止其登录
        if (userCode!=null){
            //查询工号是否存在
            Integer count = userInfoService.selectCountByUserCode(userCode);
            if (count==0){
                resultUtils = new ResultUtils(501,"该工号未注册或不存在！");
                return resultUtils;
            }
            Integer status = userInfoService.selectStatusByUserCode(userCode);
            if (status==1){
                resultUtils = new ResultUtils(502,"用户被管理员停用！");
                return resultUtils;
            }
        }
        //登录时需要进行密码的判断：如果密码为工号+"123"的形式则提示用户修改密码
        if (userPwd!=null){
            if (userPwd.equals(userCode+"123")){
                resultUtils = new ResultUtils(503,"用户密码被管理员重置需要修改密码！");
                return resultUtils;
            }
            //暫時對郵件重置的密碼不做强制修改密碼的提示
//            byte initLocked=userInfoService.selectLockedByUserCode(userCode);
//            if (initLocked==2){
//                resultUtils = new ResultUtils(503,"用户密码被管理员重置需要修改密码！");
//                return resultUtils;
//            }
        }
        //如果用户名和密码正确则成功登录
        if (userCode !=null && userPwd !=null){
            //如果用户名和密码存在会返回一条数据
            int num = userInfoService.selectUserByPwd(userCode,newPwd);
            //适配之前的明文登录方式
            int num2 = userInfoService.selectUserByPwd(userCode,userPwd);
            //存在且只有一条数据意味着登录成功
            if (num==1 || num2==1){
                //成功登录返回用户名给前端
                String userName = userInfoService.selectUserNameByUserCode(userCode);
                //获取token
                String token = String.valueOf(SnowflakeIdWorker.getSnowflakeId());
                //注册token到redis设置超时时间为5分钟
                redisService.setCacheObject(token,"1", sessionExpire);
                //成功登录返回成功码0并且提示成功
                //返回权限列表
                String power = userInfoService.selectPowerByUserCode(userCode);
                if (power.contains(";")){
                    System.out.println("多权限");//有待于完善
                    String []newPower2 = power.split(";");
                    String []newList = new String[newPower2.length];
                    String []real = new String[newList.length];
                    for (int i=0;i<=newPower2.length-1;i++){
                        newList[i] = newPower2[i].substring(1,newPower2[i].length()-1);
                    }
                    for (int i=0;i<=newList.length-1;i++){
                        real[i] = newList[i].replaceAll(", ",",").trim();
                    }
                    return new ResultUtils(0,"成功登录并且获取了权限！",new LoginResultVone(userName, token),userCode,real);
                }
                String newPower = power.substring(1,power.length()-1);
                String s = newPower.replaceAll(", ",",").trim();
                String[]arr = s.split(",");
                return new ResultUtils(0,"成功登录并且获取了权限！",new LoginResultVone(userName, token),userCode,arr);
            }
        }
        //如果用户名或密码为空或是不存在此用户提示登录失败
        //返回错误状态码500和登录失败消息
        resultUtils = new ResultUtils(500,"用户名或密码错误登录失败！");
        return resultUtils;
    }

    /**
     *判断原密码是否正确
     */
    @RequestMapping(value = "/premodifyPwd", method = {RequestMethod.GET, RequestMethod.POST})
    @UserLoginToken
    @ResponseBody
    public ResultUtils preModifyPwd(@RequestBody UserInfo userInfo){
        //通过从前端接收的工号和密码来判断是否存在此用户
        String userCode = userInfo.getUserCode();
        String userPwd = userInfo.getUserPwd();
        //根据用户工号查询对应密码盐
        String salt = userInfoService.selectSaltByUserCode(userCode);
        String newPwd = MD5Util2.encode(userPwd+salt);
        if(userCode!=null && userPwd!=null){
            //调用查询逻辑查找用户是否存在
            int num = userInfoService.judgeIfExistUserByUserPwd(userCode,userPwd);
            int num2 = userInfoService.judgeIfExistUserByUserPwd(userCode,newPwd);
            if (num==1 || num2==1){
                return new ResultUtils(100,"此用户存在且可以修改密码");
            }
            return new ResultUtils(501,"用户不存在或密码错误请重试");
        }
        return new ResultUtils(502,"工号或密码为空异常");
    }

    /**
     * 修改密码
     */
    @ApiOperation(value = "修改密码", notes = "修改密码")
    @RequestMapping(value = "/modifyPassword", method = {RequestMethod.GET, RequestMethod.POST})
    @UserLoginToken
    @ResponseBody
    public ResultUtils modifyPassword(@RequestBody UserInfo userInfo) {
        //初始化构造器
        ResultUtils resultUtils = null;
        //获取输入的新密码和工号
        String userCode = userInfo.getUserCode();
        String userPwd = userInfo.getUserPwd();
        //重新生成salt
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<str.length();i++){
            int nu=random.nextInt(6);
            sb.append(str.charAt(nu));
        }
        userInfoService.resetUserSalt(sb.toString(),userCode);
        //加密密码
        String newPwd = MD5Util2.encode(userPwd+sb.toString());
        if (userCode != null && userPwd != null) {
            //修改成功将uid传给页面
            int num = userInfoService.modifyPasswordByUsername(newPwd, userCode);
            if (num>0){
                UserInfo ei = userInfoService.selectByUserCode(userCode);
                resultUtils = new ResultUtils(100, "修改成功！", new LoginResultVo(ei.getUserName(),ei.getUserCode(),userPwd, ei.getUserId()));
                return resultUtils;
            }
            resultUtils = new ResultUtils(501,"修改失败！");
            return resultUtils;
        }
        resultUtils = new ResultUtils(500,"工号或密码为空");
        return resultUtils;
    }

    /**
     * 登录成功后校验密码规范
     */
    @RequestMapping(value = "/checkpwd", method = {RequestMethod.GET, RequestMethod.POST})
    @UserLoginToken
    @ResponseBody
    public ResultUtils check(@RequestBody UserInfo userInfo){
        String pwd = userInfo.getUserPwd();
        Pattern pattern = Pattern.compile("[a-zA-Z]*");
        Matcher isNum = pattern.matcher(pwd);
        Pattern patternSe = Pattern.compile("[0-9]*");
        Matcher isNum2 = patternSe.matcher(pwd);
        //判斷密碼是否符合規範
        if (isNum.matches()){
            return new ResultUtils(500,"不符合密碼規範（不能全是字母）");
        }
        if (isNum2.matches()){
            return new ResultUtils(501,"不符合密碼規範（不能全是數字）");
        }
        //boolean isNum = pwd.matches("[0-9]+");
        //boolean isWord = pwd.matches("[a-zA-Z]+");
        if (pwd.length()<8 || pwd.length()>20){
            return new ResultUtils(503,"不符合密碼規範（8-20位）");
        }
        return new ResultUtils(0,"密碼符合規範無需提示");
    }
}
