package com.tsbg.mis.ecoMapper;


import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JwtUsedOnceMapper {

    int selectByUsedOnce(String usedOnce);

    int insert(String usedOnce);
}