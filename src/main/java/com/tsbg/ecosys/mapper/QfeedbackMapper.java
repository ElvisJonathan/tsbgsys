package com.tsbg.ecosys.mapper;

import com.tsbg.ecosys.dto.Comment;
import com.tsbg.ecosys.model.Qfeedback;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface QfeedbackMapper {
    List<Qfeedback> selectqfeedbackall();

    List<Comment> selectColumnNamea();

    List<Comment> selectColumnNameb();

    List<Comment> selectColumnNamec();

    List<Comment> selectColumnNamed();

    List<Comment> selectColumnNamee();
}
