package com.tsbg.ecosys.serviceImpl;

import com.tsbg.ecosys.mapper.JwtUsedOnceMapper;
import com.tsbg.ecosys.service.JwtUsedOnceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JwtUsedOnceServiceImpl implements JwtUsedOnceService {

    @Autowired
    private JwtUsedOnceMapper jwtUsedOnceMapper;

    @Override
    public int selectByUsedOnce(String usedOnce) {
        return jwtUsedOnceMapper.selectByUsedOnce(usedOnce);
    }

    @Override
    public int insert(String usedOnce) {
        return jwtUsedOnceMapper.insert(usedOnce);
    }
}
