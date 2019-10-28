package com.tsbg.mis.questionService;

import com.tsbg.mis.ecoDto.Comment;
import com.tsbg.mis.questionModel.QuestionFeedback2;

import java.util.List;

public interface QuestionFeedBackService2 {

    int insert(QuestionFeedback2 record);

    QuestionFeedback2 selectByPrimaryKey(Integer questionFeedbackId);

    List<QuestionFeedback2> selectAll();

    int deleteByPrimaryKey(Integer questionFeedbackId);

    int insertSelective(QuestionFeedback2 record);

    int updateByPrimaryKeySelective(QuestionFeedback2 record);

    int updateByPrimaryKey(QuestionFeedback2 record);

    int updateApplyStatusIdByPrimaryKey(Integer questionFeedbackId, Integer applyStatusId);

    List<Comment> selectColumnNamea();

    List<Comment> selectColumnNameb();

    List<Comment> selectColumnNamec();

    List<Comment> selectColumnNamed();

    List<Comment> selectColumnNamee();
}
