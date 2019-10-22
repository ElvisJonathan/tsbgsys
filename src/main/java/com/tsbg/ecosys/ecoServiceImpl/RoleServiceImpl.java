package com.tsbg.ecosys.ecoServiceImpl;

import com.tsbg.ecosys.ecoMapper.RoleMapper;
import com.tsbg.ecosys.ecoModel.Role;
import com.tsbg.ecosys.ecoService.RoleService;
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
