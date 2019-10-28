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

    @Override
    public int selectUserByPwd(String userCode, String userPwd) {
        return userInfoMapper2.selectUserByPwd(userCode,userPwd);
    }

    @Override
    public String selectUserNameByUserCode(String userCode) {
        return userInfoMapper2.selectUserNameByUserCode(userCode);
    }

    @Override
    public int insertDatatoEuserArea(int uid, int aid, String remark) {
        return userInfoMapper2.insertDatatoEuserArea(uid,aid,remark);
    }

    @Override
    public int insertDatatoEuserRole(int uid, int rid, String remark) {
        return userInfoMapper2.insertDatatoEuserRole(uid,rid,remark);
    }

    @Override
    public UserInfo selectUidAndName(String userCode) {
        return userInfoMapper2.selectUidAndName(userCode);
    }

    @Override
    public int setEcoUserByUserCode(int status, String userCode) {
        return userInfoMapper2.setEcoUserByUserCode(status,userCode);
    }

    @Override
    public UserInfo selectUserMsgbyUserCode(String userCode) {
        return userInfoMapper2.selectUserMsgbyUserCode(userCode);
    }

    @Override
    public int updateByUserCodeSelective(UserInfo userInfo) {
        return userInfoMapper2.updateByUserCodeSelective(userInfo);
    }

    @Override
    public int modifyPasswordByUsername(String userPwd, String userCode) {
        return userInfoMapper2.modifyPasswordByUsername(userPwd,userCode);
    }

    @Override
    public UserInfo selectByUserCode(String userCode) {
        return userInfoMapper2.selectByUserCode(userCode);
    }

    @Override
    public int judgeIfExistUserByUserPwd(String userCode, String userPwd) {
        return userInfoMapper2.judgeIfExistUserByUserPwd(userCode,userPwd);
    }

    @Override
    public int reSetPwdByUserCode(String userPwd, String userCode) {
        return userInfoMapper2.reSetPwdByUserCode(userPwd,userCode);
    }

    @Override
    public int selectIdentityByUserCode(String userCode) {
        return userInfoMapper2.selectIdentityByUserCode(userCode);
    }

    @Override
    public int resetUserSalt(String salt, String userCode) {
        return userInfoMapper2.resetUserSalt(salt,userCode);
    }

    @Override
    public int modifyRoleListByuserId(String rolelist, Integer userId) {
        return userInfoMapper2.modifyRoleListByuserId(rolelist,userId);
    }

    @Override
    public String selectPowerByUserCode(String userCode) {
        return null;
    }

    @Override
    public String selectEmailByUserCode(String userCode) {
        return userInfoMapper2.selectEmailByUserCode(userCode);
    }

    @Override
    public byte selectLockedByUserCode(String userCode) {
        return userInfoMapper2.selectLockedByUserCode(userCode);
    }

    @Override
    public int selectIfExistThisUser(Integer userId) {
        return userInfoMapper2.selectIfExistThisUser(userId);
    }

    @Override
    public String selectPwdByUserCode(String userCode) {
        return userInfoMapper2.selectPwdByUserCode(userCode);
    }

    @Override
    public UserInfo selectFeedbackUserByUserCode(String userCode) {
        return userInfoMapper2.selectFeedbackUserByUserCode(userCode);
    }

    @Override
    public UserInfo selectHandleUserByUserCode(String userCode) {
        return userInfoMapper2.selectHandleUserByUserCode(userCode);
    }

    @Override
    public int updateFeedbackUserByUserCode(UserInfo userInfo) {
        return userInfoMapper2.updateFeedbackUserByUserCode(userInfo);
    }

    @Override
    public int updateHandleUserByUserCode(UserInfo userInfo) {
        return userInfoMapper2.updateHandleUserByUserCode(userInfo);
    }
}
