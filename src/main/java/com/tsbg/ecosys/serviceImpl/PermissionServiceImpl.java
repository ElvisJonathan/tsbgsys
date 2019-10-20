package com.tsbg.ecosys.serviceImpl;

import com.tsbg.ecosys.mapper.PermissionMapper;
import com.tsbg.ecosys.model.Permission;
import com.tsbg.ecosys.model.bag.PermRolePackage;
import com.tsbg.ecosys.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<String> selectPowerDetailByPid(List<Integer> pid) {
        return permissionMapper.selectPowerDetailByPid(pid);
    }

    @Override
    public List<Permission> findPermissionByRoleId(Integer roleId) {
        return permissionMapper.findPermissionByRoleId(roleId);
    }

    @Override
    public List<String> findPermissionByRoleId2(Integer roleId) {
        return permissionMapper.findPermissionByRoleId2(roleId);
    }

    @Override
    public String selectPermission(String name) {
        return permissionMapper.selectPermission(name);
    }

    @Override
    public List<Integer> selectPermIdByPerm(String[] perm) {
        return permissionMapper.selectPermIdByPerm(perm);
    }

    @Override
    public List<PermRolePackage> selectRolePermMsg(Integer projId, Integer roleId) {
        return permissionMapper.selectRolePermMsg(projId,roleId);
    }
}
