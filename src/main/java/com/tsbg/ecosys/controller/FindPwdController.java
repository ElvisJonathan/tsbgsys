package com.tsbg.ecosys.controller;

import com.alibaba.fastjson.JSONObject;
import com.tsbg.ecosys.model.UserInfo;
import com.tsbg.ecosys.service.JwtUsedOnceService;
import com.tsbg.ecosys.service.UserInfoService;
import com.tsbg.ecosys.util.JWTUtils;
import com.tsbg.ecosys.util.SendMailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class FindPwdController {
    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private JwtUsedOnceService jwtUsedOnceService;


    @RequestMapping(value = "/test", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String testcode(){
        return "success";
    }


    @RequestMapping("/findPwdPage")
    public String FindPwdPage(){
        return "<div align='center'></br></br></br></br></br></br></br></br></br></br></br></br><table><form action='/verifyAndgetToken' method='post'><tr><td><input name='user_code' id='user_code' type='text' /></td></tr><tr><td><input name='email_address' id='email_address' type='text'/> </td></tr><tr><td><input type='submit' value='确定'/></td></tr></table></form></div>";
    }

    @RequestMapping(value="/verifyAndgetToken",method= RequestMethod.POST)
    @ResponseBody
    public String VerifyAndgetToken(HttpServletRequest request) throws Exception {

        String email="";
        JSONObject Message=new JSONObject();
        String user_code=request.getParameter("user_code");
        String email_address=request.getParameter("email_address");
        if(!user_code.isEmpty() && !email_address.isEmpty()){//提交的Form表單數據user_code、email_address均不爲空
            email=userInfoService.selectEmailByUserCode(user_code);
            System.out.println(email);
            if(email.equals(email_address)){        //工號與郵箱一致，生成Token
                String username=userInfoService.selectUserNameByUserCode(user_code);
                UserInfo u=new UserInfo();
                u.setUserCode(user_code);
                u.setUserName(username);
                u.setEmailAddress(email_address);
                Message.put("工號",user_code);
                Message.put("姓名",username);
                Message.put("郵箱",email_address);
                //String token = JWTUtils.sign(jsonInfo, 30L * 24L * 3600L * 1000L);//一個月
                String token = JWTUtils.sign(u, 1L * 1L * 1L * 60000L);//60秒的時間
                if (token != null) {
                    Message.put("Toekn",token);
                    String subject="系統密碼修改";
                    String url="http://localhost:8080/testToken?token="+token;
                    String mailContent="<p>Dear "+username+",您好:      </p><p>点此链接通过验证前往修改密码！Url:"+url+"</p>";

                    JSONObject jsonObject=SendMailUtils.send(username, email_address, subject, mailContent);
                    if(jsonObject.get("status").equals("success")){
                        return "<script> alert('鏈接已發送至您的SuperNotes郵箱，請及時修改密碼！');</script>";
                    }else{
                        return "<script> alert('郵件發送失敗，請稍後重試！');</script>";
                    }
                }else{
                    Message.put("Token","Token生成失敗，請稍後重試！");
                    return "<script> alert('驗證失敗，請稍後重試！');</script>";
                }
            }else{
                return "<script> alert('工號對應的郵箱不一致，請重新輸入！');window.location.href='http://localhost:8080/findPwdPage';</script>";
            }
        }else{
            return "<script> alert('工號或郵箱爲空，請重新填寫！');</script>";
        }
    }








    @RequestMapping("/get_info")
    @ResponseBody
    //public String getInfo(@RequestParam String token) {
    public String getInfo() {
        if (true) {
            return "getToken";
        }
        return "工號或郵箱錯誤！";
    }

    @RequestMapping(value="/testToken",method= RequestMethod.GET)
    @ResponseBody
    public String getInfo(HttpServletRequest request) {
//        JSONObject jsonInfo=new JSONObject();
//        String EpNo="F1336602";
//        String Mail="abc850085812@163.com";
//        jsonInfo.put("EpNo",EpNo);
//        jsonInfo.put("mail",Mail);
//        UserInfo u=new UserInfo();
//        u.setCreater("F1336602");
//        u.setEmailAddress("abc850085812@163.com");

        String token=request.getParameter("token");
        if(jwtUsedOnceService.selectByUsedOnce(token)<1){
            JSONObject jsonInfo=new JSONObject();
            UserInfo u= JWTUtils.unsign(token, UserInfo.class);
            if (u != null) {
                String UserCode=u.getUserCode();
                String EmailAddress=u.getEmailAddress();
                jsonInfo.put("UserCode",UserCode);
                jsonInfo.put("EmailAddress",EmailAddress);
                jwtUsedOnceService.insert(token);
                return "<div align='center'></br></br></br></br></br></br></br></br></br></br></br></br><table><form action='/modifyPwd' method='post'><tr><td><input type='hidden' id='user_code' name='user_code' value='"+UserCode+"' /></td></tr><tr><td><input type='password' id='user_pwd' name='user_pwd'/></td></tr><tr><td><input type='password' id='repwd' name='repwd'/></td></tr><tr><td><input type='submit' value='提交' /></td></tr></table></form><div>";
            }else{
                jsonInfo.put("UserCode","Token已經過期");
                jsonInfo.put("EmailAddress","Token已經過期");
                //return jsonInfo;

                return "<script> alert('會話已經過期，請重新申請！');window.location.href='http://localhost:8080/findPwdPage';</script>";
            }
        }else{
            return "<script> alert('該鏈接在其有效期内已經被使用過一次，已經失效！請重新申請！');window.location.href='http://localhost:8080/findPwdPage';</script>";
        }


    }



    @RequestMapping(value = "/modifyPwd",method = RequestMethod.POST)
    @ResponseBody
    public String ModifyPwd(HttpServletRequest request) throws ParseException {
        String user_code= request.getParameter("user_code");
        String user_pwd= request.getParameter("user_pwd");
        UserInfo u=new UserInfo();
        u.setUserCode(user_code);
        u.setUserPwd(user_pwd);

        Date time =new Date();
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" );
        String updatetime = sdf.format(time);

        u.setUpdateTime(sdf.parse(updatetime));
        if(userInfoService.updateByUserCodeSelective(u)>0) {

            return "<script>alert('修改密碼成功！請前往登錄頁面登錄！');window.history.pushState('forward',null,'#');window.location.href='http://localhost:8080/findPwdPage';</script>";
        }else{
            return "<script> alert('修改密碼失敗，請稍後重試！');</script>";

        }
    }
}
