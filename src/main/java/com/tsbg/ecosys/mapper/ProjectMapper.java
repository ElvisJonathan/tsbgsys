package com.tsbg.ecosys.mapper;

import com.tsbg.ecosys.model.Project;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProjectMapper {
    //根據proj_id查詢All
    Project selectByPrimaryKey(int projId);

    //根據projName查詢All
    Project selectByProName(String proName);

}
