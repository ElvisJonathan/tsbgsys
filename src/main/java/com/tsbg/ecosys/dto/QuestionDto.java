package com.tsbg.ecosys.dto;

import com.tsbg.ecosys.model.QHandle;
import com.tsbg.ecosys.model.Qfeedback;

import java.util.List;

public class QuestionDto {
    private List<Qfeedback> qfeedbacks;

    private List<QHandle> qHandles;

    public List<Qfeedback> getQfeedbacks() {
        return qfeedbacks;
    }

    public void setQfeedbacks(List<Qfeedback> qfeedbacks) {
        this.qfeedbacks = qfeedbacks;
    }

    public List<QHandle> getqHandles() {
        return qHandles;
    }

    public void setqHandles(List<QHandle> qHandles) {
        this.qHandles = qHandles;
    }
}
