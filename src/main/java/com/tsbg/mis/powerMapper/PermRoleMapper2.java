package com.tsbg.mis.powerMapper;

import com.tsbg.mis.powerModel.PermRole;

public interface PermRoleMapper2 {
    int deleteByPrimaryKey(Integer permroleId);

    int insert(PermRole record);

    int insertSelective(PermRole record);

    PermRole selectByPrimaryKey(Integer permroleId);

    int updateByPrimaryKeySelective(PermRole record);

    int updateByPrimaryKey(PermRole record);
}