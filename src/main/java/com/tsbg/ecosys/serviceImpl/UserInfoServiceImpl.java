package com.tsbg.ecosys.serviceImpl;

import com.tsbg.ecosys.mapper.UserInfoMapper;
import com.tsbg.ecosys.model.UserInfo;
import com.tsbg.ecosys.service.UserInfoService;
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
    public int insertDatatoEuserArea(int uid, String remark) {
        return userInfoMapper.insertDatatoEuserArea(uid,remark);
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
}