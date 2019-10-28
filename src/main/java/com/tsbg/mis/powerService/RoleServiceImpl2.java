package com.tsbg.mis.powerService;

import com.tsbg.mis.powerMapper.RoleMapper2;
import com.tsbg.mis.powerModel.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl2 implements RoleService2 {

    @Autowired
    private RoleMapper2 roleMapper;
    @Override
    public List<Role> findRoleByUserCode(String userCode) {
        return roleMapper.findRoleByUserCode(userCode);
    }

    @Override
    public List<Integer> findRoleByUserCode2(String userCode) {
        return roleMapper.findRoleByUserCode2(userCode);
    }
}
