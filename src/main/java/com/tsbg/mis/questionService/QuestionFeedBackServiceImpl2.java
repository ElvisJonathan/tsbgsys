package com.tsbg.mis.questionService;

import com.tsbg.mis.ecoDto.Comment;
import com.tsbg.mis.questionMapper.QuestionFeedbackMapper2;
import com.tsbg.mis.questionModel.QuestionFeedback2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class QuestionFeedBackServiceImpl2 implements QuestionFeedBackService2 {
    @Autowired
    private QuestionFeedbackMapper2 questionFeedbackMapper;

    @Override
    public int insert(QuestionFeedback2 record) {
        return questionFeedbackMapper.insert(record);
    }

    public QuestionFeedback2 selectByPrimaryKey(Integer questionFeedbackId){
        return questionFeedbackMapper.selectByPrimaryKey(questionFeedbackId);
    }

    @Override
    public List<QuestionFeedback2> selectAll() {
        return questionFeedbackMapper.selectAll();
    }

    @Override
    public int deleteByPrimaryKey(Integer questionFeedbackId) {
        return questionFeedbackMapper.deleteByPrimaryKey(questionFeedbackId);
    }

    @Override
    public int insertSelective(QuestionFeedback2 record) {
        return questionFeedbackMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(QuestionFeedback2 record) {
        return questionFeedbackMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateApplyStatusIdByPrimaryKey(Integer questionFeedbackId,Integer applyStatusId) {
        return questionFeedbackMapper.updateApplyStatusIdByPrimaryKey(questionFeedbackId,applyStatusId);
    }

    @Override
    public int updateByPrimaryKey(QuestionFeedback2 record) {
        return questionFeedbackMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Comment> selectColumnNamea() {
        return questionFeedbackMapper.selectColumnNamea();
    }

    @Override
    public List<Comment> selectColumnNameb() {
        return questionFeedbackMapper.selectColumnNameb();
    }

    @Override
    public List<Comment> selectColumnNamec() {
        return questionFeedbackMapper.selectColumnNamec();
    }

    @Override
    public List<Comment> selectColumnNamed() {
        return questionFeedbackMapper.selectColumnNamed();
    }

    @Override
    public List<Comment> selectColumnNamee() {
        return questionFeedbackMapper.selectColumnNamee();
    }

}
