package com.tsbg.ecosys.serviceImpl;

import com.tsbg.ecosys.mapper.EperRoleMapper;
import com.tsbg.ecosys.model.EperRole;
import com.tsbg.ecosys.service.EperRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EperRoleServiceImpl implements EperRoleService {

    @Autowired
    private EperRoleMapper eperRoleMapper;


    @Override
    public List<Integer> selectPidByRid(Integer rid) {
        return eperRoleMapper.selectPidByRid(rid);
    }

    @Override
    public List<Integer> selectPridByRid(Integer rid) {
        return eperRoleMapper.selectPridByRid(rid);
    }

    @Override
    public int updatePowerByPrid(Object pid, Object prid) {
        return eperRoleMapper.updatePowerByPrid(pid, prid);
    }
}
