package com.tsbg.ecosys.service;

import com.tsbg.ecosys.model.bag.RoleAndProJPackage;

import java.util.List;

public interface RoleAndProJPackageService {

    List<RoleAndProJPackage> selectRoleAndProj();

    //配置映射user_role
    List<RoleAndProJPackage> selectRoleAndProj2();
}
