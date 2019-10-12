package com.tsbg.ecosys.service;

import com.tsbg.ecosys.model.tokenBlacklist;

public interface tokenBlacklistService {

    int insertSelective(tokenBlacklist record);

    int updateStatusByTokenCode(String token);

    int selectCountFromTokenList(String token);
}
