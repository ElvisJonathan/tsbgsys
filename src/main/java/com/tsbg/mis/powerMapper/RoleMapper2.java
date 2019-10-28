package com.tsbg.mis.powerMapper;

import com.tsbg.mis.powerModel.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper2 {
    int deleteByPrimaryKey(Integer roleid);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleid);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    //通过userCode查找用户角色信息
    List<Role> findRoleByUserCode(@Param("userCode") String userCode);

    //通过userCode查找用户角色信息
    List<Integer> findRoleByUserCode2(@Param("userCode") String userCode);
}
