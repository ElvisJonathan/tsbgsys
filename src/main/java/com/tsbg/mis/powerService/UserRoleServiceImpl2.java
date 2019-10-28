package com.tsbg.mis.powerService;

import com.tsbg.mis.powerMapper.UserRoleMapper2;
import com.tsbg.mis.powerModel.UserRole;
import com.tsbg.mis.powerModel.powerBag.RoleAndProJPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserRoleServiceImpl2 implements UserRoleService2{

    @Autowired
    private UserRoleMapper2 userRoleMapper2;

    @Override
    public int insertData(Integer userId, Integer roleId, String createCode, Date date, Integer projId) {
        return userRoleMapper2.insertData(userId,roleId,createCode,date,projId);
    }

    @Override
    public List<RoleAndProJPackage> selectProJMsgByUid(Integer uid) {
        return userRoleMapper2.selectProJMsgByUid(uid);
    }

    @Override
    public UserRole selectEuserInfo(String userCode) {
        return userRoleMapper2.selectEuserInfo(userCode);
    }

    @Override
    public Integer selectRidByUid(Integer uid) {
        return userRoleMapper2.selectRidByUid(uid);
    }

    @Override
    public List<Integer> getRole(Integer uid) {
        return userRoleMapper2.getRole(uid);
    }

    @Override
    public int updateUserRoleByProAndRoleId(Integer roleId, Integer userId, Integer projId) {
        return userRoleMapper2.updateUserRoleByProAndRoleId(roleId,userId,projId);
    }
}
