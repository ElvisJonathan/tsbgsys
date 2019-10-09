package com.tsbg.ecosys.serviceImpl;

import com.tsbg.ecosys.dto.Comment;
import com.tsbg.ecosys.mapper.QfeedbackMapper;
import com.tsbg.ecosys.model.Qfeedback;
import com.tsbg.ecosys.service.QfeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QfeedbackServiceImpl implements QfeedbackService{
    @Autowired
    private QfeedbackMapper qfeedbackMapper;
    @Override
    public List<Qfeedback> selectqfeedbackall() {
        return qfeedbackMapper.selectqfeedbackall();
    }

    @Override
    public List<Comment> selectColumnNamea() {
       return qfeedbackMapper.selectColumnNamea();
     }
    @Override
    public List<Comment> selectColumnNameb() {
        return qfeedbackMapper.selectColumnNameb();
    }

    @Override
    public List<Comment> selectColumnNamec() {
        return qfeedbackMapper.selectColumnNamec();
    }

    @Override
    public List<Comment> selectColumnNamed() {
        return qfeedbackMapper.selectColumnNamed();
    }

    @Override
    public List<Comment> selectColumnNamee() {
        return qfeedbackMapper.selectColumnNamee();
    }

}
