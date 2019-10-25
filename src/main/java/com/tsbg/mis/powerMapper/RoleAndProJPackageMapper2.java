package com.tsbg.mis.powerMapper;


import com.tsbg.mis.powerModel.powerBag.RoleAndProJPackage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface RoleAndProJPackageMapper2 {

    List<RoleAndProJPackage> selectRoleAndProj();

    //配置映射user_role
    List<RoleAndProJPackage> selectRoleAndProj2();
}
