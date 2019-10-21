package com.tsbg.ecosys.mapper;

import com.tsbg.ecosys.model.Permission;
import com.tsbg.ecosys.model.bag.PermRolePackage;
import com.tsbg.ecosys.model.example.EpermissionExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PermissionMapper {

    int deleteByPrimaryKey(Integer permId);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer permId);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    //根据pid查询对应权限详情
    List<String> selectPowerDetailByPid(List<Integer> pid);

    //根据permission查询对应权限ID
    List<Integer> selectPermIdByPerm(String[] perm);

    long countByExample(EpermissionExample example);

    int deleteByExample(EpermissionExample example);

    List<Permission> selectByExample(EpermissionExample example);

    int updateByExampleSelective(@Param("record") Permission record, @Param("example") EpermissionExample example);

    int updateByExample(@Param("record") Permission record, @Param("example") EpermissionExample example);

    //根据角色ID查询角色对应的权限信息
    List<Permission> findPermissionByRoleId(@Param("roleId") Integer roleId);

    //根据角色ID查询角色对应的权限信息
    List<String> findPermissionByRoleId2(@Param("roleId") Integer roleId);

    //根据权限返回对应权限名
    String selectPermission(String name);

    //查询当前系统角色-权限对应信息
    List<PermRolePackage> selectRolePermMsg(@Param("projId")Integer projId, @Param("roleId")Integer roleId);
}
