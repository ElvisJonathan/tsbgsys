package com.tsbg.ecosys.service;

import com.tsbg.ecosys.model.FileInfo;

public interface FileInfoService {

    int insertSelective(FileInfo record);

    //重复文件名判断
    int selectFileCountByFileName(String filename);
}
