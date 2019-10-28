package com.tsbg.mis.questionMapper;

import com.tsbg.mis.questionModel.bag.DerivediPackage2;
import com.tsbg.mis.questionModel.bag.ProjectqfPackage2;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionMapper2 {
    //导出反馈人、处理人信息
    List<DerivediPackage2> selectquestion();

    //分页查询反馈人、处理人信息
    List<ProjectqfPackage2> selectbasePage();
}
