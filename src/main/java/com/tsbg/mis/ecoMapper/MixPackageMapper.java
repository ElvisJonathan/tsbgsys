package com.tsbg.mis.ecoMapper;

import com.tsbg.mis.ecoModel.Eccontacts;
import com.tsbg.mis.ecoModel.Epartner;
import com.tsbg.mis.ecoModel.bag.MixPackage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MixPackageMapper {

    List<MixPackage> selectAll(@Param("epartner")Epartner epartner, @Param("eccontacts")Eccontacts eccontacts);
}
