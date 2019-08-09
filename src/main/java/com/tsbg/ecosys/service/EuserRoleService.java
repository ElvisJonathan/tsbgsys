package com.tsbg.ecosys.service;

import com.tsbg.ecosys.model.EuserRole;

public interface EuserRoleService {

    //根据用户工号去数据库查询是否为生态员工
    EuserRole selectEuserInfo(String userCode);

    //通过uid查询用户对应的角色rid
    int selectRidByUid(int uid);
}
