package com.tsbg.mis.powerService;

import com.tsbg.mis.powerModel.UserRole;
import com.tsbg.mis.powerModel.powerBag.RoleAndProJPackage;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface UserRoleService2 {

    //插入数据到user_role
    int insertData(@Param("uid")Integer userId, @Param("rid")Integer roleId, @Param("createCode")String createCode, @Param("createDate") Date date,@Param("projId")Integer projId);

    //查询当前登录用户所拥有的项目情况
    List<RoleAndProJPackage> selectProJMsgByUid(Integer uid);

    //根据用户工号去数据库查询是否为生态员工
    UserRole selectEuserInfo(String userCode);

    //通过uid查询用户对应的角色rid
    Integer selectRidByUid(Integer uid);

    //根据用户的userId查询出其所拥有的角色插入到字段role_list
    List<Integer> getRole(Integer uid);

    //修改用户的角色
    int updateUserRoleByProAndRoleId(@Param("rid")Integer roleId,@Param("uid")Integer userId,@Param("projId")Integer projId);

}
