package com.tsbg.ecosys.model;

public class UserRole {
    private Integer uroleId;

    private Integer userId;

    private Integer roleId;

    private String remark;

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}