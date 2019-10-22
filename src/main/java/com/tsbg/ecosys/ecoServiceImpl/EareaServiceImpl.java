package com.tsbg.ecosys.ecoServiceImpl;

import com.tsbg.ecosys.ecoMapper.EareaMapper;
import com.tsbg.ecosys.ecoService.EareaService;
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
