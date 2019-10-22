package com.tsbg.ecosys.ecoMapper;

import com.tsbg.ecosys.ecoModel.bag.RoleAndProJPackage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleAndProJPackageMapper {

    List<RoleAndProJPackage> selectRoleAndProj();

    //配置映射user_role
    List<RoleAndProJPackage> selectRoleAndProj2();
}
