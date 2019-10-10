package com.tsbg.ecosys.serviceImpl;

import com.tsbg.ecosys.mapper.ProjectqfMapper;
import com.tsbg.ecosys.model.bag.ProjectqfPackage;
import com.tsbg.ecosys.service.ProjectqfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectqfServiceImpl implements ProjectqfService{
    @Autowired
    private ProjectqfMapper projectqfMapper;

    @Override
    public List<ProjectqfPackage> selectProjectqfall() {
        return projectqfMapper.selectProjectqfall();
    }
}
