package com.tsbg.ecosys.mapper;


import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JwtUsedOnceMapper {

    int selectByUsedOnce(String usedOnce);

    int insert(String usedOnce);
}