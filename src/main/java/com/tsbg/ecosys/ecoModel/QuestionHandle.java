package com.tsbg.ecosys.ecoModel;

import java.util.Date;

public class QuestionHandle {
    private Integer questionHandleId;

    private Integer questionFeedbackId;

    private String handleCode;

    private String handleName;

    private Integer questionType;

    private Integer isHandle;

    private Date handleTime;

    private Date deadlineDate;

    private Date startDate;

    private Integer isComplete;

    private Integer projId;

    private String remark;

    public Integer getQuestionHandleId() {
        return questionHandleId;
    }

    public void setQuestionHandleId(Integer questionHandleId) {
        this.questionHandleId = questionHandleId;
    }

    public Integer getQuestionFeedbackId() {
        return questionFeedbackId;
    }

    public void setQuestionFeedbackId(Integer questionFeedbackId) {
        this.questionFeedbackId = questionFeedbackId;
    }

    public String getHandleCode() {
        return handleCode;
    }

    public void setHandleCode(String handleCode) {
        this.handleCode = handleCode == null ? null : handleCode.trim();
    }

    public String getHandleName() {
        return handleName;
    }

    public void setHandleName(String handleName) {
        this.handleName = handleName == null ? null : handleName.trim();
    }

    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    public Integer getIsHandle() {
        return isHandle;
    }

    public void setIsHandle(Integer isHandle) {
        this.isHandle = isHandle;
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    public Date getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(Integer isComplete) {
        this.isComplete = isComplete;
    }

    public Integer getProjId() {
        return projId;
    }

    public void setProjId(Integer projId) {
        this.projId = projId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

}