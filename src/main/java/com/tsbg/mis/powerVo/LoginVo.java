package com.tsbg.mis.powerVo;

import com.tsbg.mis.powerModel.UserRole;

import java.util.List;

public class LoginVo {

    private String userCode;

    private String token;

    List<UserRole> userRoles;

    public LoginVo() {
    }

    public LoginVo(String userCode, String token, List<UserRole> userRoles) {
        this.userCode = userCode;
        this.token = token;
        this.userRoles = userRoles;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }
}
