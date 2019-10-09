package com.tsbg.ecosys.mapper;


import com.tsbg.ecosys.model.bag.DerivediPackage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface QuestionMapper {
    List<DerivediPackage> selectquestion();
}