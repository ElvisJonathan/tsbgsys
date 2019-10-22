package com.tsbg.ecosys.ecoService;

import com.tsbg.ecosys.ecoModel.Project;

public interface ProjectService {
    //根據proj_id查詢All
    Project selectByPrimaryKey(int projId);

    //根據projName查詢projId
    Project selectByProName(String proName);

}
