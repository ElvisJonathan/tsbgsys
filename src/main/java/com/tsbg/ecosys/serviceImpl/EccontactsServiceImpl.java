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
    private EccontactsMapper eccontactsMapper;


    @Override
    public List<Eccontacts> selectContacts(Integer partnerNo) {
        return eccontactsMapper.selectContacts(partnerNo);

    }

    @Override
    public int insertSelective(Eccontacts record) {
        return eccontactsMapper.insertSelective(record);
    }

    @Override
    public int updateByPartnerNoSelective(Eccontacts record) {
        return eccontactsMapper.updateByPartnerNoSelective(record);
    }

    @Override
    public int updateStatusByCid(int partnerNo) {
        return eccontactsMapper.updateStatusByCid(partnerNo);
    }

    @Override
    public List<Eccontacts> selectByPartnerNo(Integer partnerNo) {
        return eccontactsMapper.selectByPartnerNo(partnerNo);
    }

    @Override
    public int updateByCid(int status, int cid) {
        return eccontactsMapper.updateByCid(status,cid);
    }

    @Override
    public List<Eccontacts> selecteccontactsExcellAll() {
        return eccontactsMapper.selecteccontactsExcellAll();
    }

    @Override
    public int deleteByPrimaryKey3(Integer partnerNo) {
        return eccontactsMapper.deleteByPrimaryKey3(partnerNo);
    }
}
