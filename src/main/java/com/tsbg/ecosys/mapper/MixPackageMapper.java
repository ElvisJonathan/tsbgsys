package com.tsbg.ecosys.mapper;

import com.tsbg.ecosys.model.Eccontacts;
import com.tsbg.ecosys.model.Epartner;
import com.tsbg.ecosys.model.bag.MixPackage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MixPackageMapper {

    List<MixPackage> selectAll(@Param("epartner")Epartner epartner, @Param("eccontacts")Eccontacts eccontacts);
}
