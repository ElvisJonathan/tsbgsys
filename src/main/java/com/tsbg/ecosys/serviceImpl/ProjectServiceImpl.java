package com.tsbg.ecosys.serviceImpl;

import com.tsbg.ecosys.mapper.ProjectMapper;
import com.tsbg.ecosys.model.Project;
import com.tsbg.ecosys.service.ProjectService;
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
