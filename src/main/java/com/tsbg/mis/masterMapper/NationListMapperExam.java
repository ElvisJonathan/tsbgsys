package com.tsbg.mis.masterMapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NationListMapperExam {

    List<String> selectNationName();
}