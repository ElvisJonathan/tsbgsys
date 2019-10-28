package com.tsbg.mis.masterMapper;

import com.tsbg.mis.masterModel.UnitList;

public interface UnitListMapper {
    int deleteByPrimaryKey(Integer unitId);

    int insert(UnitList record);

    int insertSelective(UnitList record);

    UnitList selectByPrimaryKey(Integer unitId);

    int updateByPrimaryKeySelective(UnitList record);

    int updateByPrimaryKey(UnitList record);
}