package com.tsbg.ecosys.ecoServiceImpl;


import com.tsbg.ecosys.ecoMapper.EccontactsMapper;
import com.tsbg.ecosys.ecoModel.Eccontacts;
import com.tsbg.ecosys.ecoService.EccontactsService;
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

    @Override
    public List<Eccontacts> selectEccontactsByCidl(Integer cid) {
        return eccontactsMapper.selectEccontactsByCidl(cid);
    }
}
