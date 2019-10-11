package com.tsbg.ecosys.mapper;

import com.tsbg.ecosys.model.QuestionFeedback;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionFeedbackMapper {
    int deleteByPrimaryKey(Integer questionFeedbackId);

    int insert(QuestionFeedback record);

    int insertSelective(QuestionFeedback record);

    QuestionFeedback selectByPrimaryKey(Integer questionFeedbackId);

    List<QuestionFeedback> selectAll();

    int updateByPrimaryKeySelective(QuestionFeedback record);

    int updateByPrimaryKey(QuestionFeedback record);

    int updateApplyStatusIdByPrimaryKey(Integer questionFeedbackId,Integer applyStatusId);
}