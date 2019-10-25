package com.tsbg.mis.ecoServiceImpl;

import com.tsbg.mis.ecoDto.Comment;
import com.tsbg.mis.ecoMapper.QuestionFeedbackMapper;
import com.tsbg.mis.ecoModel.QuestionFeedback;
import com.tsbg.mis.ecoService.QuestionFeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionFeedBackServiceImpl implements QuestionFeedBackService {
    @Autowired
    private QuestionFeedbackMapper questionFeedbackMapper;

    @Override
    public int insert(QuestionFeedback record) {
        return questionFeedbackMapper.insert(record);
    }

    public QuestionFeedback selectByPrimaryKey(Integer questionFeedbackId){
        return questionFeedbackMapper.selectByPrimaryKey(questionFeedbackId);
    }

    @Override
    public List<QuestionFeedback> selectAll() {
        return questionFeedbackMapper.selectAll();
    }

    @Override
    public int deleteByPrimaryKey(Integer questionFeedbackId) {
        return questionFeedbackMapper.deleteByPrimaryKey(questionFeedbackId);
    }

    @Override
    public int insertSelective(QuestionFeedback record) {
        return questionFeedbackMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(QuestionFeedback record) {
        return questionFeedbackMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateApplyStatusIdByPrimaryKey(Integer questionFeedbackId,Integer applyStatusId) {
        return questionFeedbackMapper.updateApplyStatusIdByPrimaryKey(questionFeedbackId,applyStatusId);
    }

    @Override
    public int updateByPrimaryKey(QuestionFeedback record) {
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
