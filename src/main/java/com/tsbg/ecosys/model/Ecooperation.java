package com.tsbg.ecosys.model;

import java.util.Date;

public class Ecooperation {
    private Integer coopId;

    private Integer partnerNo;

    private String partnerName;

    private Date partnerCallintime;

    private String partnerBdOwner;

    private String partnerCostage;

    private Integer signContract;

    private String contractDate;

    private Integer entrust;

    private String entrustName;

    private Integer partnerAwarding;

    private String projectName;

    private String coType;

    private String coProgress;

    private String fiiCodepartment;

    private Date createTime;

    private Date updateTime;

    private String creater;

    private String updater;

    private Integer status;

    private Integer delStatus;

    private String remark;

    public Integer getCoopId() {
        return coopId;
    }

    public void setCoopId(Integer coopId) {
        this.coopId = coopId;
    }

    public Integer getPartnerNo() {
        return partnerNo;
    }

    public void setPartnerNo(Integer partnerNo) {
        this.partnerNo = partnerNo;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName == null ? null : partnerName.trim();
    }

    public Date getPartnerCallintime() {
        return partnerCallintime;
    }

    public void setPartnerCallintime(Date partnerCallintime) {
        this.partnerCallintime = partnerCallintime;
    }

    public String getPartnerBdOwner() {
        return partnerBdOwner;
    }

    public void setPartnerBdOwner(String partnerBdOwner) {
        this.partnerBdOwner = partnerBdOwner == null ? null : partnerBdOwner.trim();
    }

    public String getPartnerCostage() {
        return partnerCostage;
    }

    public void setPartnerCostage(String partnerCostage) {
        this.partnerCostage = partnerCostage == null ? null : partnerCostage.trim();
    }

    public Integer getSignContract() {
        return signContract;
    }

    public void setSignContract(Integer signContract) {
        this.signContract = signContract;
    }

    public String getContractDate() {
        return contractDate;
    }

    public void setContractDate(String contractDate) {
        this.contractDate = contractDate == null ? null : contractDate.trim();
    }

    public Integer getEntrust() {
        return entrust;
    }

    public void setEntrust(Integer entrust) {
        this.entrust = entrust;
    }

    public String getEntrustName() {
        return entrustName;
    }

    public void setEntrustName(String entrustName) {
        this.entrustName = entrustName == null ? null : entrustName.trim();
    }

    public Integer getPartnerAwarding() {
        return partnerAwarding;
    }

    public void setPartnerAwarding(Integer partnerAwarding) {
        this.partnerAwarding = partnerAwarding;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getCoType() {
        return coType;
    }

    public void setCoType(String coType) {
        this.coType = coType == null ? null : coType.trim();
    }

    public String getCoProgress() {
        return coProgress;
    }

    public void setCoProgress(String coProgress) {
        this.coProgress = coProgress == null ? null : coProgress.trim();
    }

    public String getFiiCodepartment() {
        return fiiCodepartment;
    }

    public void setFiiCodepartment(String fiiCodepartment) {
        this.fiiCodepartment = fiiCodepartment == null ? null : fiiCodepartment.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater == null ? null : updater.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(Integer delStatus) {
        this.delStatus = delStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}