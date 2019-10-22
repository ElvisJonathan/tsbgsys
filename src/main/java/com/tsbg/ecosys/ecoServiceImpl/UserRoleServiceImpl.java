package com.tsbg.ecosys.ecoServiceImpl;

import com.tsbg.ecosys.ecoMapper.UserRoleMapper;
import com.tsbg.ecosys.ecoModel.UserRole;
import com.tsbg.ecosys.ecoService.UserRoleService;
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

    @Override
    public int insertData(Integer userId, Integer roleId, Integer projId) {
        return userRoleMapper.insertData(userId,roleId,projId);
    }

    @Override
    public List<UserRole> selectProJMsgByUid(Integer uid) {
        return userRoleMapper.selectProJMsgByUid(uid);
    }

    @Override
    public int updateUserRoleByProAndRoleId(Integer roleId, Integer userId, Integer projId) {
        return userRoleMapper.updateUserRoleByProAndRoleId(roleId,userId,projId);
    }
}
