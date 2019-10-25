package com.tsbg.mis.powerService;

import com.tsbg.mis.powerMapper.UserRoleMapper2;
import com.tsbg.mis.powerModel.UserRole;
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
    public List<UserRole> selectProJMsgByUid(Integer uid) {
        return userRoleMapper2.selectProJMsgByUid(uid);
    }
}
