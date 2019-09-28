package com.tsbg.ecosys.serviceImpl;

import com.tsbg.ecosys.dto.QuestionDto;
import com.tsbg.ecosys.mapper.QHandleMapper;
import com.tsbg.ecosys.mapper.QfeedbackMapper;
import com.tsbg.ecosys.model.QHandle;
import com.tsbg.ecosys.model.Qfeedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Questionsservice {
    @Autowired
    private QfeedbackMapper qfeedbackMapper;

    @Autowired
    private QHandleMapper qHandleMapper;

    //查询用户反馈信息
    public QuestionDto getQuestionList() {
        List<Qfeedback> qfeedbacks = qfeedbackMapper.selectqfeedbackall();
        List<QHandle> qHandles = qHandleMapper.selectqHandleall();
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQfeedbacks(qfeedbacks);
        questionDto.setqHandles(qHandles);
        return questionDto;
    }
}
