package com.tsbg.ecosys.mapper;


import com.tsbg.ecosys.model.bag.DerivediPackage;
import com.tsbg.ecosys.model.bag.ProjectqfPackage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface QuestionMapper {
    //导出反馈人、处理人信息
    List<DerivediPackage> selectquestion();

    //分页查询反馈人、处理人信息
    List<ProjectqfPackage> selectbasePage();
}