package com.tsbg.mis.stampVo;

import com.tsbg.mis.stampModel.AddProject;

public class AddProjectVo extends AddProject {
    private String factoryName;         //厂区
    private String BGName;              //事业群
    private String BUName;              //事业处
    private String departName;          //部门
    private String typeName;            //印章類別名

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getBGName() {
        return BGName;
    }

    public void setBGName(String BGName) {
        this.BGName = BGName;
    }

    public String getBUName() {
        return BUName;
    }

    public void setBUName(String BUName) {
        this.BUName = BUName;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
