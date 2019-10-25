package com.tsbg.mis.questionMapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuesTypeMapper {

    List<String> selectTypeName();
}
