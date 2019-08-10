package com.tsbg.ecosys.serviceImpl;

import com.tsbg.ecosys.mapper.EuserRoleMapper;
import com.tsbg.ecosys.model.EuserRole;
import com.tsbg.ecosys.service.EuserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EuserRoleServiceImpl implements EuserRoleService {

    @Autowired
    private EuserRoleMapper euserRoleMapper;

    @Override
    public EuserRole selectEuserInfo(String userCode) {
        return euserRoleMapper.selectEuserInfo(userCode);
    }

    @Override
    public Integer selectRidByUid(Integer uid) {
        return euserRoleMapper.selectRidByUid(uid);
    }
}
