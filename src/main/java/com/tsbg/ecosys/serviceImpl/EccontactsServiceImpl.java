package com.tsbg.ecosys.serviceImpl;


import com.tsbg.ecosys.mapper.EccontactsMapper;
import com.tsbg.ecosys.model.Eccontacts;
import com.tsbg.ecosys.service.EccontactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EccontactsServiceImpl implements EccontactsService {

    @Autowired
    private EccontactsMapper ecContacts;

    @Override
    public List<Eccontacts> selectContacts(Integer partnerNo) {
        return ecContacts.selectContacts(partnerNo);
    }

}
