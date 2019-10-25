package com.tsbg.mis.ecoMapper;


import com.tsbg.mis.ecoModel.Eccontacts;
import com.tsbg.mis.ecoModel.example.EccontactsExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EccontactsMapper {
    int deleteByPrimaryKey(Integer contactId);

    int deleteByPrimaryKey3(Integer partnerNo);

    int insert(Eccontacts record);

    int insertSelective(Eccontacts record);

    Eccontacts selectByPrimaryKey(Integer contactId);

    int updateByPrimaryKeySelective(Eccontacts record);

    int updateByPrimaryKey(Eccontacts record);

    List<Eccontacts> selectContacts(Integer partnerNo);

    List<Eccontacts> selectByExample(EccontactsExample example);

    long countByExample(EccontactsExample example);

    int deleteByExample(EccontactsExample example);

    int updateByExampleSelective(@Param("record") Eccontacts record, @Param("example") EccontactsExample example);

    int updateByExample(@Param("record") Eccontacts record, @Param("example") EccontactsExample example);

    List<Eccontacts> selectEccontactsByCid(Integer cid);
    //用合作伙伴编号进行修改
    int updateByPartnerNoSelective(Eccontacts record);
    //管理员删除公司（软删除）
    int updateStatusByCid(int partnerNo);

    List<Eccontacts> selectByPartnerNo(Integer partnerNo);

    int updateByCid(@Param("status") int status, @Param("cid") int cid);

    //查询全部导出Excel
    List<Eccontacts> selecteccontactsExcellAll();
    //根据查询条件导出Excel
    List<Eccontacts> selectEccontactsByCidl(Integer cid);
}