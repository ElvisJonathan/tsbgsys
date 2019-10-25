package com.tsbg.mis.masterMapper;

import com.tsbg.mis.masterModel.SecondBgList;

public interface SecondBgListMapper {
    int deleteByPrimaryKey(Integer secondBgId);

    int insert(SecondBgList record);

    int insertSelective(SecondBgList record);

    SecondBgList selectByPrimaryKey(Integer secondBgId);

    int updateByPrimaryKeySelective(SecondBgList record);

    int updateByPrimaryKey(SecondBgList record);
}