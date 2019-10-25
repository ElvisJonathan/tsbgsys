package com.tsbg.mis.powerMapper;

import com.tsbg.mis.powerModel.Project;

public interface ProjectMapper2 {
    int deleteByPrimaryKey(Integer projId);

    int insert(Project record);

    int insertSelective(Project record);

    Project selectByPrimaryKey(Integer projId);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);
}