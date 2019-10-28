package com.tsbg.mis.powerMapper;

import com.tsbg.mis.powerModel.Project;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProjectMapper2 {
    int deleteByPrimaryKey(Integer projId);

    int insert(Project record);

    int insertSelective(Project record);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);

    Project selectByPrimaryKey(Integer projId);

    //根據projName查詢All
    Project selectByProName(String proName);
}
