package com.tsbg.ecosys.mapper;


import com.tsbg.ecosys.model.Ecooperation;
import com.tsbg.ecosys.model.example.EcooperationExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EcooperationMapper {
    int deleteByPrimaryKey(Integer coopId);

    int insert(Ecooperation record);

    int insertSelective(Ecooperation record);

    Ecooperation selectByPrimaryKey(Integer coopId);

    int updateByPrimaryKeySelective(Ecooperation record);

    int updateByPrimaryKey(Ecooperation record);

    List<Ecooperation> selectCooinfo(Integer partnerNo);

    List<Ecooperation> selectByExample(EcooperationExample example);

    long countByExample(EcooperationExample example);

    int deleteByExample(EcooperationExample example);

    int updateByExampleSelective(@Param("record") Ecooperation record, @Param("example") EcooperationExample example);

    int updateByExample(@Param("record") Ecooperation record, @Param("example") EcooperationExample example);

    List<Ecooperation> selectEcooperationByCid(Integer cid);
    //用合作伙伴编号进行修改
    int updateByPartnerNoSelective(Ecooperation record);
    //管理员删除公司（软删除）
    int updateStatusByCid(int partnerNo);

    List<Ecooperation> selectByPartnerNo(Integer partnerNo);
}