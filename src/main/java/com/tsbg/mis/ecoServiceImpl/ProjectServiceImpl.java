package com.tsbg.mis.ecoServiceImpl;

import com.tsbg.mis.ecoMapper.ProjectMapper;
import com.tsbg.mis.ecoModel.Project;
import com.tsbg.mis.ecoService.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;
    //根據proj_id查詢All
    public Project selectByPrimaryKey(int projId){
        return projectMapper.selectByPrimaryKey(projId);
    }

    //根據projName查詢projId
    public Project selectByProName(String proName){
        return projectMapper.selectByProName(proName);
    }
}
