package com.tsbg.mis.powerService;

import com.tsbg.mis.powerMapper.UserInfoMapper2;
import com.tsbg.mis.powerModel.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl2 implements UserInfoService2 {

    @Autowired
    private UserInfoMapper2 userInfoMapper2;

    @Override
    public int selectisExistUserCodeByStaffCode(String userCode) {
        return userInfoMapper2.selectisExistUserCodeByStaffCode(userCode);
    }

    @Override
    public int insertSelective(UserInfo record) {
        return userInfoMapper2.insertSelective(record);
    }

    @Override
    public Integer selectuidbyuserCode(String userCode) {
        return userInfoMapper2.selectuidbyuserCode(userCode);
    }

    @Override
    public int modifyPermListByuserId(String permlist, Integer userId) {
        return userInfoMapper2.modifyPermListByuserId(permlist,userId);
    }

    @Override
    public Integer selectCountByUserCode(String userCode) {
        return userInfoMapper2.selectCountByUserCode(userCode);
    }

    @Override
    public Integer selectStatusByUserCode(String userCode) {
        return userInfoMapper2.selectStatusByUserCode(userCode);
    }

    @Override
    public String selectSaltByUserCode(String userCode) {
        return userInfoMapper2.selectSaltByUserCode(userCode);
    }
}
