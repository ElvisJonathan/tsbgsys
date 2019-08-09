package com.tsbg.ecosys.mapper;

import com.tsbg.ecosys.model.EperRole;
import org.apache.ibatis.annotations.Mapper;

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
    List<Integer> selectPidByRid(int rid);
}