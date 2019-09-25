package com.tsbg.ecosys.serviceImpl;

import com.tsbg.ecosys.mapper.MixPackageMapper;
import com.tsbg.ecosys.model.Eccontacts;
import com.tsbg.ecosys.model.Epartner;
import com.tsbg.ecosys.model.bag.MixPackage;
import com.tsbg.ecosys.service.MixPackageService;
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
