package com.tsbg.ecosys.service;

import com.tsbg.ecosys.model.Project;

public interface ProjectService {
    //根據proj_id查詢All
    Project selectByPrimaryKey(int projId);

    //根據projName查詢projId
    Project selectByProName(String proName);

}
