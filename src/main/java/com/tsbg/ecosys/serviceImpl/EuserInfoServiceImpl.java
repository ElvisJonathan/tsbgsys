package com.tsbg.ecosys.serviceImpl;

import com.tsbg.ecosys.mapper.EuserInfoMapper;
import com.tsbg.ecosys.model.EuserInfo;
import com.tsbg.ecosys.service.EuserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public int selectuidbyuserCode(String userCode) {
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
}
