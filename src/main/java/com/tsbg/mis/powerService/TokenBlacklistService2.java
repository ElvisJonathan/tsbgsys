package com.tsbg.mis.powerService;

import com.tsbg.mis.powerModel.TokenBlacklist2;

public interface TokenBlacklistService2 {

    int insertSelective(TokenBlacklist2 record);

    int updateStatusByTokenCode(String token);

    int selectCountFromTokenList(String token);
}
