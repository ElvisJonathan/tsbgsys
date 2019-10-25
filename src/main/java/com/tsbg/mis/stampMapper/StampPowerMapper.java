package com.tsbg.mis.stampMapper;

import com.tsbg.mis.stampVo.StampPowerVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StampPowerMapper {

    //根据工号查询员工姓名和部门
    StampPowerVo selectNameAndDePartByUserCode(String userCode);

    //根据用户编号和项目编号查询角色
    Integer selectRoleByUidAndProJId(Integer uid);

    //根据工号查询用户编号
    Integer selectUserIdByUserCode(String userCode);

    //根据用户编号和项目编号修改用户角色
    int updateStampRoleByUidAndProjID(Integer roleId,Integer uid);

    //查询用印群组列表
    List<StampPowerVo> selectPowerList();
}
