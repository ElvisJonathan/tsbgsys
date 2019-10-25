package com.tsbg.mis.ecoServiceImpl;

import com.tsbg.mis.ecoMapper.UserInfoMapper;
import com.tsbg.mis.ecoModel.UserInfo;
import com.tsbg.mis.ecoService.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public int insertSelective(UserInfo record) {
        return userInfoMapper.insertSelective(record);
    }

    @Override
    public int selectUserByPwd(String userCode, String userPwd) {
        int count = userInfoMapper.selectUserByPwd(userCode, userPwd);
        return count;
    }

    @Override
    public String selectUserNameByUserCode(String userCode) {
        return userInfoMapper.selectUserNameByUserCode(userCode);
    }

    @Override
    public int selectisExistUserCodeByStaffCode(String userCode) {
        return userInfoMapper.selectisExistUserCodeByStaffCode(userCode);
    }

    @Override
    public Integer selectuidbyuserCode(String userCode) {
        return userInfoMapper.selectuidbyuserCode(userCode);
    }

    @Override
    public int insertDatatoEuserArea(int uid,int aid, String remark) {
        return userInfoMapper.insertDatatoEuserArea(uid,aid,remark);
    }

    @Override
    public int insertDatatoEuserRole(int uid, int rid, String remark) {
        return userInfoMapper.insertDatatoEuserRole(uid,rid,remark);
    }

    @Override
    public UserInfo selectUidAndName(String userCode) {
        return userInfoMapper.selectUidAndName(userCode);
    }

    @Override
    public List<UserInfo> selectEuserList() {
        return userInfoMapper.selectEuserList();
    }

    @Override
    public int setEcoUserByUserCode(int status, String userCode) {
        return userInfoMapper.setEcoUserByUserCode(status,userCode);
    }

    @Override
    public UserInfo selectUserMsgbyUserCode(String userCode) {
        return userInfoMapper.selectUserMsgbyUserCode(userCode);
    }

    @Override
    public int updateByUserCodeSelective(UserInfo userInfo) {
        return userInfoMapper.updateByUserCodeSelective(userInfo);
    }

    @Override
    public int modifyPasswordByUsername(String userPwd, String userCode) {
        return userInfoMapper.modifyPasswordByUsername(userPwd,userCode);
    }

    @Override
    public UserInfo selectByUserCode(String userCode) {
        return userInfoMapper.selectByUserCode(userCode);
    }

    @Override
    public int judgeIfExistUserByUserPwd(String userCode, String userPwd) {
        return userInfoMapper.judgeIfExistUserByUserPwd(userCode,userPwd);
    }

    @Override
    public int reSetPwdByUserCode(String userPwd, String userCode) {
        return userInfoMapper.reSetPwdByUserCode(userPwd,userCode);
    }

    @Override
    public int selectIdentityByUserCode(String userCode) {
        return userInfoMapper.selectIdentityByUserCode(userCode);
    }

    @Override
    public Integer selectStatusByUserCode(String userCode) {
        return userInfoMapper.selectStatusByUserCode(userCode);
    }

    @Override
    public Integer selectCountByUserCode(String userCode) {
        return userInfoMapper.selectCountByUserCode(userCode);
    }

    @Override
    public String selectSaltByUserCode(String userCode) {
        return userInfoMapper.selectSaltByUserCode(userCode);
    }

    @Override
    public int resetUserSalt(String salt, String userCode) {
        return userInfoMapper.resetUserSalt(salt,userCode);
    }

    @Override
    public int modifyRoleListByuserId(String rolelist, Integer userId) {
        return userInfoMapper.modifyRoleListByuserId(rolelist,userId);
    }

    @Override
    public int modifyPermListByuserId(String permlist, Integer userId) {
        return userInfoMapper.modifyPermListByuserId(permlist,userId);
    }

    @Override
    public String selectPowerByUserCode(String userCode) {
        return userInfoMapper.selectPowerByUserCode(userCode);
    }

    @Override
    public String selectEmailByUserCode(String userCode) {
        return userInfoMapper.selectEmailByUserCode(userCode);
    }

    //根據工號查詢郵箱，用於忘記密碼，驗證
    public byte selectLockedByUserCode(String userCode){
        return userInfoMapper.selectLockedByUserCode(userCode);
    }

    @Override
    public int selectIfExistThisUser(Integer userId) {
        return userInfoMapper.selectIfExistThisUser(userId);
    }

    @Override
    public String selectPwdByUserCode(String userCode) {
        return userInfoMapper.selectPwdByUserCode(userCode);
    }

    //問題反饋根據工號查詢反饋者的相關信息
    @Override
    public UserInfo selectFeedbackUserByUserCode(String userCode) {
        return userInfoMapper.selectFeedbackUserByUserCode(userCode);
    }

    //處理問題反饋根據工號查詢處理者的相關信息
    @Override
    public UserInfo selectHandleUserByUserCode(String userCode) {
        return userInfoMapper.selectHandleUserByUserCode(userCode);
    }

    //問題反饋根據工號修改反饋者的相關信息
    @Override
    public int updateFeedbackUserByUserCode(UserInfo userInfo) {
        return userInfoMapper.updateFeedbackUserByUserCode(userInfo);
    }

    //處理問題反饋根據工號修改處理者的相關信息
    @Override
    public int updateHandleUserByUserCode(UserInfo userInfo) {
        return userInfoMapper.updateHandleUserByUserCode(userInfo);
    }
}
