package com.tsbg.ecosys.mapper;


import com.tsbg.ecosys.model.bag.ProjectqfPackage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectqfMapper {

    List<ProjectqfPackage> selectProjectqfall();
}
