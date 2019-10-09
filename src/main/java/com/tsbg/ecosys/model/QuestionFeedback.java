package com.tsbg.ecosys.model;

import java.util.Date;

public class QuestionFeedback {
    private Integer questionFeedbackId;

    private String questionName;

    private String questionDescribe;

    private String userCode;

    private String userName;

    private String userExt;

    private String userEmailAddress;

    private String userDepartment;

    private String remark;

    private Date applicationDate;

    private Integer applyStatusId;

    private Integer projId;

    public Integer getQuestionFeedbackId() {
        return questionFeedbackId;
    }

    public void setQuestionFeedbackId(Integer questionFeedbackId) {
        this.questionFeedbackId = questionFeedbackId;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName == null ? null : questionName.trim();
    }


    public String getQuestionDescribe() {
        return questionDescribe;
    }

    public void setQuestionDescribe(String questionDescribe) {
        this.questionDescribe = questionDescribe == null ? null : questionDescribe.trim();
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserExt() {
        return userExt;
    }

    public void setUserExt(String userExt) {
        this.userExt = userExt == null ? null : userExt.trim();
    }

    public String getUserEmailAddress() {
        return userEmailAddress;
    }

    public void setUserEmailAddress(String userEmailAddress) {
        this.userEmailAddress = userEmailAddress == null ? null : userEmailAddress.trim();
    }

    public String getUserDepartment() {
        return userDepartment;
    }

    public void setUserDepartment(String userDepartment) {
        this.userDepartment = userDepartment == null ? null : userDepartment.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public Integer getApplyStatusId() {
        return applyStatusId;
    }

    public void setApplyStatusId(Integer applyStatusId) {
        this.applyStatusId = applyStatusId;
    }

    public Integer getProjId() {
        return projId;
    }

    public void setProjId(Integer projId) {
        this.projId = projId;
    }
}