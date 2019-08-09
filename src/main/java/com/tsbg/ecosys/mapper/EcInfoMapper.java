package com.tsbg.ecosys.mapper;

import com.tsbg.ecosys.model.EcInfo;
import org.apache.ibatis.annotations.Mapper;

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
}