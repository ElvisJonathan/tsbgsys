package com.tsbg.mis.powerService;

import com.tsbg.mis.powerMapper.RoleAndProJPackageMapper2;
import com.tsbg.mis.powerModel.powerBag.RoleAndProJPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoleAndProJPackageServiceImpl2 implements RoleAndProJPackageService2 {

    @Autowired
    private RoleAndProJPackageMapper2 roleAndProJPackageMapper2;


    @Override
    public List<RoleAndProJPackage> selectRoleAndProj() {
        return roleAndProJPackageMapper2.selectRoleAndProj();
    }

    @Override
    public List<RoleAndProJPackage> selectRoleAndProj2() {
        return roleAndProJPackageMapper2.selectRoleAndProj2();
    }
}
