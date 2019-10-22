package com.tsbg.ecosys.ecoServiceImpl;


import com.tsbg.ecosys.ecoMapper.QuestionHandleMapper;
import com.tsbg.ecosys.ecoModel.QuestionHandle;
import com.tsbg.ecosys.ecoService.QuestionHandleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionHandleServiceImpl implements QuestionHandleService {
    @Autowired
    private QuestionHandleMapper questionHandleMapper;
    @Override
    public int deleteByPrimaryKey(Integer questionHandleId) {
        return questionHandleMapper.deleteByPrimaryKey(questionHandleId);
    }

    @Override
    public int insert(QuestionHandle record) {
        return questionHandleMapper.insert(record);
    }

    @Override
    public int insertSelective(QuestionHandle record) {
        return questionHandleMapper.insertSelective(record);
    }

    @Override
    public QuestionHandle selectByPrimaryKey(Integer questionHandleId) {
        return questionHandleMapper.selectByPrimaryKey(questionHandleId);
    }

    @Override
    public int updateByPrimaryKeySelective(QuestionHandle record) {
        return questionHandleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByFeedBackIdSelective(QuestionHandle record){
        return questionHandleMapper.updateByFeedBackIdSelective(record);
    }

    @Override
    public int updateByPrimaryKey(QuestionHandle record) {
        return questionHandleMapper.updateByPrimaryKey(record);
    }

    @Override
    public QuestionHandle selectByQuestionFeedBackId(Integer questionFeedbackId){
        return questionHandleMapper.selectByQuestionFeedBackId(questionFeedbackId);
    }
}
