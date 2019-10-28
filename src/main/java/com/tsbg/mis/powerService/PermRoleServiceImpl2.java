package com.tsbg.mis.powerService;

import com.tsbg.mis.powerMapper.PermRoleMapper2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermRoleServiceImpl2 implements PermRoleService2 {

    @Autowired
    private PermRoleMapper2 permRoleMapper;


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
