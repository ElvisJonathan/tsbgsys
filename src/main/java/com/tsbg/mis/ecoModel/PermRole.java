package com.tsbg.mis.ecoModel;

public class PermRole {
    private Integer permroleId;

    private Integer permId;

    private Integer roleId;

    private String remark;

    public Integer getPermroleId() {
        return permroleId;
    }

    public void setPermroleId(Integer permroleId) {
        this.permroleId = permroleId;
    }

    public Integer getPermId() {
        return permId;
    }

    public void setPermId(Integer permId) {
        this.permId = permId;
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