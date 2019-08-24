package com.tsbg.ecosys.serviceImpl;

import com.tsbg.ecosys.mapper.PermRoleMapper;
import com.tsbg.ecosys.service.PermRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermRoleServiceImpl implements PermRoleService {

    @Autowired
    private PermRoleMapper permRoleMapper;


    @Override
    public List<Integer> selectPidByRid(Integer rid) {
        return permRoleMapper.selectPidByRid(rid);
    }

    @Override
    public List<Integer> selectPridByRid(Integer rid) {
        return permRoleMapper.selectPridByRid(rid);
    }

    @Override
    public int updatePowerByPrid(Object pid, Object prid) {
        return permRoleMapper.updatePowerByPrid(pid, prid);
    }
}
