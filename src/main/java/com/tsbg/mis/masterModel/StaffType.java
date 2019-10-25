package com.tsbg.mis.masterModel;

import java.util.Date;

public class StaffType {
    private Integer leaderTypeId;

    private String staffTypeName;

    private Integer status;

    private String createCode;

    private Date createDate;

    private String lastUpdateCode;

    private Date lastUpdateDate;

    public Integer getLeaderTypeId() {
        return leaderTypeId;
    }

    public void setLeaderTypeId(Integer leaderTypeId) {
        this.leaderTypeId = leaderTypeId;
    }

    public String getStaffTypeName() {
        return staffTypeName;
    }

    public void setStaffTypeName(String staffTypeName) {
        this.staffTypeName = staffTypeName == null ? null : staffTypeName.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateCode() {
        return createCode;
    }

    public void setCreateCode(String createCode) {
        this.createCode = createCode == null ? null : createCode.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getLastUpdateCode() {
        return lastUpdateCode;
    }

    public void setLastUpdateCode(String lastUpdateCode) {
        this.lastUpdateCode = lastUpdateCode == null ? null : lastUpdateCode.trim();
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}