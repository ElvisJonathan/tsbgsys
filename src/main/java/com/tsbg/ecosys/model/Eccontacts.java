package com.tsbg.ecosys.model;

import java.util.Date;

public class Eccontacts {
    private Integer ccid;

    private Integer partnerNo;

    private String partnerCname;

    private String name;

    private String title;

    private Integer gender;

    private String phoneNumber;

    private String mailAddress;

    private Date createTime;

    private Date updateTime;

    private String creater;

    private String updater;

    private Integer status;

    private String remark;

    public Eccontacts() {
    }

    public Eccontacts(String partnerCname, String name, String title, Integer gender, String phoneNumber, String mailAddress) {
        this.partnerCname = partnerCname;
        this.name = name;
        this.title = title;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.mailAddress = mailAddress;
    }

    public Integer getCcid() {
        return ccid;
    }

    public void setCcid(Integer ccid) {
        this.ccid = ccid;
    }

    public Integer getPartnerNo() {
        return partnerNo;
    }

    public void setPartnerNo(Integer partnerNo) {
        this.partnerNo = partnerNo;
    }

    public String getPartnerCname() {
        return partnerCname;
    }

    public void setPartnerCname(String partnerCname) {
        this.partnerCname = partnerCname == null ? null : partnerCname.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress == null ? null : mailAddress.trim();
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}