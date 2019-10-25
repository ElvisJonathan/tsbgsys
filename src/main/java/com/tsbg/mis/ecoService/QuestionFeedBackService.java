package com.tsbg.mis.ecoService;

import com.tsbg.mis.ecoDto.Comment;
import com.tsbg.mis.ecoModel.QuestionFeedback;

import java.util.List;

public interface QuestionFeedBackService {

    int insert(QuestionFeedback record);

    QuestionFeedback selectByPrimaryKey(Integer questionFeedbackId);

    List<QuestionFeedback> selectAll();

    int deleteByPrimaryKey(Integer questionFeedbackId);

    int insertSelective(QuestionFeedback record);

    int updateByPrimaryKeySelective(QuestionFeedback record);

    int updateByPrimaryKey(QuestionFeedback record);

    int updateApplyStatusIdByPrimaryKey(Integer questionFeedbackId,Integer applyStatusId);

    List<Comment> selectColumnNamea();

    List<Comment> selectColumnNameb();

    List<Comment> selectColumnNamec();

    List<Comment> selectColumnNamed();

    List<Comment> selectColumnNamee();
}
