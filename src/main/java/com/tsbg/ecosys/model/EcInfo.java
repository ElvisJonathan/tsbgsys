package com.tsbg.ecosys.model;

import java.util.Date;

public class EcInfo {
    private Integer cid;

    private String partnerCname;

    private Date partnerCdate;

    private String partnerCregistcapital;

    private Integer partnerClist;

    private String partnerCscale;

    private String partnerCaddr;

    private String partnerCwebddr;

    private String partnerCtech;

    private String partnerCproduct;

    private String partnerCchannel;

    private String partnerCturnover;

    private String partnerCregion;

    private String partnerCindustry;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private String creater;

    private String updater;

    private String remark;

    public EcInfo() {
    }

    public EcInfo(String partnerCname, Date partnerCdate, String partnerCregistcapital, Integer partnerClist, String partnerCscale, String partnerCaddr, String partnerCwebddr, String partnerCtech, String partnerCproduct, String partnerCchannel, String partnerCturnover, String partnerCregion, String partnerCindustry, Date createTime) {
        this.partnerCname = partnerCname;
        this.partnerCdate = partnerCdate;
        this.partnerCregistcapital = partnerCregistcapital;
        this.partnerClist = partnerClist;
        this.partnerCscale = partnerCscale;
        this.partnerCaddr = partnerCaddr;
        this.partnerCwebddr = partnerCwebddr;
        this.partnerCtech = partnerCtech;
        this.partnerCproduct = partnerCproduct;
        this.partnerCchannel = partnerCchannel;
        this.partnerCturnover = partnerCturnover;
        this.partnerCregion = partnerCregion;
        this.partnerCindustry = partnerCindustry;
        this.createTime = createTime;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getPartnerCname() {
        return partnerCname;
    }

    public void setPartnerCname(String partnerCname) {
        this.partnerCname = partnerCname == null ? null : partnerCname.trim();
    }

    public Date getPartnerCdate() {
        return partnerCdate;
    }

    public void setPartnerCdate(Date partnerCdate) {
        this.partnerCdate = partnerCdate;
    }

    public String getPartnerCregistcapital() {
        return partnerCregistcapital;
    }

    public void setPartnerCregistcapital(String partnerCregistcapital) {
        this.partnerCregistcapital = partnerCregistcapital == null ? null : partnerCregistcapital.trim();
    }

    public Integer getPartnerClist() {
        return partnerClist;
    }

    public void setPartnerClist(Integer partnerClist) {
        this.partnerClist = partnerClist;
    }

    public String getPartnerCscale() {
        return partnerCscale;
    }

    public void setPartnerCscale(String partnerCscale) {
        this.partnerCscale = partnerCscale == null ? null : partnerCscale.trim();
    }

    public String getPartnerCaddr() {
        return partnerCaddr;
    }

    public void setPartnerCaddr(String partnerCaddr) {
        this.partnerCaddr = partnerCaddr == null ? null : partnerCaddr.trim();
    }

    public String getPartnerCwebddr() {
        return partnerCwebddr;
    }

    public void setPartnerCwebddr(String partnerCwebddr) {
        this.partnerCwebddr = partnerCwebddr == null ? null : partnerCwebddr.trim();
    }

    public String getPartnerCtech() {
        return partnerCtech;
    }

    public void setPartnerCtech(String partnerCtech) {
        this.partnerCtech = partnerCtech == null ? null : partnerCtech.trim();
    }

    public String getPartnerCproduct() { return partnerCproduct; }

    public void setPartnerCproduct(String partnerCproduct) {
        this.partnerCproduct = partnerCproduct == null ? null : partnerCproduct.trim();
    }

    public String getPartnerCchannel() {
        return partnerCchannel;
    }

    public void setPartnerCchannel(String partnerCchannel) {
        this.partnerCchannel = partnerCchannel == null ? null : partnerCchannel.trim();
    }

    public String getPartnerCturnover() {
        return partnerCturnover;
    }

    public void setPartnerCturnover(String partnerCturnover) {
        this.partnerCturnover = partnerCturnover == null ? null : partnerCturnover.trim();
    }

    public String getPartnerCregion() {
        return partnerCregion;
    }

    public void setPartnerCregion(String partnerCregion) {
        this.partnerCregion = partnerCregion == null ? null : partnerCregion.trim();
    }

    public String getPartnerCindustry() {
        return partnerCindustry;
    }

    public void setPartnerCindustry(String partnerCindustry) {
        this.partnerCindustry = partnerCindustry == null ? null : partnerCindustry.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
}