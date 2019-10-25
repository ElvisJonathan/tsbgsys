package com.tsbg.mis.ecoMapper;

import com.tsbg.mis.ecoModel.QuestionType;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionTypeMapper {
    int deleteByPrimaryKey(Integer typeId);

    int insert(QuestionType record);

    int insertSelective(QuestionType record);

    QuestionType selectByPrimaryKey(Integer typeId);

    int updateByPrimaryKeySelective(QuestionType record);

    int updateByPrimaryKey(QuestionType record);
}