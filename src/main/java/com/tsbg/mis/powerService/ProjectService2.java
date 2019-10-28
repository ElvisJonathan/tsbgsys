package com.tsbg.mis.powerService;

import com.tsbg.mis.powerModel.Project;

public interface ProjectService2 {

    Project selectByPrimaryKey(Integer projId);

    //根據projName查詢All
    Project selectByProName(String proName);
}
