package com.tsbg.ecosys.mapper;

import com.tsbg.ecosys.model.PermRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PermRoleMapper {

    int deleteByPrimaryKey(Integer permroleId);

    int insert(PermRole record);

    int insertSelective(PermRole record);

    PermRole selectByPrimaryKey(Integer permroleId);

    int updateByPrimaryKeySelective(PermRole record);

    int updateByPrimaryKey(PermRole record);

    //通过角色rid查询对应的权限id
    List<Integer> selectPidByRid(Integer rid);

    //通过角色rid去查询对应的prid
    List<Integer> selectPridByRid(Integer rid);

    //通过prid去修改pid
    int updatePowerByPrid(@Param("pid") Object pid,@Param("prid") Object prid);
}