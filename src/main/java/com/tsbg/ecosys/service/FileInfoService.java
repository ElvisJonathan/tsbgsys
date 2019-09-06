package com.tsbg.ecosys.service;

import com.tsbg.ecosys.model.FileInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface FileInfoService {

    int insertSelective(FileInfo record);

    //重复文件名判断
    int selectFileCountByFileName(String filename,Integer partnerNo);

    //判断文件是否未修改
    int judgeIfFileChanged(@Param("partnerNo")Integer partnerNo, @Param("fileName")String fileName);

    //查找当前文件的ID和更新时间
    FileInfo selectIDandDate();

    //查询当前文件编号在表中是否存在
    int selectIfExistId(int fileId);

    //更新最新的文件记录
    int updateRecord(@Param("partnerNo")int partnerNo,@Param("updater")String updater,@Param("fileId")int fileId);

    //通过文件名查询文件路径
    String selectRealPathByName(String fileName);

    //根据公司编号查询文件列表
    List<String> selectFileListByNo(Integer partnerNo);

    //根据公司编号查询当前文件列表对应的编号
    List<Integer> selectFileNoByNo(Integer partnerNo);

    //根据文件名查询当前文件编号
    List<Integer> selectFileIdByFileName(String fileName);

    //根据文件编号修改文件状态
    int updateFileStatusByFileNo(Integer fileNo);

    //根据上传时的文件名查询文件状态
    List<Integer> selectFileStatusByFileName(String fileName,Integer partnerNo);
}
