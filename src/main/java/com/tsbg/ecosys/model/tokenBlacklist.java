package com.tsbg.ecosys.model;

import java.util.Date;

public class tokenBlacklist {
    private Long tokenId;

    private String tokenCode;

    private Integer status;

    private Date createTime;

    public Long getTokenId() {
        return tokenId;
    }

    public void setTokenId(Long tokenId) {
        this.tokenId = tokenId;
    }

    public String getTokenCode() {
        return tokenCode;
    }

    public void setTokenCode(String tokenCode) {
        this.tokenCode = tokenCode == null ? null : tokenCode.trim();
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
}