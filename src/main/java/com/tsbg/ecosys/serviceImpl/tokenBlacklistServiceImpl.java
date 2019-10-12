package com.tsbg.ecosys.serviceImpl;

import com.tsbg.ecosys.mapper.tokenBlacklistMapper;
import com.tsbg.ecosys.model.tokenBlacklist;
import com.tsbg.ecosys.service.tokenBlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class tokenBlacklistServiceImpl implements tokenBlacklistService {

    @Autowired
    private tokenBlacklistMapper tokenBlacklistMapper;


    @Override
    public int insertSelective(tokenBlacklist record) {
        return tokenBlacklistMapper.insertSelective(record);
    }

    @Override
    public int updateStatusByTokenCode(String token) {
        return tokenBlacklistMapper.updateStatusByTokenCode(token);
    }

    @Override
    public int selectCountFromTokenList(String token) {
        return tokenBlacklistMapper.selectCountFromTokenList(token);
    }
}
