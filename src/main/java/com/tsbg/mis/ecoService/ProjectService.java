package com.tsbg.mis.ecoService;

import com.tsbg.mis.ecoModel.Project;

public interface ProjectService {
    //根據proj_id查詢All
    Project selectByPrimaryKey(int projId);

    //根據projName查詢projId
    Project selectByProName(String proName);

}
