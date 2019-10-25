package com.tsbg.mis.powerService;

import com.tsbg.mis.powerModel.UserInfo;
import org.apache.ibatis.annotations.Param;

public interface UserInfoService2 {

    //员工注册之前需要去用户表判断该工号是否已经注册
    int selectisExistUserCodeByStaffCode(String userCode);

    int insertSelective(UserInfo record);

    //根据用户工号查询对应的uid
    Integer selectuidbyuserCode(String userCode);

    //根据user_id修改用户的perm_list
    int modifyPermListByuserId(@Param("permlist")String permlist, @Param("uid")Integer userId);

    //根据工号查询是否存在该用户
    Integer selectCountByUserCode(String userCode);

    //通过工号查询用户状态
    Integer selectStatusByUserCode(String userCode);

    //根据工号查询密码盐
    String selectSaltByUserCode(String userCode);
}
