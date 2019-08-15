package com.tsbg.ecosys.service;



import com.tsbg.ecosys.model.Eccontacts;

import java.util.List;


public interface EccontactsService
{
    List<Eccontacts> selectContacts(Integer partnerNo);

}
