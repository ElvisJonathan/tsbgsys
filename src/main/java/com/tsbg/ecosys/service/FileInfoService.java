package com.tsbg.ecosys.service;

import com.tsbg.ecosys.model.FileInfo;
import org.apache.ibatis.annotations.Param;

public interface FileInfoService {

    int insertSelective(FileInfo record);

    //重复文件名判断
    int selectFileCountByFileName(String filename);

    //判断文件是否未修改
    int judgeIfFileChanged(@Param("partnerNo")Integer partnerNo, @Param("fileName")String fileName);
}
