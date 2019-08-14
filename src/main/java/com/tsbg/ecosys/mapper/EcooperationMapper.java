package com.tsbg.ecosys.mapper;


import com.tsbg.ecosys.model.Ecooperation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EcooperationMapper {
    int deleteByPrimaryKey(Integer coid);

    int insert(Ecooperation record);

    int insertSelective(Ecooperation record);

    Ecooperation selectByPrimaryKey(Integer coid);

    int updateByPrimaryKeySelective(Ecooperation record);

    int updateByPrimaryKey(Ecooperation record);

    List<Ecooperation> selectCooinfo(Integer partnerNo);
}