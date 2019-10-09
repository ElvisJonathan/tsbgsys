package com.tsbg.ecosys.service;

import com.tsbg.ecosys.model.QuestionType;

public interface QuestionTypeService {
    int deleteByPrimaryKey(Integer typeId);

    int insert(QuestionType record);

    int insertSelective(QuestionType record);

    QuestionType selectByPrimaryKey(Integer typeId);

    int updateByPrimaryKeySelective(QuestionType record);

    int updateByPrimaryKey(QuestionType record);
}
