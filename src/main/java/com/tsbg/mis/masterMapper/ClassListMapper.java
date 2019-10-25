package com.tsbg.mis.masterMapper;

import com.tsbg.mis.masterModel.ClassList;

public interface ClassListMapper {
    int deleteByPrimaryKey(Integer classId);

    int insert(ClassList record);

    int insertSelective(ClassList record);

    ClassList selectByPrimaryKey(Integer classId);

    int updateByPrimaryKeySelective(ClassList record);

    int updateByPrimaryKey(ClassList record);
}