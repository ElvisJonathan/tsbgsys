package com.tsbg.ecosys.mapper;

import com.tsbg.ecosys.model.EuserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EuserInfoMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(EuserInfo record);

    int insertSelective(EuserInfo record);

    EuserInfo selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(EuserInfo record);

    int updateByPrimaryKey(EuserInfo record);

    //根据工号和密码来查询是否存在此用户
    int selectUserByPwd(@Param("userCode") String userCode, @Param("userPwd") String userPwd);

    //根据工号来查询此用户用户名
    String selectUserNameByUserCode(String userCode);

    //员工注册之前需要去用户表判断该工号是否已经注册
    int selectisExistUserCodeByStaffCode(String userCode);

    //根据用户工号查询对应的uid
    Integer selectuidbyuserCode(String userCode);

    //更新euser_area表的数据
    int insertDatatoEuserArea(@Param("uid") int uid,@Param("aid") int aid,@Param("remark") String remark);

    //更新euser_role表的数据
    int insertDatatoEuserRole(@Param("uid") int uid,@Param("rid") int rid,@Param("remark") String remark);

    //根据用户工号查询对应的uid和用户名
    EuserInfo selectUidAndName(String userCode);

    //查询用户列表
    List<EuserInfo> selectEuserList();

    //管理员停用和启用用户
    int setEcoUserByUserCode(@Param("status") int status,@Param("userCode") String userCode);

    //根据工号查询用户个人信息
    EuserInfo selectUserMsgbyUserCode(String userCode);

    //根据工号修改个人信息
    int updateByUserCodeSelective(EuserInfo euserInfo);
}