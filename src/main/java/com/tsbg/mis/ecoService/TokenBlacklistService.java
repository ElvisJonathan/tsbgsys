package com.tsbg.mis.ecoService;

import com.tsbg.mis.ecoModel.TokenBlacklist;

public interface TokenBlacklistService {

    int insertSelective(TokenBlacklist record);

    int updateStatusByTokenCode(String token);

    int selectCountFromTokenList(String token);
}
