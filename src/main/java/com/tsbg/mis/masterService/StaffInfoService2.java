package com.tsbg.mis.masterService;

import com.tsbg.mis.masterModel.StaffInfo;

public interface StaffInfoService2 {

    //根据工号判断注册时输入的工号是否存在于员工表
    int selectisExistStaffCodeByuserCode(String userCode);

    //根据工号查询注册时员工其他信息
    StaffInfo selectStaffMsg(String userCode);
}
