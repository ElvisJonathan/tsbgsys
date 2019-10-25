package com.tsbg.mis.ecoService;

import com.tsbg.mis.ecoModel.Eccontacts;
import com.tsbg.mis.ecoModel.Epartner;
import com.tsbg.mis.ecoModel.bag.MixPackage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MixPackageService {

    List<MixPackage> selectAll(@Param("epartner")Epartner epartner, @Param("eccontacts") Eccontacts eccontacts);
}
