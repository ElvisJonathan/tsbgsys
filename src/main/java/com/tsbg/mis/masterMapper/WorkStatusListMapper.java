package com.tsbg.mis.masterMapper;

import com.tsbg.mis.masterModel.WorkStatusList;

public interface WorkStatusListMapper {
    int deleteByPrimaryKey(Integer workStatusId);

    int insert(WorkStatusList record);

    int insertSelective(WorkStatusList record);

    WorkStatusList selectByPrimaryKey(Integer workStatusId);

    int updateByPrimaryKeySelective(WorkStatusList record);

    int updateByPrimaryKey(WorkStatusList record);
}