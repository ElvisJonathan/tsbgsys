package com.tsbg.mis.powerMapper;

import com.tsbg.mis.powerModel.TokenBlacklist2;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TokenBlacklistMapper2 {
    int deleteByPrimaryKey(Long tokenId);

    int insert(TokenBlacklist2 record);

    int insertSelective(TokenBlacklist2 record);

    TokenBlacklist2 selectByPrimaryKey(Long tokenId);

    int updateByPrimaryKeySelective(TokenBlacklist2 record);

    int updateByPrimaryKey(TokenBlacklist2 record);

    int updateStatusByTokenCode(String token);

    int selectCountFromTokenList(String token);
}