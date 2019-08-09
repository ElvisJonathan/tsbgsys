package com.tsbg.ecosys.serviceImpl;

import com.tsbg.ecosys.mapper.EcInfoMapper;
import com.tsbg.ecosys.service.EcInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EcInfoServiceImpl implements EcInfoService {

    @Autowired
    private EcInfoMapper ecInfoMapper;


    @Override
    public int updateByCid(int cid) {
        return ecInfoMapper.updateByCid(cid);
    }
}
