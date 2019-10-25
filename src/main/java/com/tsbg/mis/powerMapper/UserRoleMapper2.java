package com.tsbg.mis.powerMapper;

import com.tsbg.mis.powerModel.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface UserRoleMapper2 {
    int deleteByPrimaryKey(Integer uroleId);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Integer uroleId);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

    //插入数据到user_role
    int insertData(@Param("uid")Integer userId, @Param("rid")Integer roleId, @Param("createCode")String createCode, @Param("createDate")Date date, @Param("projId")Integer projId);

    //查询当前登录用户所拥有的项目情况
    List<UserRole> selectProJMsgByUid(Integer uid);

    //根据用户编号、角色编号和项目编号查找条数
    int selectRoleCountByCondition(@Param("uid")Integer uid,@Param("roleId")Integer roleId,@Param("projId")Integer projId);

    //根据用户编号查询项目编号
    List<Integer> selectProJIdByUserId(Integer uid);
}