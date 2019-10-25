package com.tsbg.mis.ecoServiceImpl;

import com.tsbg.mis.ecoMapper.MixPackageMapper;
import com.tsbg.mis.ecoModel.Eccontacts;
import com.tsbg.mis.ecoModel.Epartner;
import com.tsbg.mis.ecoModel.bag.MixPackage;
import com.tsbg.mis.ecoService.MixPackageService;
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
