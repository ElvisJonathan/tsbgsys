package com.tsbg.ecosys.service;

import java.util.List;

public interface EperRoleService {

    //通过角色rid查询对应的权限id
    List<Integer> selectPidByRid(int rid);
}
