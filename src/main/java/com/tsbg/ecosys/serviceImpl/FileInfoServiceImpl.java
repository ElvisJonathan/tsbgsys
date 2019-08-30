package com.tsbg.ecosys.serviceImpl;

import com.tsbg.ecosys.mapper.FileInfoMapper;
import com.tsbg.ecosys.model.FileInfo;
import com.tsbg.ecosys.service.FileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileInfoServiceImpl implements FileInfoService {

    @Autowired
    private FileInfoMapper fileInfoMapper;

    @Override
    public int insertSelective(FileInfo record) {
        return fileInfoMapper.insertSelective(record);
    }

    @Override
    public int selectFileCountByFileName(String filename) {
        return fileInfoMapper.selectFileCountByFileName(filename);
    }

    @Override
    public int judgeIfFileChanged(Integer partnerNo, String fileName) {
        return fileInfoMapper.judgeIfFileChanged(partnerNo,fileName);
    }
}
