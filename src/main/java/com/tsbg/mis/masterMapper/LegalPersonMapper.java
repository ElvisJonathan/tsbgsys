package com.tsbg.mis.masterMapper;

import com.tsbg.mis.masterModel.LegalPerson;

public interface LegalPersonMapper {
    int deleteByPrimaryKey(Integer legalPersonId);

    int insert(LegalPerson record);

    int insertSelective(LegalPerson record);

    LegalPerson selectByPrimaryKey(Integer legalPersonId);

    int updateByPrimaryKeySelective(LegalPerson record);

    int updateByPrimaryKey(LegalPerson record);
}