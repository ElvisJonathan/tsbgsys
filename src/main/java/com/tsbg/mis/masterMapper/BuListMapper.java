package com.tsbg.mis.masterMapper;

import com.tsbg.mis.masterModel.BuList;

public interface BuListMapper {
    int deleteByPrimaryKey(Integer buId);

    int insert(BuList record);

    int insertSelective(BuList record);

    BuList selectByPrimaryKey(Integer buId);

    int updateByPrimaryKeySelective(BuList record);

    int updateByPrimaryKey(BuList record);
}