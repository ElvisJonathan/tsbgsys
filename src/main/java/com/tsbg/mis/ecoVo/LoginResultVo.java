package com.tsbg.mis.ecoVo;

public class LoginResultVo {

    private String userName;

    private String token;

    private String userCode;

    private String userPwd;

    private Integer uid;

    public LoginResultVo(){}

    public LoginResultVo(String userName, String token){
        this.userName = userName;
        this.token = token;
    }

    public LoginResultVo(String userName, String userCode, String userPwd, Integer uid){
        this.userName = userName;
        this.userCode = userCode;
        this.userPwd = userPwd;
        this.uid = uid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
