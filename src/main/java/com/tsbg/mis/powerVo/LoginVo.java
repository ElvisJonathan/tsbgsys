package com.tsbg.mis.powerVo;

import com.tsbg.mis.powerModel.powerBag.RoleAndProJPackage;

import java.util.List;

public class LoginVo {

    private String userCode;

    private String userName;

    private String token;

    List<RoleAndProJPackage> userRoles;

    public LoginVo() {
    }

    public LoginVo(String userCode, String userName, String token, List<RoleAndProJPackage> userRoles) {
        this.userCode = userCode;
        this.userName = userName;
        this.token = token;
        this.userRoles = userRoles;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
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

    public List<RoleAndProJPackage> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<RoleAndProJPackage> userRoles) {
        this.userRoles = userRoles;
    }
}
