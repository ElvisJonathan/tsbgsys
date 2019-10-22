package com.tsbg.ecosys.ecoMapper;

import com.tsbg.ecosys.ecoModel.TokenBlacklist;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TokenBlacklistMapper {
    int deleteByPrimaryKey(Long tokenId);

    int insert(TokenBlacklist record);

    int insertSelective(TokenBlacklist record);

    TokenBlacklist selectByPrimaryKey(Long tokenId);

    int updateByPrimaryKeySelective(TokenBlacklist record);

    int updateByPrimaryKey(TokenBlacklist record);

    int updateStatusByTokenCode(String token);

    int selectCountFromTokenList(String token);
}