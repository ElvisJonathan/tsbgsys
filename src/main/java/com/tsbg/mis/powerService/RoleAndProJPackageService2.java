package com.tsbg.mis.powerService;

import com.tsbg.mis.powerModel.powerBag.RoleAndProJPackage;

import java.util.List;

public interface RoleAndProJPackageService2 {

    List<RoleAndProJPackage> selectRoleAndProj();

    //配置映射user_role
    List<RoleAndProJPackage> selectRoleAndProj2();
}
