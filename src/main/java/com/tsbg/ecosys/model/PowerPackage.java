package com.tsbg.ecosys.model;

/**
 * 封装了用户和权限ID的封装类
 * 用于接收多个对象
 */
public class PowerPackage {

    public PowerPackage() {
    }

    private EuserInfo euserInfo;

    private String[] data;

    public EuserInfo getEuserInfo() {
        return euserInfo;
    }

    public void setEuserInfo(EuserInfo euserInfo) {
        this.euserInfo = euserInfo;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }
}
