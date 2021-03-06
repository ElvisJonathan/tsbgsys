package com.tsbg.mis.powerMapper;

import com.alibaba.fastjson.JSONObject;
import com.tsbg.mis.powerModel.Permission;
import com.tsbg.mis.powerModel.powerBag.PermRolePackage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PermMapper2 {
    int deleteByPrimaryKey(Integer permId);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer permId);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    //根据角色ID查询角色对应的权限信息
    List<String> findPermissionByRoleId2(@Param("roleId") Integer roleId);

    /**
     * 查询用户的角色 菜单 权限
     */
    JSONObject getMyUserPermission(String userCode, Integer projId);
    /**
     * 查询用户个人的角色 菜单 权限
     */
    JSONObject getMyUserPermission2(String userCode);

    //根据pid查询对应权限详情
    List<String> selectPowerDetailByPid(List<Integer> pid);

    //根据permission查询对应权限ID
    List<Integer> selectPermIdByPerm(String[] perm);

    //根据角色ID查询角色对应的权限信息
    List<Permission> findPermissionByRoleId(@Param("roleId") Integer roleId);

    //根据权限返回对应权限名
    String selectPermission(String name);

    //查询当前系统角色-权限对应信息
    List<PermRolePackage> selectRolePermMsg(@Param("projId")Integer projId, @Param("roleId")Integer roleId);

    //生态系统权限列表查询
    String selectPermListByUserCode(String userCode);
}
