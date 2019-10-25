package com.tsbg.mis.assetsMapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AssetsTypeMapper {

    List<String> selectAssetsType();
}
