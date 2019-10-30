package com.tsbg.mis.powerService;

import com.alibaba.fastjson.JSONObject;
import com.tsbg.mis.powerMapper.PermMapper2;
import com.tsbg.mis.powerModel.Permission;
import com.tsbg.mis.powerModel.powerBag.PermRolePackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermServiceImpl2 implements PermService2 {

    @Autowired
    private PermMapper2 permMapper2;

    @Override
    public List<String> findPermissionByRoleId2(Integer roleId) {
        return permMapper2.findPermissionByRoleId2(roleId);
    }

    @Override
    public JSONObject getMyUserPermission(String userCode, Integer projId) {
        return permMapper2.getMyUserPermission(userCode,projId);
    }

    @Override
    public JSONObject getMyUserPermission2(String userCode) {
        return permMapper2.getMyUserPermission2(userCode);
    }

    @Override
    public List<String> selectPowerDetailByPid(List<Integer> pid) {
        return permMapper2.selectPowerDetailByPid(pid);
    }

    @Override
    public List<Permission> findPermissionByRoleId(Integer roleId) {
        return permMapper2.findPermissionByRoleId(roleId);
    }

    @Override
    public String selectPermission(String name) {
        return permMapper2.selectPermission(name);
    }

    @Override
    public List<Integer> selectPermIdByPerm(String[] perm) {
        return permMapper2.selectPermIdByPerm(perm);
    }

    @Override
    public List<PermRolePackage> selectRolePermMsg(Integer projId, Integer roleId) {
        return permMapper2.selectRolePermMsg(projId,roleId);
    }

    @Override
    public String selectPermListByUserCode(String userCode) {
        return permMapper2.selectPermListByUserCode(userCode);
    }
}
