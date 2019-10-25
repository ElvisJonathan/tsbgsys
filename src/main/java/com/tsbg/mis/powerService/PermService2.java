package com.tsbg.mis.powerService;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermService2 {

    //根据角色ID查询角色对应的权限信息
    List<String> findPermissionByRoleId2(@Param("roleId") Integer roleId);

    /**
     * 查询某角色  菜单列表   权限列表
     */
    JSONObject getMyUserPermission(String userCode, Integer projId);

    /**
     * 查询用户个人的角色 菜单 权限
     */
    JSONObject getMyUserPermission2(String userCode);
}
