package com.tsbg.ecosys.ecoServiceImpl;

import com.tsbg.ecosys.ecoMapper.JwtUsedOnceMapper;
import com.tsbg.ecosys.ecoService.JwtUsedOnceService;
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
