package com.tsbg.ecosys.mapper;

import com.tsbg.ecosys.model.EcInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EcInfoMapper {
    int deleteByPrimaryKey(Integer cid);

    int insert(EcInfo record);

    int insertSelective(EcInfo record);

    EcInfo selectByPrimaryKey(Integer cid);

    int updateByPrimaryKeySelective(EcInfo record);

    int updateByPrimaryKey(EcInfo record);

    //管理员隐藏个别公司
    int updateByCid(int cid);

    //查询公司列表
    List<EcInfo> selectAll();

    //分页查询公司列表
    List<EcInfo> selectPage();

    //查询公司信息
    List<EcInfo>selectCinfo(EcInfo ecInfo);
}