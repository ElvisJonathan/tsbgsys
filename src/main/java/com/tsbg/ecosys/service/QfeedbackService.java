package com.tsbg.ecosys.service;

import com.tsbg.ecosys.dto.Comment;
import com.tsbg.ecosys.model.Qfeedback;

import java.util.List;
import java.util.Map;

public interface QfeedbackService {
    List<Qfeedback> selectqfeedbackall();

    List<Comment> selectColumnNamea();

    List<Comment> selectColumnNameb();

    List<Comment> selectColumnNamec();

    List<Comment> selectColumnNamed();

    List<Comment> selectColumnNamee();
}
