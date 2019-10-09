package com.tsbg.ecosys.serviceImpl;

import com.tsbg.ecosys.mapper.QuestionTypeMapper;
import com.tsbg.ecosys.model.QuestionType;
import com.tsbg.ecosys.service.QuestionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionTypeServiceImpl implements QuestionTypeService {
    @Autowired
    private QuestionTypeMapper questionTypeMapper;
    @Override
    public int deleteByPrimaryKey(Integer typeId) {
        return questionTypeMapper.deleteByPrimaryKey(typeId);
    }

    @Override
    public int insert(QuestionType record) {
        return questionTypeMapper.insert(record);
    }

    @Override
    public int insertSelective(QuestionType record) {
        return questionTypeMapper.insertSelective(record);
    }

    @Override
    public QuestionType selectByPrimaryKey(Integer typeId) {
        return questionTypeMapper.selectByPrimaryKey(typeId);
    }

    @Override
    public int updateByPrimaryKeySelective(QuestionType record) {
        return questionTypeMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(QuestionType record) {
        return questionTypeMapper.updateByPrimaryKey(record);
    }
}
