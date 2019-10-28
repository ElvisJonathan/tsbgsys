package com.tsbg.mis.powerMapper;

import com.tsbg.mis.powerModel.UserRole;
import com.tsbg.mis.powerModel.powerBag.RoleAndProJPackage;
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
    List<RoleAndProJPackage> selectProJMsgByUid(Integer uid);

    //根据用户编号、角色编号和项目编号查找条数
    int selectRoleCountByCondition(@Param("uid")Integer uid,@Param("roleId")Integer roleId,@Param("projId")Integer projId);

    //根据用户编号查询项目编号
    List<Integer> selectProJIdByUserId(Integer uid);

    //根据用户工号去数据库查询是否为生态员工
    UserRole selectEuserInfo(String userCode);

    //通过uid查询用户对应的角色rid
    Integer selectRidByUid(Integer uid);

    //根据用户的userId查询出其所拥有的角色插入到字段role_list
    List<Integer> getRole(Integer uid);

    //修改用户的角色
    int updateUserRoleByProAndRoleId(@Param("rid")Integer roleId,@Param("uid")Integer userId,@Param("projId")Integer projId);
}
