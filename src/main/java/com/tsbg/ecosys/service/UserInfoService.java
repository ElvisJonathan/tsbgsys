package com.tsbg.ecosys.service;


import com.tsbg.ecosys.model.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserInfoService {

    // 注册成功时添加用户信息
    int insertSelective(UserInfo record);

    //根据用户名和密码来查询是否存在此用户
    int selectUserByPwd(@Param("userCode") String userCode, @Param("userPwd") String userPwd);

    //根据工号来查询此用户用户名
    String selectUserNameByUserCode(String userCode);

    //员工注册之前需要去用户表判断该工号是否已经注册
    int selectisExistUserCodeByStaffCode(String userCode);

    //根据用户工号查询对应的uid
    Integer selectuidbyuserCode(String userCode);

    //更新euser_area表的数据
    int insertDatatoEuserArea(@Param("uid") int uid,@Param("remark") String remark);

    //更新user_role表的数据
    int insertDatatoEuserRole(@Param("uid") int uid,@Param("rid") int rid,@Param("remark") String remark);

    //根据用户工号查询对应的uid和用户名
    UserInfo selectUidAndName(String userCode);

    //查询用户列表
    List<UserInfo> selectEuserList();

    //管理员停用和启用用户
    int setEcoUserByUserCode(@Param("status") int status,@Param("userCode") String userCode);

    //根据工号查询用户个人信息
    UserInfo selectUserMsgbyUserCode(String userCode);

    //根据工号修改个人信息
    int updateByUserCodeSelective(UserInfo userInfo);

    //修改密码
    int modifyPasswordByUsername(String userPwd,String userCode);

    UserInfo selectByUserCode(String userCode);

    //通过工号和密码来判断是否存在此用户
    int judgeIfExistUserByUserPwd(@Param("userCode") String userCode,@Param("userPwd") String userPwd);

    //管理员重置用户密码
    int reSetPwdByUserCode(@Param("userPwd")String userPwd,@Param("userCode")String userCode);

    //根据工号查询当前用户身份是否为管理员
    int selectIdentityByUserCode(String userCode);

    //通过工号查询用户状态
    int selectStatusByUserCode(String userCode);
}
