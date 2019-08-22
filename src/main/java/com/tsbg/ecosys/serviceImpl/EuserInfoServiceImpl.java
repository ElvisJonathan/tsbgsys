package com.tsbg.ecosys.serviceImpl;

import com.tsbg.ecosys.mapper.EuserInfoMapper;
import com.tsbg.ecosys.model.EuserInfo;
import com.tsbg.ecosys.service.EuserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EuserInfoServiceImpl implements EuserInfoService {

    @Autowired
    private EuserInfoMapper euserInfoMapper;

    @Override
    public int insertSelective(EuserInfo record) {
        return euserInfoMapper.insertSelective(record);
    }

    @Override
    public int selectUserByPwd(String userCode, String userPwd) {
        int count = euserInfoMapper.selectUserByPwd(userCode, userPwd);
        return count;
    }

    @Override
    public String selectUserNameByUserCode(String userCode) {
        return euserInfoMapper.selectUserNameByUserCode(userCode);
    }

    @Override
    public int selectisExistUserCodeByStaffCode(String userCode) {
        return euserInfoMapper.selectisExistUserCodeByStaffCode(userCode);
    }

    @Override
    public Integer selectuidbyuserCode(String userCode) {
        return euserInfoMapper.selectuidbyuserCode(userCode);
    }

    @Override
    public int insertDatatoEuserArea(int uid, int aid, String remark) {
        return euserInfoMapper.insertDatatoEuserArea(uid,aid,remark);
    }

    @Override
    public int insertDatatoEuserRole(int uid, int rid, String remark) {
        return euserInfoMapper.insertDatatoEuserRole(uid,rid,remark);
    }

    @Override
    public EuserInfo selectUidAndName(String userCode) {
        return euserInfoMapper.selectUidAndName(userCode);
    }

    @Override
    public List<EuserInfo> selectEuserList() {
        return euserInfoMapper.selectEuserList();
    }

    @Override
    public int setEcoUserByUserCode(int status, String userCode) {
        return euserInfoMapper.setEcoUserByUserCode(status,userCode);
    }

    @Override
    public EuserInfo selectUserMsgbyUserCode(String userCode) {
        return euserInfoMapper.selectUserMsgbyUserCode(userCode);
    }

    @Override
    public int updateByUserCodeSelective(EuserInfo euserInfo) {
        return euserInfoMapper.updateByUserCodeSelective(euserInfo);
    }

    @Override
    public int modifyPasswordByUsername(String userPwd, String userCode) {
        return euserInfoMapper.modifyPasswordByUsername(userPwd,userCode);
    }

    @Override
    public EuserInfo selectByUserCode(String userCode) {
        return euserInfoMapper.selectByUserCode(userCode);
    }

    @Override
    public int judgeIfExistUserByUserPwd(String userCode, String userPwd) {
        return euserInfoMapper.judgeIfExistUserByUserPwd(userCode,userPwd);
    }

    @Override
    public int reSetPwdByUserCode(String userPwd, String userCode) {
        return euserInfoMapper.reSetPwdByUserCode(userPwd,userCode);
    }
}
