package com.tsbg.mis.masterMapper;

import com.tsbg.mis.masterModel.GroupList;

public interface GroupListMapper {
    int deleteByPrimaryKey(Integer groupId);

    int insert(GroupList record);

    int insertSelective(GroupList record);

    GroupList selectByPrimaryKey(Integer groupId);

    int updateByPrimaryKeySelective(GroupList record);

    int updateByPrimaryKey(GroupList record);
}