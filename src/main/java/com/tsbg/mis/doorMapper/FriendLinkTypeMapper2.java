package com.tsbg.mis.doorMapper;

import com.tsbg.mis.doorModel.FriendLinkType;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FriendLinkTypeMapper2 {
    int deleteByPrimaryKey(Integer typeId);

    int insert(FriendLinkType record);

    int insertSelective(FriendLinkType record);

    FriendLinkType selectByPrimaryKey(Integer typeId);

    int updateByPrimaryKeySelective(FriendLinkType record);

    int updateByPrimaryKey(FriendLinkType record);
}
