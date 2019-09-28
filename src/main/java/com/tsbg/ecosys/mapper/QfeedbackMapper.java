package com.tsbg.ecosys.mapper;

import com.tsbg.ecosys.model.Qfeedback;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QfeedbackMapper {
    List<Qfeedback> selectqfeedbackall();
}
