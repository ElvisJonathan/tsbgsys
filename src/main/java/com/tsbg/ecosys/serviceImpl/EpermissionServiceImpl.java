package com.tsbg.ecosys.serviceImpl;

import com.tsbg.ecosys.mapper.EpermissionMapper;
import com.tsbg.ecosys.service.EpermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EpermissionServiceImpl implements EpermissionService {

    @Autowired
    private EpermissionMapper epermissionMapper;

    @Override
    public List<String> selectPowerDetailByPid(List<Integer> pid) {
        return epermissionMapper.selectPowerDetailByPid(pid);
    }
}
