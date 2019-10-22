package com.tsbg.ecosys.ecoService;

import com.tsbg.ecosys.ecoModel.Eccontacts;
import com.tsbg.ecosys.ecoModel.Epartner;
import com.tsbg.ecosys.ecoModel.bag.MixPackage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MixPackageService {

    List<MixPackage> selectAll(@Param("epartner")Epartner epartner, @Param("eccontacts") Eccontacts eccontacts);
}
