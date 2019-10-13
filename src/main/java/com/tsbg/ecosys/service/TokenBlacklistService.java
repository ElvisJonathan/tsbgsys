package com.tsbg.ecosys.service;

import com.tsbg.ecosys.model.TokenBlacklist;

public interface TokenBlacklistService {

    int insertSelective(TokenBlacklist record);

    int updateStatusByTokenCode(String token);

    int selectCountFromTokenList(String token);
}
