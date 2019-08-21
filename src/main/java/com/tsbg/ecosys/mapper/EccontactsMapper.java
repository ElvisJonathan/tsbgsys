package com.tsbg.ecosys.mapper;


import com.tsbg.ecosys.model.Eccontacts;
import com.tsbg.ecosys.model.example.EccontactsExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    List<Eccontacts> selectByExample(EccontactsExample example);

    long countByExample(EccontactsExample example);

    int deleteByExample(EccontactsExample example);

    int updateByExampleSelective(@Param("record") Eccontacts record, @Param("example") EccontactsExample example);

    int updateByExample(@Param("record") Eccontacts record, @Param("example") EccontactsExample example);

    /*//分页查询公司联系人信息
    List<Eccontacts> selectecPage();*/

    public List<Eccontacts> selectEccontactsByCid(Integer cid);
}