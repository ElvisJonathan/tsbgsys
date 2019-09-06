package com.tsbg.ecosys.serviceImpl;

import com.tsbg.ecosys.mapper.FileInfoMapper;
import com.tsbg.ecosys.model.FileInfo;
import com.tsbg.ecosys.service.FileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FileInfoServiceImpl implements FileInfoService {

    @Autowired
    private FileInfoMapper fileInfoMapper;

    @Override
    public int insertSelective(FileInfo record) {
        return fileInfoMapper.insertSelective(record);
    }

    @Override
    public int selectFileCountByFileName(String filename, Integer partnerNo) {
        return fileInfoMapper.selectFileCountByFileName(filename,partnerNo);
    }

    @Override
    public int judgeIfFileChanged(Integer partnerNo, String fileName) {
        return fileInfoMapper.judgeIfFileChanged(partnerNo,fileName);
    }

    @Override
    public FileInfo selectIDandDate() {
        return fileInfoMapper.selectIDandDate();
    }

    @Override
    public int selectIfExistId(int fileId) {
        return fileInfoMapper.selectIfExistId(fileId);
    }

    @Override
    public int updateRecord(int partnerNo, String updater, int fileId) {
        return fileInfoMapper.updateRecord(partnerNo,updater,fileId);
    }

    @Override
    public String selectRealPathByName(String fileName) {
        return fileInfoMapper.selectRealPathByName(fileName);
    }

    @Override
    public List<String> selectFileListByNo(Integer partnerNo) {
        return fileInfoMapper.selectFileListByNo(partnerNo);
    }

    @Override
    public List<Integer> selectFileNoByNo(Integer partnerNo) {
        return fileInfoMapper.selectFileNoByNo(partnerNo);
    }

    @Override
    public List<Integer> selectFileIdByFileName(String fileName) {
        return fileInfoMapper.selectFileIdByFileName(fileName);
    }

    @Override
    public int updateFileStatusByFileNo(Integer fileNo) {
        return fileInfoMapper.updateFileStatusByFileNo(fileNo);
    }

    @Override
    public List<Integer> selectFileStatusByFileName(String fileName, Integer partnerNo) {
        return fileInfoMapper.selectFileStatusByFileName(fileName,partnerNo);
    }
}
