package com.tsbg.ecosys.assetsMapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AssetsTypeMapper {

    List<String> selectAssetsType();
}
