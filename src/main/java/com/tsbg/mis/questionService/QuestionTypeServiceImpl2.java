package com.tsbg.mis.questionService;

import com.tsbg.mis.questionMapper.QuestionTypeMapper2;
import com.tsbg.mis.questionModel.QuestionType2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionTypeServiceImpl2 implements QuestionTypeService2 {

    @Autowired
    private QuestionTypeMapper2 questionTypeMapper;

    @Override
    public int deleteByPrimaryKey(Integer typeId) {
        return questionTypeMapper.deleteByPrimaryKey(typeId);
    }

    @Override
    public int insert(QuestionType2 record) {
        return questionTypeMapper.insert(record);
    }

    @Override
    public int insertSelective(QuestionType2 record) {
        return questionTypeMapper.insertSelective(record);
    }

    @Override
    public QuestionType2 selectByPrimaryKey(Integer typeId) {
        return questionTypeMapper.selectByPrimaryKey(typeId);
    }

    @Override
    public int updateByPrimaryKeySelective(QuestionType2 record) {
        return questionTypeMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(QuestionType2 record) {
        return questionTypeMapper.updateByPrimaryKey(record);
    }
}
