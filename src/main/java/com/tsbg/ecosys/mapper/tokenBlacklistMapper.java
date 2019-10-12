package com.tsbg.ecosys.mapper;

import com.tsbg.ecosys.model.tokenBlacklist;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface tokenBlacklistMapper {
    int deleteByPrimaryKey(Long tokenId);

    int insert(tokenBlacklist record);

    int insertSelective(tokenBlacklist record);

    tokenBlacklist selectByPrimaryKey(Long tokenId);

    int updateByPrimaryKeySelective(tokenBlacklist record);

    int updateByPrimaryKey(tokenBlacklist record);

    int updateStatusByTokenCode(String token);

    int selectCountFromTokenList(String token);
}