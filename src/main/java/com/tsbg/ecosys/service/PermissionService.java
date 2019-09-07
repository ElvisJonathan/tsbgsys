package com.tsbg.ecosys.service;

import com.tsbg.ecosys.model.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionService {

    //根据pid查询对应权限详情
    List<String> selectPowerDetailByPid(List<Integer> pid);

    //根据角色ID查询角色对应的权限信息
    List<Permission> findPermissionByRoleId(@Param("roleId") Integer roleId);

    //根据角色ID查询角色对应的权限信息
    List<String> findPermissionByRoleId2(@Param("roleId") Integer roleId);
}
