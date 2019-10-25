package com.tsbg.mis.doorMapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FriendLinkMapper {

    List<String> selectLinkName();
}
