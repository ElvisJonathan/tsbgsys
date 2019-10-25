package com.tsbg.mis.ecoService;

import com.tsbg.mis.ecoModel.QuestionHandle;


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
