package com.tsbg.ecosys.serviceImpl;

import com.tsbg.ecosys.mapper.UserRoleMapper;
import com.tsbg.ecosys.model.UserRole;
import com.tsbg.ecosys.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public UserRole selectEuserInfo(String userCode) {
        return userRoleMapper.selectEuserInfo(userCode);
    }

    @Override
    public Integer selectRidByUid(Integer uid) {
        return userRoleMapper.selectRidByUid(uid);
    }

    @Override
    public List<Integer> getRole(Integer uid) {
        return userRoleMapper.getRole(uid);
    }
}
