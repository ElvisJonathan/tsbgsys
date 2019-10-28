package com.tsbg.mis.questionMapper;

import com.tsbg.mis.ecoDto.Comment;
import com.tsbg.mis.questionModel.QuestionFeedback2;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionFeedbackMapper2 {
    int deleteByPrimaryKey(Integer questionFeedbackId);

    int insert(QuestionFeedback2 record);

    int insertSelective(QuestionFeedback2 record);

    QuestionFeedback2 selectByPrimaryKey(Integer questionFeedbackId);

    int updateByPrimaryKeySelective(QuestionFeedback2 record);

    int updateByPrimaryKey(QuestionFeedback2 record);

    List<QuestionFeedback2> selectAll();

    int updateApplyStatusIdByPrimaryKey(Integer questionFeedbackId,Integer applyStatusId);

    List<Comment> selectColumnNamea();

    List<Comment> selectColumnNameb();

    List<Comment> selectColumnNamec();

    List<Comment> selectColumnNamed();

    List<Comment> selectColumnNamee();
}