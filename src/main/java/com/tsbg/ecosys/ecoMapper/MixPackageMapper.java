package com.tsbg.ecosys.ecoMapper;

import com.tsbg.ecosys.ecoModel.Eccontacts;
import com.tsbg.ecosys.ecoModel.Epartner;
import com.tsbg.ecosys.ecoModel.bag.MixPackage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MixPackageMapper {

    List<MixPackage> selectAll(@Param("epartner")Epartner epartner, @Param("eccontacts")Eccontacts eccontacts);
}
