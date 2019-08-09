package com.tsbg.ecosys.service;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EperRoleService {

    //通过角色rid查询对应的权限id
    List<Integer> selectPidByRid(int rid);

    //通过角色rid去查询对应的prid
    List<Integer> selectPridByRid(int rid);

    //通过prid去修改pid
    int updatePowerByPrid(@Param("pid") Object pid, @Param("prid") Object prid);
}
