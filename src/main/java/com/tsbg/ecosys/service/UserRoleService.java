package com.tsbg.ecosys.service;

import com.tsbg.ecosys.model.UserRole;

public interface UserRoleService {

    //根据用户工号去数据库查询是否为生态员工
    UserRole selectEuserInfo(String userCode);

    //通过uid查询用户对应的角色rid
    Integer selectRidByUid(Integer uid);
}
