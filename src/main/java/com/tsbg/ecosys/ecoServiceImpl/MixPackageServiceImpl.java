package com.tsbg.ecosys.ecoServiceImpl;

import com.tsbg.ecosys.ecoMapper.MixPackageMapper;
import com.tsbg.ecosys.ecoModel.Eccontacts;
import com.tsbg.ecosys.ecoModel.Epartner;
import com.tsbg.ecosys.ecoModel.bag.MixPackage;
import com.tsbg.ecosys.ecoService.MixPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MixPackageServiceImpl implements MixPackageService {

    @Autowired
    private MixPackageMapper mixPackageMapper;


    @Override
    public List<MixPackage> selectAll(Epartner epartner, Eccontacts eccontacts) {
        return mixPackageMapper.selectAll(epartner,eccontacts);
    }
}
