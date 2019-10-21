package com.tsbg.ecosys.mapper;

import com.tsbg.ecosys.model.bag.RoleAndProJPackage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleAndProJPackageMapper {

    List<RoleAndProJPackage> selectRoleAndProj();

    //配置映射user_role
    List<RoleAndProJPackage> selectRoleAndProj2();
}
