package com.tsbg.mis.masterMapper;

import com.tsbg.mis.masterModel.BgList;

public interface BgListMapper {
    int deleteByPrimaryKey(Integer bgId);

    int insert(BgList record);

    int insertSelective(BgList record);

    BgList selectByPrimaryKey(Integer bgId);

    int updateByPrimaryKeySelective(BgList record);

    int updateByPrimaryKey(BgList record);
}