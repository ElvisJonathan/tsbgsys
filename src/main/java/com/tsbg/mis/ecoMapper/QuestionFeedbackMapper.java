package com.tsbg.mis.ecoMapper;

import com.tsbg.mis.ecoDto.Comment;
import com.tsbg.mis.ecoModel.QuestionFeedback;
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

    List<Comment> selectColumnNamea();

    List<Comment> selectColumnNameb();

    List<Comment> selectColumnNamec();

    List<Comment> selectColumnNamed();

    List<Comment> selectColumnNamee();
}