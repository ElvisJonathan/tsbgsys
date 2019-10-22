package com.tsbg.ecosys.ecoService;

import com.tsbg.ecosys.ecoModel.Permission;
import com.tsbg.ecosys.ecoModel.bag.PermRolePackage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionService {

    //根据pid查询对应权限详情
    List<String> selectPowerDetailByPid(List<Integer> pid);

    //根据角色ID查询角色对应的权限信息
    List<Permission> findPermissionByRoleId(@Param("roleId") Integer roleId);

    //根据角色ID查询角色对应的权限信息
    List<String> findPermissionByRoleId2(@Param("roleId") Integer roleId);

    //根据权限返回对应权限名
    String selectPermission(String name);

    //根据permission查询对应权限ID
    List<Integer> selectPermIdByPerm(String[] perm);

    //查询当前系统角色-权限对应信息
    List<PermRolePackage> selectRolePermMsg(@Param("projId")Integer projId, @Param("roleId")Integer roleId);
}
