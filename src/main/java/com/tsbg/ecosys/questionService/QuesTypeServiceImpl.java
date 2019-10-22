package com.tsbg.ecosys.questionService;


import com.tsbg.ecosys.questionMapper.QuesTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuesTypeServiceImpl implements QuesTypeService {

    @Autowired
    private QuesTypeMapper quesTypeMapper;
    @Override
    public List<String> selectTypeName() {
        return quesTypeMapper.selectTypeName();
    }
}
