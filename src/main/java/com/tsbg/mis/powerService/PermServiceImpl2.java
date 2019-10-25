package com.tsbg.mis.powerService;

import com.alibaba.fastjson.JSONObject;
import com.tsbg.mis.powerMapper.PermMapper2;
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
}
