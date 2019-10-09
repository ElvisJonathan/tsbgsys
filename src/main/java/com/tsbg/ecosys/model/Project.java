package com.tsbg.ecosys.model;

import java.util.Date;

public class Project {

    private Integer projId;

    private String proName;

    private Date proStart;

    private Date proEnd;

    private String proDirector;

    private String remark;

    private Integer status;

    public Integer getProjId() {
        return projId;
    }

    public String getProName() {
        return proName;
    }

    public Date getProStart() {
        return proStart;
    }

    public Date getProEnd() {
        return proEnd;
    }

    public String getProDirector() {
        return proDirector;
    }

    public String getRemark() {
        return remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setProjId(Integer projId) {
        this.projId = projId;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public void setProStart(Date proStart) {
        this.proStart = proStart;
    }

    public void setProEnd(Date proEnd) {
        this.proEnd = proEnd;
    }

    public void setProDirector(String proDirector) {
        this.proDirector = proDirector;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
