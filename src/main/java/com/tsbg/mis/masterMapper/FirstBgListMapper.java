package com.tsbg.mis.masterMapper;

import com.tsbg.mis.masterModel.FirstBgList;

public interface FirstBgListMapper {
    int deleteByPrimaryKey(Integer firstBgId);

    int insert(FirstBgList record);

    int insertSelective(FirstBgList record);

    FirstBgList selectByPrimaryKey(Integer firstBgId);

    int updateByPrimaryKeySelective(FirstBgList record);

    int updateByPrimaryKey(FirstBgList record);
}