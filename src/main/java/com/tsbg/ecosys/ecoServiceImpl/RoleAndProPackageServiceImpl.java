package com.tsbg.ecosys.ecoServiceImpl;

import com.tsbg.ecosys.ecoMapper.RoleAndProJPackageMapper;
import com.tsbg.ecosys.ecoModel.bag.RoleAndProJPackage;
import com.tsbg.ecosys.ecoService.RoleAndProJPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleAndProPackageServiceImpl implements RoleAndProJPackageService {

    @Autowired
    private RoleAndProJPackageMapper roleAndProJPackageMapper;

    @Override
    public List<RoleAndProJPackage> selectRoleAndProj() {
        return roleAndProJPackageMapper.selectRoleAndProj();
    }

    @Override
    public List<RoleAndProJPackage> selectRoleAndProj2() {
        return roleAndProJPackageMapper.selectRoleAndProj2();
    }
}
