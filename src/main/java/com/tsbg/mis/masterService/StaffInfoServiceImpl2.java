package com.tsbg.mis.masterService;

import com.tsbg.mis.masterMapper.StaffInfoMapper2;
import com.tsbg.mis.masterModel.StaffInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffInfoServiceImpl2 implements StaffInfoService2 {

    @Autowired
    private StaffInfoMapper2 staffInfoMapper2;


    @Override
    public int selectisExistStaffCodeByuserCode(String userCode) {
        return staffInfoMapper2.selectisExistStaffCodeByuserCode(userCode);
    }

    @Override
    public StaffInfo selectStaffMsg(String userCode) {
        return staffInfoMapper2.selectStaffMsg(userCode);
    }

    @Override
    public String selectDepartCodeByuserCode(String userCode) {
        return staffInfoMapper2.selectDepartCodeByuserCode(userCode);
    }

    @Override
    public String selectlocationByUserCode(String userCode) {
        return staffInfoMapper2.selectlocationByUserCode(userCode);
    }

    @Override
    public String selectDepartmentByUserCode(String userCode) {
        return staffInfoMapper2.selectDepartmentByUserCode(userCode);
    }
}
