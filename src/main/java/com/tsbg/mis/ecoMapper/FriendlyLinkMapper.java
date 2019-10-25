package com.tsbg.mis.ecoMapper;

import com.tsbg.mis.ecoModel.FriendlyLink;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FriendlyLinkMapper {
    int deleteByPrimaryKey(Integer linkId);

    int insert(FriendlyLink record);

    int insertSelective(FriendlyLink record);

    FriendlyLink selectByPrimaryKey(Integer linkId);

    int updateByPrimaryKeySelective(FriendlyLink record);

    int updateByPrimaryKey(FriendlyLink record);

    List<FriendlyLink> selectPortalsiteUrl();
}