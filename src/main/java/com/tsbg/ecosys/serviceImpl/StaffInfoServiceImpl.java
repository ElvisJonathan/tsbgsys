package com.tsbg.ecosys.serviceImpl;

import com.tsbg.ecosys.mapper.StaffInfoMapper;
import com.tsbg.ecosys.service.StaffInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffInfoServiceImpl implements StaffInfoService {

    @Autowired
    private StaffInfoMapper staffInfoMapper;
    @Override
    public int selectisExistStaffCodeByuserCode(String userCode) {
        return staffInfoMapper.selectisExistStaffCodeByuserCode(userCode);
    }

    @Override
    public String selectDepartCodeByuserCode(String userCode) {
        return staffInfoMapper.selectDepartCodeByuserCode(userCode);
    }

    @Override
    public String selectlocationByUserCode(String userCode) {
        return staffInfoMapper.selectlocationByUserCode(userCode);
    }

    //根據工號查詢員工的部門
    @Override
    public String selectDepartmentByUserCode(String userCode) {
        return staffInfoMapper.selectDepartmentByUserCode(userCode);
    }
}
