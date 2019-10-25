package com.tsbg.mis.powerMapper;

import com.tsbg.mis.powerModel.FileInfo;

public interface FileInfoMapper2 {
    int deleteByPrimaryKey(Integer fileId);

    int insert(FileInfo record);

    int insertSelective(FileInfo record);

    FileInfo selectByPrimaryKey(Integer fileId);

    int updateByPrimaryKeySelective(FileInfo record);

    int updateByPrimaryKey(FileInfo record);
}