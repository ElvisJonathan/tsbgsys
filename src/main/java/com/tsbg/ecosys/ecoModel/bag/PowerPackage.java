package com.tsbg.ecosys.ecoModel.bag;

import com.tsbg.ecosys.ecoModel.UserInfo;

/**
 * 封装了用户、重置密码标志和权限ID的封装类
 * 用于接收多个对象
 */
public class PowerPackage {

    public PowerPackage() {
    }

    private UserInfo userInfo;

    private Object[] data;

    private Object sign;

    private Object userCode;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Object[] getData() {
        return data;
    }

    public void setData(Object[] data) {
        this.data = data;
    }

    public Object getSign() {
        return sign;
    }

    public void setSign(Object sign) {
        this.sign = sign;
    }

    public Object getUserCode() {
        return userCode;
    }

    public void setUserCode(Object userCode) {
        this.userCode = userCode;
    }
}
