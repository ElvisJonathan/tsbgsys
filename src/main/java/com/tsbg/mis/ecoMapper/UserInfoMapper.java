package com.tsbg.mis.ecoMapper;

import com.tsbg.mis.ecoModel.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserInfoMapper {

    int deleteByPrimaryKey(Integer userId);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

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
    int modifyPasswordByUsername(@Param("userPwd") String userPwd, @Param("userCode") String userCode);

    UserInfo selectByUserCode(@Param("userCode") String userCode);

    //通过工号和密码来判断是否存在此用户
    int judgeIfExistUserByUserPwd(@Param("userCode") String userCode,@Param("userPwd") String userPwd);

    //管理员重置用户密码
    int reSetPwdByUserCode(@Param("userPwd")String userPwd,@Param("userCode")String userCode);

    //根据工号查询当前用户身份是否为管理员
    int selectIdentityByUserCode(String userCode);

    //通过工号查询用户状态
    Integer selectStatusByUserCode(String userCode);

    //根据工号查询是否存在该用户
    Integer selectCountByUserCode(String userCode);

    //根据工号查询密码盐
    String selectSaltByUserCode(String userCode);

    //重置用户密码盐
    int resetUserSalt(@Param("salt")String salt,@Param("userCode")String userCode);

    //根据user_id修改用户的role_list
    int modifyRoleListByuserId(@Param("rolelist")String rolelist,@Param("uid")Integer userId);

    //根据user_id修改用户的perm_list
    int modifyPermListByuserId(@Param("permlist")String permlist,@Param("uid")Integer userId);

    //根据工号查询权限列表
    String selectPowerByUserCode(String userCode);

    //根據工號查詢郵箱，用於忘記密碼，驗證
    String selectEmailByUserCode(String userCode);

    //根據工號查詢locked字段是否重置過密碼
    byte selectLockedByUserCode(String userCode);

    //根据userId查询是否存在该用户
    int selectIfExistThisUser(Integer userId);

    //根据工号查询密码
    String selectPwdByUserCode(String userCode);

    //問題反饋根據工號查詢反饋者的相關信息
    UserInfo selectFeedbackUserByUserCode(String userCode);

    //處理問題反饋根據工號查詢處理者的相關信息
    UserInfo selectHandleUserByUserCode(String userCode);

    //問題反饋根據工號修改反饋者的相關信息
    int updateFeedbackUserByUserCode(UserInfo userInfo);

    //處理問題反饋根據工號修改處理者的相關信息
    int updateHandleUserByUserCode(UserInfo userInfo);
}