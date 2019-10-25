package com.tsbg.mis.masterMapper;

import com.tsbg.mis.masterModel.FactoryList;

public interface FactoryListMapper {
    int deleteByPrimaryKey(Integer factoryId);

    int insert(FactoryList record);

    int insertSelective(FactoryList record);

    FactoryList selectByPrimaryKey(Integer factoryId);

    int updateByPrimaryKeySelective(FactoryList record);

    int updateByPrimaryKey(FactoryList record);
}