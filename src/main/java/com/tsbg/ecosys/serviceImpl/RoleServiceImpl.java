package com.tsbg.ecosys.serviceImpl;

import com.tsbg.ecosys.mapper.RoleMapper;
import com.tsbg.ecosys.model.Role;
import com.tsbg.ecosys.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> findRoleByUserCode(String userCode) {
        return roleMapper.findRoleByUserCode(userCode);
    }

    @Override
    public List<Integer> findRoleByUserCode2(String userCode) {
        return roleMapper.findRoleByUserCode2(userCode);
    }
}
