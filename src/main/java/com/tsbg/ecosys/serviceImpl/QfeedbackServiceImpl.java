package com.tsbg.ecosys.serviceImpl;

import com.tsbg.ecosys.mapper.QfeedbackMapper;
import com.tsbg.ecosys.model.Qfeedback;
import com.tsbg.ecosys.service.QfeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QfeedbackServiceImpl implements QfeedbackService{
    @Autowired
    private QfeedbackMapper qfeedbackMapper;
    @Override
    public List<Qfeedback> selectqfeedbackall() {
        return qfeedbackMapper.selectqfeedbackall();
    }
}
