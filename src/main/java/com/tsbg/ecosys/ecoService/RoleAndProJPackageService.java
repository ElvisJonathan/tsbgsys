package com.tsbg.ecosys.ecoService;

import com.tsbg.ecosys.ecoModel.bag.RoleAndProJPackage;

import java.util.List;

public interface RoleAndProJPackageService {

    List<RoleAndProJPackage> selectRoleAndProj();

    //配置映射user_role
    List<RoleAndProJPackage> selectRoleAndProj2();
}
