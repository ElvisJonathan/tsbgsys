package com.tsbg.ecosys.serviceImpl;

import com.tsbg.ecosys.mapper.EareaMapper;
import com.tsbg.ecosys.service.EareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EareaServiceImpl implements EareaService{

    @Autowired
    private EareaMapper eareaMapper;


    @Override
    public Integer selectAreaNoByAreaName(String area) {
        return eareaMapper.selectAreaNoByAreaName(area);
    }
}
