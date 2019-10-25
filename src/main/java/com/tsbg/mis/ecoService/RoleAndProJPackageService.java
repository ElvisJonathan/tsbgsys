package com.tsbg.mis.ecoService;

import com.tsbg.mis.ecoModel.bag.RoleAndProJPackage;

import java.util.List;

public interface RoleAndProJPackageService {

    List<RoleAndProJPackage> selectRoleAndProj();

    //配置映射user_role
    List<RoleAndProJPackage> selectRoleAndProj2();
}
