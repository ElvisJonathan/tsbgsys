package com.tsbg.ecosys.mapper;


import com.tsbg.ecosys.model.Eccontacts;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EccontactsMapper {
    int deleteByPrimaryKey(Integer ccid);

    int insert(Eccontacts record);

    int insertSelective(Eccontacts record);

    Eccontacts selectByPrimaryKey(Integer ccid);

    int updateByPrimaryKeySelective(Eccontacts record);

    int updateByPrimaryKey(Eccontacts record);

    List<Eccontacts> selectContacts(Integer partnerNo);

   }