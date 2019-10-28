package com.tsbg.mis.powerService;

import com.tsbg.mis.powerMapper.ProjectMapper2;
import com.tsbg.mis.powerModel.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl2 implements ProjectService2 {

    @Autowired
    private ProjectMapper2 projectMapper;


    @Override
    public Project selectByPrimaryKey(Integer projId) {
        return projectMapper.selectByPrimaryKey(projId);
    }

    @Override
    public Project selectByProName(String proName) {
        return projectMapper.selectByProName(proName);
    }
}
