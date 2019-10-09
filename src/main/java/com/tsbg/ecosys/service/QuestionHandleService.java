package com.tsbg.ecosys.service;

import com.tsbg.ecosys.model.QuestionHandle;


public interface QuestionHandleService {
    int deleteByPrimaryKey(Integer questionHandleId);

    int insert(QuestionHandle record);

    int insertSelective(QuestionHandle record);

    QuestionHandle selectByPrimaryKey(Integer questionHandleId);

    int updateByPrimaryKeySelective(QuestionHandle record);

    int updateByFeedBackIdSelective(QuestionHandle record);

    int updateByPrimaryKey(QuestionHandle record);

    QuestionHandle selectByQuestionFeedBackId(Integer questionFeedbackId);
}
