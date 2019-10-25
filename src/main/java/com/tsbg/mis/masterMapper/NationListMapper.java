package com.tsbg.mis.masterMapper;

import com.tsbg.mis.masterModel.NationList;

public interface NationListMapper {
    int deleteByPrimaryKey(Integer nationId);

    int insert(NationList record);

    int insertSelective(NationList record);

    NationList selectByPrimaryKey(Integer nationId);

    int updateByPrimaryKeySelective(NationList record);

    int updateByPrimaryKey(NationList record);
}