package com.tsbg.mis.questionModel.bag;

import java.util.Date;

//分页查询反馈、处理、文件等信息
public class ProjectqfPackage2 {
    //question_feedback表
    private Integer questionFeedbackId;
    private String questionName;
    private String questionDescribe;
    private String userCode;
    private String userName;
    private String userExt;
    private String userEmailAddress;
    private String userDepartment;
    private String remarka;
    private Date applicationDate;
    private Integer applyStatusId;
    private Integer projIda;
    //project表中的proName
    private String proName;

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
        this.questionName = questionName;
    }

    public String getQuestionDescribe() {
        return questionDescribe;
    }

    public void setQuestionDescribe(String questionDescribe) {
        this.questionDescribe = questionDescribe;
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

    public String getUserExt() {
        return userExt;
    }

    public void setUserExt(String userExt) {
        this.userExt = userExt;
    }

    public String getUserEmailAddress() {
        return userEmailAddress;
    }

    public void setUserEmailAddress(String userEmailAddress) {
        this.userEmailAddress = userEmailAddress;
    }

    public String getUserDepartment() {
        return userDepartment;
    }

    public void setUserDepartment(String userDepartment) {
        this.userDepartment = userDepartment;
    }

    public String getRemarka() {
        return remarka;
    }

    public void setRemarka(String remarka) {
        this.remarka = remarka;
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

    public Integer getProjIda() {
        return projIda;
    }

    public void setProjIda(Integer projIda) {
        this.projIda = projIda;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }
}
