package com.tsbg.mis.masterMapper;

import com.tsbg.mis.masterModel.DepartList;

public interface DepartListMapper {
    int deleteByPrimaryKey(Integer departId);

    int insert(DepartList record);

    int insertSelective(DepartList record);

    DepartList selectByPrimaryKey(Integer departId);

    int updateByPrimaryKeySelective(DepartList record);

    int updateByPrimaryKey(DepartList record);
}