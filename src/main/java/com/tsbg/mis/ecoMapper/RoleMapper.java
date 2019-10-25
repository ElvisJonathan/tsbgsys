package com.tsbg.mis.ecoMapper;


import com.tsbg.mis.ecoModel.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface RoleMapper {

    //通过userCode查找用户角色信息
    List<Role> findRoleByUserCode(@Param("userCode") String userCode);

    //通过userCode查找用户角色信息
    List<Integer> findRoleByUserCode2(@Param("userCode") String userCode);
}
