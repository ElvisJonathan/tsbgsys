package com.tsbg.ecosys.ecoModel;

public class UserRole {
    private Integer uroleId;

    private Integer userId;

    private Integer roleId;

    private Integer projId;

    private String remark;

    //额外字段
    private String proName;

    public Integer getUroleId() {
        return uroleId;
    }

    public void setUroleId(Integer uroleId) {
        this.uroleId = uroleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getProjId() {
        return projId;
    }

    public void setProjId(Integer projId) {
        this.projId = projId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }
}
