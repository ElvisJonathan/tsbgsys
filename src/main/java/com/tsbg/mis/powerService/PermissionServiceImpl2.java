package com.tsbg.mis.powerService;

import com.tsbg.mis.powerMapper.PermissionMapper2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl2 implements PermissionService2 {

    @Autowired
    private PermissionMapper2 permissionMapper2;

    @Override
    public List<String> selectPermission() {
        return permissionMapper2.selectPermission();
    }
}
