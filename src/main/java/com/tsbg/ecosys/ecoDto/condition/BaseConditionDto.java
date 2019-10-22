package com.tsbg.ecosys.ecoDto.condition;

import java.util.List;

public class BaseConditionDto {

    private Long id;

    private List<Long> ids;

    private Boolean deleted;

    private String status;

    private List<String> statuses;

    private Long createdTimeStart;
    private Long createdTimeEnd;

    private Long updatedTimeStart;
    private Long updatedTimeEnd;
    //排序字段
    private String orderBy;
    //倒序 true 为 倒序
    private Boolean desc;

    //分页大小
    private Integer limit;
    //数据下标
    private Integer offset;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<String> statuses) {
        this.statuses = statuses;
    }

    public Long getCreatedTimeStart() {
        return createdTimeStart;
    }

    public void setCreatedTimeStart(Long createdTimeStart) {
        this.createdTimeStart = createdTimeStart;
    }

    public Long getCreatedTimeEnd() {
        return createdTimeEnd;
    }

    public void setCreatedTimeEnd(Long createdTimeEnd) {
        this.createdTimeEnd = createdTimeEnd;
    }

    public Long getUpdatedTimeStart() {
        return updatedTimeStart;
    }

    public void setUpdatedTimeStart(Long updatedTimeStart) {
        this.updatedTimeStart = updatedTimeStart;
    }

    public Long getUpdatedTimeEnd() {
        return updatedTimeEnd;
    }

    public void setUpdatedTimeEnd(Long updatedTimeEnd) {
        this.updatedTimeEnd = updatedTimeEnd;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Boolean getDesc() {
        return desc;
    }

    public void setDesc(Boolean desc) {
        this.desc = desc;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
