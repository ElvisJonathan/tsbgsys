package com.tsbg.mis.powerService;

import com.tsbg.mis.powerMapper.TokenBlacklistMapper2;
import com.tsbg.mis.powerModel.TokenBlacklist2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenBlacklistServiceImpl2 implements TokenBlacklistService2 {

    @Autowired
    private TokenBlacklistMapper2 tokenBlacklistMapper;

    @Override
    public int insertSelective(TokenBlacklist2 record) {
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
