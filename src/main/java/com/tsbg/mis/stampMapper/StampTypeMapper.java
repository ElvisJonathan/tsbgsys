package com.tsbg.mis.stampMapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StampTypeMapper {

    List<String> selectTypeName();
}