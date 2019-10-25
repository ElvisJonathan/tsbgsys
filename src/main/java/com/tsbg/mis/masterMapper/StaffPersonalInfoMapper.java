package com.tsbg.mis.masterMapper;

import com.tsbg.mis.masterModel.StaffPersonalInfo;

public interface StaffPersonalInfoMapper {
    int deleteByPrimaryKey(Integer staffPersonId);

    int insert(StaffPersonalInfo record);

    int insertSelective(StaffPersonalInfo record);

    StaffPersonalInfo selectByPrimaryKey(Integer staffPersonId);

    int updateByPrimaryKeySelective(StaffPersonalInfo record);

    int updateByPrimaryKey(StaffPersonalInfo record);
}