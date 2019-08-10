package com.tsbg.ecosys.mapper;


import com.tsbg.ecosys.model.EuserRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EuserRoleMapper {
    int deleteByPrimaryKey(Integer urid);

    int insert(EuserRole record);

    int insertSelective(EuserRole record);

    EuserRole selectByPrimaryKey(Integer urid);

    int updateByPrimaryKeySelective(EuserRole record);

    int updateByPrimaryKey(EuserRole record);

    //根据用户工号去数据库查询是否为生态员工
    EuserRole selectEuserInfo(String userCode);

    //通过uid查询用户对应的角色rid
    Integer selectRidByUid(Integer uid);
}