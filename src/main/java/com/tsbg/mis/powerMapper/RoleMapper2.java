package com.tsbg.mis.powerMapper;

import com.tsbg.mis.powerModel.Role;

public interface RoleMapper2 {
    int deleteByPrimaryKey(Integer roleid);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleid);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}