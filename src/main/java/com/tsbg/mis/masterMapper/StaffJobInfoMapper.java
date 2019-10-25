package com.tsbg.mis.masterMapper;

import com.tsbg.mis.masterModel.StaffJobInfo;

public interface StaffJobInfoMapper {
    int deleteByPrimaryKey(Integer staffJobInfoId);

    int insert(StaffJobInfo record);

    int insertSelective(StaffJobInfo record);

    StaffJobInfo selectByPrimaryKey(Integer staffJobInfoId);

    int updateByPrimaryKeySelective(StaffJobInfo record);

    int updateByPrimaryKey(StaffJobInfo record);
}