package com.tsbg.mis.questionService;

import com.tsbg.mis.questionMapper.QuestionHandleMapper2;
import com.tsbg.mis.questionModel.QuestionHandle2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionHandleServiceImpl2 implements QuestionHandleService2 {

    @Autowired
    private QuestionHandleMapper2 questionHandleMapper;

    @Override
    public int deleteByPrimaryKey(Integer questionHandleId) {
        return questionHandleMapper.deleteByPrimaryKey(questionHandleId);
    }

    @Override
    public int insert(QuestionHandle2 record) {
        return questionHandleMapper.insert(record);
    }

    @Override
    public int insertSelective(QuestionHandle2 record) {
        return questionHandleMapper.insertSelective(record);
    }

    @Override
    public QuestionHandle2 selectByPrimaryKey(Integer questionHandleId) {
        return questionHandleMapper.selectByPrimaryKey(questionHandleId);
    }

    @Override
    public int updateByPrimaryKeySelective(QuestionHandle2 record) {
        return questionHandleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByFeedBackIdSelective(QuestionHandle2 record){
        return questionHandleMapper.updateByFeedBackIdSelective(record);
    }

    @Override
    public int updateByPrimaryKey(QuestionHandle2 record) {
        return questionHandleMapper.updateByPrimaryKey(record);
    }

    @Override
    public QuestionHandle2 selectByQuestionFeedBackId(Integer questionFeedbackId){
        return questionHandleMapper.selectByQuestionFeedBackId(questionFeedbackId);
    }
}
