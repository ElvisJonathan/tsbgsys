package com.tsbg.ecosys.service;

import com.tsbg.ecosys.model.bag.ProjectqfPackage;

import java.util.List;

public interface ProjectqfService {
    //查询反馈、处理、文件等信息
    List<ProjectqfPackage> selectProjectqfall();
}
