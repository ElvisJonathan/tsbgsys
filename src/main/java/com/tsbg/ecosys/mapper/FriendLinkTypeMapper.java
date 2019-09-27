package com.tsbg.ecosys.mapper;

import com.tsbg.ecosys.model.FriendLinkType;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FriendLinkTypeMapper {
    int deleteByPrimaryKey(Integer typeId);

    int insert(FriendLinkType record);

    int insertSelective(FriendLinkType record);

    FriendLinkType selectByPrimaryKey(Integer typeId);

    int updateByPrimaryKeySelective(FriendLinkType record);

    int updateByPrimaryKey(FriendLinkType record);
}