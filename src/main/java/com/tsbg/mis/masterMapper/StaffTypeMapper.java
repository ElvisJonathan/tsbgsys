package com.tsbg.mis.masterMapper;

import com.tsbg.mis.masterModel.StaffType;

public interface StaffTypeMapper {
    int deleteByPrimaryKey(Integer leaderTypeId);

    int insert(StaffType record);

    int insertSelective(StaffType record);

    StaffType selectByPrimaryKey(Integer leaderTypeId);

    int updateByPrimaryKeySelective(StaffType record);

    int updateByPrimaryKey(StaffType record);
}