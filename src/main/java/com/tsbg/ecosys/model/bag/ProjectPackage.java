package com.tsbg.ecosys.model.bag;

import com.tsbg.ecosys.util.PageRequest;

public class ProjectPackage {

    private PageRequest pageRequest;

    private ProjectqfPackage projectqfPackage;

    public ProjectPackage() {

    }

    public PageRequest getPageRequest() {
        return pageRequest;
    }

    public void setPageRequest(PageRequest pageRequest) {
        this.pageRequest = pageRequest;
    }

    public ProjectqfPackage getProjectqfPackage() {
        return projectqfPackage;
    }

    public void setProjectqfPackage(ProjectqfPackage projectqfPackage) {
        this.projectqfPackage = projectqfPackage;
    }
}
