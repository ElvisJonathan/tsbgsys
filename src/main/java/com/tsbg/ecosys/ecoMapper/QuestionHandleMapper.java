package com.tsbg.ecosys.ecoMapper;

import com.tsbg.ecosys.ecoModel.QuestionHandle;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionHandleMapper {
    int deleteByPrimaryKey(Integer questionHandleId);

    int insert(QuestionHandle record);

    int insertSelective(QuestionHandle record);

    QuestionHandle selectByPrimaryKey(Integer questionHandleId);

    int updateByPrimaryKeySelective(QuestionHandle record);

    int updateByFeedBackIdSelective(QuestionHandle record);

    int updateByPrimaryKey(QuestionHandle record);

    QuestionHandle selectByQuestionFeedBackId(Integer questionFeedbackId);
}