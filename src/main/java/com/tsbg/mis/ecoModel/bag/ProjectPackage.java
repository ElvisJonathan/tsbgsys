package com.tsbg.mis.ecoModel.bag;

import com.tsbg.mis.util.PageRequest;

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
