package com.tsbg.mis.masterMapper;

import com.tsbg.mis.masterModel.AreaList;

public interface AreaListMapper {
    int deleteByPrimaryKey(Integer areaId);

    int insert(AreaList record);

    int insertSelective(AreaList record);

    AreaList selectByPrimaryKey(Integer areaId);

    int updateByPrimaryKeySelective(AreaList record);

    int updateByPrimaryKey(AreaList record);
}