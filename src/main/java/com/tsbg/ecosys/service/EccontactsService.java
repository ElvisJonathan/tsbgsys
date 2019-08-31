package com.tsbg.ecosys.service;



import com.tsbg.ecosys.model.Eccontacts;

import java.util.List;


public interface EccontactsService {

    List<Eccontacts> selectContacts(Integer partnerNo);

    int insertSelective(Eccontacts record);

    //用合作伙伴编号进行修改
    int updateByPartnerNoSelective(Eccontacts record);

    //管理员删除公司（软删除）
    int updateStatusByCid(int partnerNo);

    List<Eccontacts> selectByPartnerNo(Integer partnerNo);
}
