package com.tsbg.ecosys.ecoService;

import com.tsbg.ecosys.ecoModel.TokenBlacklist;

public interface TokenBlacklistService {

    int insertSelective(TokenBlacklist record);

    int updateStatusByTokenCode(String token);

    int selectCountFromTokenList(String token);
}
