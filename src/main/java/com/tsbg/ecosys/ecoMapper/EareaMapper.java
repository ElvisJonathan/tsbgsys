package com.tsbg.ecosys.ecoMapper;


import com.tsbg.ecosys.ecoModel.Earea;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EareaMapper {
    int deleteByPrimaryKey(Integer areaId);

    int insert(Earea record);

    int insertSelective(Earea record);

    Earea selectByPrimaryKey(Integer areaId);

    int updateByPrimaryKeySelective(Earea record);

    int updateByPrimaryKey(Earea record);

    //根据地区名查找对应的地区编号（特殊情况需要用厂区名来查找编号）
    Integer selectAreaNoByAreaName(String area);
}