package com.tsbg.ecosys.powerMapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PermissionMapper2 {

    List<String> selectPermission();
}
