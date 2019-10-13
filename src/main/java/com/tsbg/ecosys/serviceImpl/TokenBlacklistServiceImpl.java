package com.tsbg.ecosys.serviceImpl;

import com.tsbg.ecosys.mapper.TokenBlacklistMapper;
import com.tsbg.ecosys.model.TokenBlacklist;
import com.tsbg.ecosys.service.TokenBlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenBlacklistServiceImpl implements TokenBlacklistService {

    @Autowired
    private TokenBlacklistMapper tokenBlacklistMapper;


    @Override
    public int insertSelective(TokenBlacklist record) {
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
