package com.tsbg.mis.masterModel;

import java.util.Date;

public class StaffInfo {
    private Integer staffId;

    private String staffCode;

    private String staffName;

    private String staffSimpleName;

    private Integer staffTypeId;

    private Integer nationId;

    private Integer areaId;

    private String factoryId;

    private Integer firstBgId;

    private Integer secondBgId;

    private Integer bgId;

    private String costCode;

    private String organizationName;

    private String organizationCode;

    private Integer buId;

    private String departId;

    private Integer classId;

    private Integer groupId;

    private String legalPersonId;

    private Integer gender;

    private String creater;

    private Date createTime;

    private String updater;

    private Date updateTime;

    private Integer workStatusName;

    private Integer status;

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode == null ? null : staffCode.trim();
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName == null ? null : staffName.trim();
    }

    public String getStaffSimpleName() {
        return staffSimpleName;
    }

    public void setStaffSimpleName(String staffSimpleName) {
        this.staffSimpleName = staffSimpleName == null ? null : staffSimpleName.trim();
    }

    public Integer getStaffTypeId() {
        return staffTypeId;
    }

    public void setStaffTypeId(Integer staffTypeId) {
        this.staffTypeId = staffTypeId;
    }

    public Integer getNationId() {
        return nationId;
    }

    public void setNationId(Integer nationId) {
        this.nationId = nationId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(String factoryId) {
        this.factoryId = factoryId == null ? null : factoryId.trim();
    }

    public Integer getFirstBgId() {
        return firstBgId;
    }

    public void setFirstBgId(Integer firstBgId) {
        this.firstBgId = firstBgId;
    }

    public Integer getSecondBgId() {
        return secondBgId;
    }

    public void setSecondBgId(Integer secondBgId) {
        this.secondBgId = secondBgId;
    }

    public Integer getBgId() {
        return bgId;
    }

    public void setBgId(Integer bgId) {
        this.bgId = bgId;
    }

    public String getCostCode() {
        return costCode;
    }

    public void setCostCode(String costCode) {
        this.costCode = costCode == null ? null : costCode.trim();
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName == null ? null : organizationName.trim();
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode == null ? null : organizationCode.trim();
    }

    public Integer getBuId() {
        return buId;
    }

    public void setBuId(Integer buId) {
        this.buId = buId;
    }

    public String getDepartId() {
        return departId;
    }

    public void setDepartId(String departId) {
        this.departId = departId == null ? null : departId.trim();
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getLegalPersonId() {
        return legalPersonId;
    }

    public void setLegalPersonId(String legalPersonId) {
        this.legalPersonId = legalPersonId == null ? null : legalPersonId.trim();
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater == null ? null : updater.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getWorkStatusName() {
        return workStatusName;
    }

    public void setWorkStatusName(Integer workStatusName) {
        this.workStatusName = workStatusName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}