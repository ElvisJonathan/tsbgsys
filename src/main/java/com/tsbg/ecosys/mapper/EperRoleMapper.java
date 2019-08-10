package com.tsbg.ecosys.mapper;

import com.tsbg.ecosys.model.EperRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EperRoleMapper {
    int deleteByPrimaryKey(Integer prid);

    int insert(EperRole record);

    int insertSelective(EperRole record);

    EperRole selectByPrimaryKey(Integer prid);

    int updateByPrimaryKeySelective(EperRole record);

    int updateByPrimaryKey(EperRole record);

    //通过角色rid查询对应的权限id
    List<Integer> selectPidByRid(Integer rid);

    //通过角色rid去查询对应的prid
    List<Integer> selectPridByRid(Integer rid);

    //通过prid去修改pid
    int updatePowerByPrid(@Param("pid") Object pid,@Param("prid") Object prid);
}