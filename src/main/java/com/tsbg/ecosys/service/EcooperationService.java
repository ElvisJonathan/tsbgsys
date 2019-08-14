package com.tsbg.ecosys.service;

import com.tsbg.ecosys.model.Ecooperation;

import java.util.List;

public interface EcooperationService {

    int insertSelective(Ecooperation record);

    List<Ecooperation> selectCooinfo(Integer partnerNo);
}
