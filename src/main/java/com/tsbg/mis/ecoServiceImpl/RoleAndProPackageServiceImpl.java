package com.tsbg.mis.ecoServiceImpl;

import com.tsbg.mis.ecoMapper.RoleAndProJPackageMapper;
import com.tsbg.mis.ecoModel.bag.RoleAndProJPackage;
import com.tsbg.mis.ecoService.RoleAndProJPackageService;
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
