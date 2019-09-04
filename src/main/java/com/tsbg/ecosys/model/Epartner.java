package com.tsbg.ecosys.model;

import java.util.Date;

public class Epartner {
    private Integer partnerNo;

    private String partnerName;

    private Date partnerDate;

    private String partnerRegistcapital;

    private Integer partnerList;

    private String partnerScale;

    private String partnerAddr;

    private String partnerWebddr;

    private String partnerTech;

    private String partnerProduct;

    private String partnerChannel;

    private String partnerTurnover;

    private String partnerRegion;

    private String partnerIndustry;

    private Integer status;

    private Integer delStatus;

    private Date createTime;

    private Date updateTime;

    private String creater;

    private Integer createrIdentity;

    private String updater;

    private String remark;

    private Integer projId;

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

    public Date getPartnerDate() {
        return partnerDate;
    }

    public void setPartnerDate(Date partnerDate) {
        this.partnerDate = partnerDate;
    }

    public String getPartnerRegistcapital() {
        return partnerRegistcapital;
    }

    public void setPartnerRegistcapital(String partnerRegistcapital) {
        this.partnerRegistcapital = partnerRegistcapital == null ? null : partnerRegistcapital.trim();
    }

    public Integer getPartnerList() {
        return partnerList;
    }

    public void setPartnerList(Integer partnerList) {
        this.partnerList = partnerList;
    }

    public String getPartnerScale() {
        return partnerScale;
    }

    public void setPartnerScale(String partnerScale) {
        this.partnerScale = partnerScale == null ? null : partnerScale.trim();
    }

    public String getPartnerAddr() {
        return partnerAddr;
    }

    public void setPartnerAddr(String partnerAddr) {
        this.partnerAddr = partnerAddr == null ? null : partnerAddr.trim();
    }

    public String getPartnerWebddr() {
        return partnerWebddr;
    }

    public void setPartnerWebddr(String partnerWebddr) {
        this.partnerWebddr = partnerWebddr == null ? null : partnerWebddr.trim();
    }

    public String getPartnerTech() {
        return partnerTech;
    }

    public void setPartnerTech(String partnerTech) {
        this.partnerTech = partnerTech == null ? null : partnerTech.trim();
    }

    public String getPartnerProduct() {
        return partnerProduct;
    }

    public void setPartnerProduct(String partnerProduct) {
        this.partnerProduct = partnerProduct == null ? null : partnerProduct.trim();
    }

    public String getPartnerChannel() {
        return partnerChannel;
    }

    public void setPartnerChannel(String partnerChannel) {
        this.partnerChannel = partnerChannel == null ? null : partnerChannel.trim();
    }

    public String getPartnerTurnover() {
        return partnerTurnover;
    }

    public void setPartnerTurnover(String partnerTurnover) {
        this.partnerTurnover = partnerTurnover == null ? null : partnerTurnover.trim();
    }

    public String getPartnerRegion() {
        return partnerRegion;
    }

    public void setPartnerRegion(String partnerRegion) {
        this.partnerRegion = partnerRegion == null ? null : partnerRegion.trim();
    }

    public String getPartnerIndustry() {
        return partnerIndustry;
    }

    public void setPartnerIndustry(String partnerIndustry) {
        this.partnerIndustry = partnerIndustry == null ? null : partnerIndustry.trim();
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getProjId() {
        return projId;
    }

    public void setProjId(Integer projId) {
        this.projId = projId;
    }

    public Integer getCreaterIdentity() {
        return createrIdentity;
    }

    public void setCreaterIdentity(Integer createrIdentity) {
        this.createrIdentity = createrIdentity;
    }
}