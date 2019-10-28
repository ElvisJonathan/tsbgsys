package com.tsbg.mis.stampModel;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class AddProject {
    private Integer stampUseId;         //用印申請id
    private String userCode;            //申請人工號
    private String userName;            //申請人姓名
    private String stampUseFileName;    //用印文件名稱
    private Integer stampTypeId;        //印章類別編號
    private Integer useStampNum;        //用印文件份數
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date submitDate;            //申請提交日期
    private Integer isProjectItems;     //是否專案
    private String projectItemsName;    //档案名称
    private Integer status;             //歸檔信息有效狀態
    private String remark;              //備注説明
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date lastUpdateDate;        //最後修改時間
    private String lastUpdateCode;      //最後修改人工號
    private Integer projId;             //項目編號
    private String applyNum;            //申请单号
    private Integer factoryId;          //厂区id
    private Integer BGId;               //BGid
    private Integer BUId;               //BU事业处id
    private Integer departId;           //所属部门id
    private Integer classId;            //课级id
    private Integer groupId;            //组级单位id
    private String userExt;             //分机号
    private String emailAddress;        //邮箱地址


    public Integer getStampUseId() {
        return stampUseId;
    }

    public void setStampUseId(Integer stampUseId) {
        this.stampUseId = stampUseId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStampUseFileName() {
        return stampUseFileName;
    }

    public void setStampUseFileName(String stampUseFileName) {
        this.stampUseFileName = stampUseFileName;
    }

    public Integer getStampTypeId() {
        return stampTypeId;
    }

    public void setStampTypeId(Integer stampTypeId) {
        this.stampTypeId = stampTypeId;
    }

    public Integer getUseStampNum() {
        return useStampNum;
    }

    public void setUseStampNum(Integer useStampNum) {
        this.useStampNum = useStampNum;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public Integer getIsProjectItems() {
        return isProjectItems;
    }

    public void setIsProjectItems(Integer isProjectItems) {
        this.isProjectItems = isProjectItems;
    }

    public String getProjectItemsName() {
        return projectItemsName;
    }

    public void setProjectItemsName(String projectItemsName) {
        this.projectItemsName = projectItemsName;
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
        this.remark = remark;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getLastUpdateCode() {
        return lastUpdateCode;
    }

    public void setLastUpdateCode(String lastUpdateCode) {
        this.lastUpdateCode = lastUpdateCode;
    }

    public Integer getProjId() {
        return projId;
    }

    public void setProjId(Integer projId) {
        this.projId = projId;
    }

    public String getApplyNum() {
        return applyNum;
    }

    public void setApplyNum(String applyNum) {
        this.applyNum = applyNum;
    }

    public Integer getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Integer factoryId) {
        this.factoryId = factoryId;
    }

    public Integer getBGId() {
        return BGId;
    }

    public void setBGId(Integer BGId) {
        this.BGId = BGId;
    }

    public Integer getBUId() {
        return BUId;
    }

    public void setBUId(Integer BUId) {
        this.BUId = BUId;
    }

    public Integer getDepartId() {
        return departId;
    }

    public void setDepartId(Integer departId) {
        this.departId = departId;
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

    public String getUserExt() {
        return userExt;
    }

    public void setUserExt(String userExt) {
        this.userExt = userExt;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
