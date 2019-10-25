package com.tsbg.mis.ecoService;



import com.tsbg.mis.ecoModel.Eccontacts;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface EccontactsService {

    List<Eccontacts> selectContacts(Integer partnerNo);

    int insertSelective(Eccontacts record);

    //用合作伙伴编号进行修改
    int updateByPartnerNoSelective(Eccontacts record);

    //管理员删除公司（软删除）
    int updateStatusByCid(int partnerNo);

    List<Eccontacts> selectByPartnerNo(Integer partnerNo);
    int updateByCid(@Param("status") int status, @Param("cid") int cid);
    //导出Excel查询所有
    List<Eccontacts> selecteccontactsExcellAll();

    int deleteByPrimaryKey3(Integer partnerNo);
    //根据查询条件导出Excel
    List<Eccontacts> selectEccontactsByCidl(Integer cid);
}
