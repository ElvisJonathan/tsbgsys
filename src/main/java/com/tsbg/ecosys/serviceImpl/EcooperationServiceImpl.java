package com.tsbg.ecosys.serviceImpl;

import com.tsbg.ecosys.mapper.EcooperationMapper;
import com.tsbg.ecosys.model.Ecooperation;
import com.tsbg.ecosys.service.EcooperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EcooperationServiceImpl implements EcooperationService {

    @Autowired
    private EcooperationMapper ecooperationMapper;

    @Override
    public int insertSelective(Ecooperation record) {
        return ecooperationMapper.insertSelective(record);
    }
}
