package com.tsbg.mis.ecoModel;

import java.util.Date;

public class FileInfo {
    private Integer fileId;

    private String dispName;

    private String fileName;

    private String filePath;

    private Integer projId;

    private Integer relDocId;

    private String lastUpdateUser;

    private String lastDownloadUser;

    private Date updatedTime;

    private String keyword;

    private Integer status;

    private String remark;




    private Integer questionFeedbackId;

    private Integer questionHandleId;

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getDispName() {
        return dispName;
    }

    public void setDispName(String dispName) {
        this.dispName = dispName == null ? null : dispName.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    public Integer getProjId() {
        return projId;
    }

    public void setProjId(Integer projId) {
        this.projId = projId;
    }

    public Integer getRelDocId() {
        return relDocId;
    }

    public void setRelDocId(Integer relDocId) {
        this.relDocId = relDocId;
    }

    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }

    public String getLastDownloadUser() {
        return lastDownloadUser;
    }

    public void setLastDownloadUser(String lastDownloadUser) {
        this.lastDownloadUser = lastDownloadUser;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
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







    public Integer getQuestionFeedbackId() {
        return questionFeedbackId;
    }

    public void setQuestionFeedbackId(Integer questionFeedbackId) {
        this.questionFeedbackId = questionFeedbackId;
    }

    public Integer getQuestionHandleId() {
        return questionHandleId;
    }

    public void setQuestionHandleId(Integer questionHandleId) {
        this.questionHandleId = questionHandleId;
    }
}