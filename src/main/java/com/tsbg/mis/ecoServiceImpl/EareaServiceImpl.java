package com.tsbg.mis.ecoServiceImpl;

import com.tsbg.mis.ecoMapper.EareaMapper;
import com.tsbg.mis.ecoService.EareaService;
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
