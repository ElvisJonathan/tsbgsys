package com.tsbg.ecosys.mapper;

import com.tsbg.ecosys.model.FileInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FileInfoMapper {
    int deleteByPrimaryKey(Integer fileId);

    int insert(FileInfo record);

    int insertSelective(FileInfo record);

    FileInfo selectByPrimaryKey(Integer fileId);

    int updateByPrimaryKeySelective(FileInfo record);

    int updateByPrimaryKey(FileInfo record);

    //重复文件名判断
    int selectFileCountByFileName(String filename);

    //判断文件是否未修改
    int judgeIfFileChanged(@Param("partnerNo")Integer partnerNo,@Param("fileName")String fileName);
}