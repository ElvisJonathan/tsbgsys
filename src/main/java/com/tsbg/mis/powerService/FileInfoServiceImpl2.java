package com.tsbg.mis.powerService;

import com.tsbg.mis.powerMapper.FileInfoMapper2;
import com.tsbg.mis.powerModel.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileInfoServiceImpl2 implements FileInfoService2 {

    @Autowired
    private FileInfoMapper2 fileInfoMapper;

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
    public String selectRealPathByName(String fileName, Integer partnerNo) {
        return fileInfoMapper.selectRealPathByName(fileName,partnerNo);
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

    @Override
    public Integer selectFileNo(Integer partnerNo, String fileName) {
        return fileInfoMapper.selectFileNo(partnerNo,fileName);
    }

    @Override
    public int updateDownloader(String userCode, Integer fileNo) {
        return fileInfoMapper.updateDownloader(userCode,fileNo);
    }

    @Override
    public List<FileInfo> selectFilePathByQuestionFeedBackId(Integer questionFeedbackId) {
        return null;
    }


    @Override
    //根据處理反饋编号查询当前文件列表对应的编号
    public List<Integer> selectFileNoByQuestionHandleId(Integer questionHandleId){
        return fileInfoMapper.selectFileNoByQuestionHandleId(questionHandleId);
    }


    //根據處理反饋編號修改文件狀態
    @Override
    public int updateAllFileStatusByQuestionHandleId(Integer questionHandleId){
        return fileInfoMapper.updateAllFileStatusByQuestionHandleId(questionHandleId);
    }


    //通過QuestionHandleId和文件名定位文件ID
    @Override
    public Integer selectFileNoByQuestionHandleIdAdnFileName(Integer questionHandleId,String fileName){
        return fileInfoMapper.selectFileNoByQuestionHandleIdAdnFileName(questionHandleId,fileName);
    }


    //通過QuestionFeedbackId和文件名定位文件ID
    @Override
    public Integer selectFileNoByQuestionFeedbackIdAdnFileName(Integer questionFeedbackId,String fileName){
        return fileInfoMapper.selectFileNoByQuestionFeedbackIdAdnFileName(questionFeedbackId,fileName);
    }




    //根據問題反饋id查詢文件名
    public List<FileInfo> selectFileNameByQuestionFeedBackId(Integer questionFeedbackId){
        return fileInfoMapper.selectFileNameByQuestionFeedBackId(questionFeedbackId);
    }


    //根據QuestionHandleId查詢文件名
    public List<FileInfo> selectFileNameByQuestionHandleId(Integer questionHandleId){
        return fileInfoMapper.selectFileNameByQuestionHandleId(questionHandleId);
    }


    //通過處理反饋id更新文件的狀態為刪除（實際未刪除）
    @Override
    public int UpdateFileByFileNameAndQuestionHandleId(String fileName,Integer questionHandleId){
        return fileInfoMapper.UpdateFileByFileNameAndQuestionHandleId(fileName,questionHandleId);
    }



    //通過問題反饋id更新文件的狀態為刪除（實際未刪除）
    @Override
    public int UpdateFileByFileNameAndQuestionFeedBackId(String fileName,Integer questionFeedbackId){
        return fileInfoMapper.UpdateFileByFileNameAndQuestionFeedBackId(fileName,questionFeedbackId);
    }


    @Override
    //通過文件名、反饋附件編號查詢文件附件
    public String selectRealPathByNameAndQuestionFeedBackId(String fileName,Integer questionFeedbackId){
        return fileInfoMapper.selectRealPathByNameAndQuestionFeedBackId(fileName,questionFeedbackId);
    }

    @Override
    //通過文件名、處理反饋附件編號查詢文件附件
    public String selectRealPathByNameAndQuestionHandleId(String fileName,Integer questionHandleId){
        return fileInfoMapper.selectRealPathByNameAndQuestionHandleId(fileName,questionHandleId);
    }
}
