package com.tsbg.ecosys.service;

import com.tsbg.ecosys.model.Eccontacts;
import com.tsbg.ecosys.model.Epartner;
import com.tsbg.ecosys.model.bag.MixPackage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MixPackageService {

    List<MixPackage> selectAll(@Param("epartner")Epartner epartner, @Param("eccontacts") Eccontacts eccontacts);
}
