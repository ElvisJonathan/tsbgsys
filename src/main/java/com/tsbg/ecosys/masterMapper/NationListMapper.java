package com.tsbg.ecosys.masterMapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NationListMapper {

    List<String> selectNationName();
}
