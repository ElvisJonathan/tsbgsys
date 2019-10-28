package com.tsbg.mis.questionMapper;

import com.tsbg.mis.questionModel.QuestionType2;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionTypeMapper2 {
    int deleteByPrimaryKey(Integer typeId);

    int insert(QuestionType2 record);

    int insertSelective(QuestionType2 record);

    QuestionType2 selectByPrimaryKey(Integer typeId);

    int updateByPrimaryKeySelective(QuestionType2 record);

    int updateByPrimaryKey(QuestionType2 record);
}