package com.tsbg.ecosys.model;

/**
 * 封装了用户和权限ID的封装类
 * 用于接收多个对象
 */
public class PowerPackage {

    public PowerPackage() {
    }

    private EuserInfo euserInfo;

    private Object[] data;

    public EuserInfo getEuserInfo() {
        return euserInfo;
    }

    public void setEuserInfo(EuserInfo euserInfo) {
        this.euserInfo = euserInfo;
    }

    public Object[] getData() {
        return data;
    }

    public void setData(Object[] data) {
        this.data = data;
    }
}
