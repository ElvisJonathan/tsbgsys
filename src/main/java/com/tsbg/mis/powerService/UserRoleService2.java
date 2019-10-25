package com.tsbg.mis.powerService;

import com.tsbg.mis.powerModel.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface UserRoleService2 {

    //插入数据到user_role
    int insertData(@Param("uid")Integer userId, @Param("rid")Integer roleId, @Param("createCode")String createCode, @Param("createDate") Date date,@Param("projId")Integer projId);

    //查询当前登录用户所拥有的项目情况
    List<UserRole> selectProJMsgByUid(Integer uid);
}
