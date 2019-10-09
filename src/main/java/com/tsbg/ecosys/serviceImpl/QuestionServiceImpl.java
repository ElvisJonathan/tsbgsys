package com.tsbg.ecosys.serviceImpl;

import com.tsbg.ecosys.mapper.QuestionMapper;
import com.tsbg.ecosys.model.bag.DerivediPackage;
import com.tsbg.ecosys.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService{
   @Autowired
   private QuestionMapper questionMapper;

    @Override
    public List<DerivediPackage> selectquestion() {
        return questionMapper.selectquestion();
    }
}
