package com.tsbg.mis.questionMapper;

import com.tsbg.mis.questionModel.QuestionHandle2;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionHandleMapper2 {
    int deleteByPrimaryKey(Integer questionHandleId);

    int insert(QuestionHandle2 record);

    int insertSelective(QuestionHandle2 record);

    QuestionHandle2 selectByPrimaryKey(Integer questionHandleId);

    int updateByPrimaryKeySelective(QuestionHandle2 record);

    int updateByPrimaryKey(QuestionHandle2 record);

    int updateByFeedBackIdSelective(QuestionHandle2 record);

    QuestionHandle2 selectByQuestionFeedBackId(Integer questionFeedbackId);
}
