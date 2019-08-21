package com.tsbg.ecosys.service;

import com.tsbg.ecosys.model.Ecooperation;

import java.util.List;

public interface EcooperationService {

    int insertSelective(Ecooperation record);

    List<Ecooperation> selectCooinfo(Integer partnerNo);

    //用合作伙伴编号进行修改
    int updateByPartnerNoSelective(Ecooperation record);

    //管理员删除公司（软删除）
    int updateStatusByCid(int partnerNo);
}
