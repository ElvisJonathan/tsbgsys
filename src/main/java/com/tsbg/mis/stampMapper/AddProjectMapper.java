package com.tsbg.mis.stampMapper;

import com.tsbg.mis.powerModel.FileInfo;
import com.tsbg.mis.stampVo.AddProjectVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AddProjectMapper {

    //根据当前登录人的user_code查询员工信息
    AddProjectVo selectUserInfoByUserCode(String userCode);

    //根据工号查询员工是否存在
    int selectUserCode(String userCode);

    //查询所有厂区
    List<AddProjectVo> selectFactory(Map<String, Object> map);

    //查询所有事业群
    List<AddProjectVo> selectBg(Map<String, Object> map);

    //查询所有事业处
    List<AddProjectVo> selectBu(Map<String, Object> map);

    //查询所有印章类别
    List<AddProjectVo> selectTypeName(Map<String, Object> map);

    //插入用印信息
    void insert(AddProjectVo addProjectVo);

    //查询用印信息总条数
    int selectCountApplyNum();

    //查询用印信息表中项目编号最后一条数据
    String selectLastApplyNum();

    //查询最后一条成功插入的stamp_use_id
    int selectLastStampUseId();

    //插入上传文件信息
    int insertFileInfo(FileInfo record);

}
