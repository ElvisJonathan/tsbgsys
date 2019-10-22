package com.tsbg.ecosys.ecoMapper;

import com.tsbg.ecosys.ecoModel.QuestionType;
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