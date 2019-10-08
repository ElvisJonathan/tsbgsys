package com.tsbg.ecosys.mapper;

import com.tsbg.ecosys.model.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRoleMapper {

    int deleteByPrimaryKey(Integer uroleId);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Integer uroleId);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

    //根据用户工号去数据库查询是否为生态员工
    UserRole selectEuserInfo(String userCode);

    //通过uid查询用户对应的角色rid
    Integer selectRidByUid(Integer uid);

    //根据用户的userId查询出其所拥有的角色插入到字段role_list
    List<Integer> getRole(Integer uid);
}