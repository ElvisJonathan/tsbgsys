package com.tsbg.ecosys.service;

import java.util.List;

public interface EpermissionService {

    //根据pid查询对应权限详情
    List<String> selectPowerDetailByPid(List<Integer> pid);
}
