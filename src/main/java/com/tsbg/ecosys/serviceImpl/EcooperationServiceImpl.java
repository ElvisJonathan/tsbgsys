package com.tsbg.ecosys.serviceImpl;

import com.tsbg.ecosys.mapper.EcooperationMapper;
import com.tsbg.ecosys.model.Ecooperation;
import com.tsbg.ecosys.service.EcooperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EcooperationServiceImpl implements EcooperationService {

    @Autowired
    private EcooperationMapper ecooperationMapper;

    @Override
    public int insertSelective(Ecooperation record) {
        return ecooperationMapper.insertSelective(record);
    }

    @Override
    public List<Ecooperation> selectCooinfo(Integer partnerNo) {

        return ecooperationMapper.selectCooinfo(partnerNo);
    }

    @Override
    public int updateByPartnerNoSelective(Ecooperation record) {
        return ecooperationMapper.updateByPartnerNoSelective(record);
    }

    @Override
    public int updateStatusByCid(int partnerNo) {
        return ecooperationMapper.updateStatusByCid(partnerNo);
    }
}
