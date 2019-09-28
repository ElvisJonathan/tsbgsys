package com.tsbg.ecosys.mapper;

import com.tsbg.ecosys.model.QHandle;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QHandleMapper {
    List<QHandle> selectqHandleall();
}
