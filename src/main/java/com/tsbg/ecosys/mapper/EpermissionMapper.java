package com.tsbg.ecosys.mapper;


import com.tsbg.ecosys.model.Epermission;
import com.tsbg.ecosys.model.example.EpermissionExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EpermissionMapper {
    int deleteByPrimaryKey(Integer pid);

    int insert(Epermission record);

    int insertSelective(Epermission record);

    Epermission selectByPrimaryKey(Integer pid);

    int updateByPrimaryKeySelective(Epermission record);

    int updateByPrimaryKey(Epermission record);

    //根据pid查询对应权限详情
    List<String> selectPowerDetailByPid(List<Integer> pid);

    long countByExample(EpermissionExample example);

    int deleteByExample(EpermissionExample example);

    List<Epermission> selectByExample(EpermissionExample example);

    int updateByExampleSelective(@Param("record") Epermission record, @Param("example") EpermissionExample example);

    int updateByExample(@Param("record") Epermission record, @Param("example") EpermissionExample example);
}