package com.tsbg.ecosys.mapper;

import com.tsbg.ecosys.model.StaffInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StaffInfoMapper {

    int deleteByPrimaryKey(Integer staffId);

    int insert(StaffInfo record);

    int insertSelective(StaffInfo record);

    StaffInfo selectByPrimaryKey(Integer staffId);

    int updateByPrimaryKeySelective(StaffInfo record);

    int updateByPrimaryKey(StaffInfo record);

    //根据工号判断注册时输入的工号是否存在于员工表
    int selectisExistStaffCodeByuserCode(String userCode);

    //根据用户输入的工号查询部门代码
    String selectDepartCodeByuserCode(String userCode);

    //根据工号查询当前员工的厂区
    String selectlocationByUserCode(String userCode);
}