package com.tsbg.ecosys.serviceImpl;

import com.tsbg.ecosys.mapper.EperRoleMapper;
import com.tsbg.ecosys.service.EperRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EperRoleServiceImpl implements EperRoleService {

    @Autowired
    private EperRoleMapper eperRoleMapper;


    @Override
    public List<Integer> selectPidByRid(int rid) {
        return eperRoleMapper.selectPidByRid(rid);
    }
}
