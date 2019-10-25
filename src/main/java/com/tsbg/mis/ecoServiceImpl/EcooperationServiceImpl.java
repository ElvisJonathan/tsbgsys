package com.tsbg.mis.ecoServiceImpl;

import com.tsbg.mis.ecoMapper.EcooperationMapper;
import com.tsbg.mis.ecoModel.Ecooperation;
import com.tsbg.mis.ecoService.EcooperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EcooperationServiceImpl implements EcooperationService {

    @Autowired
    private EcooperationMapper ecooperationMapper;

    @Override
    public int insertSelective(Ecooperation record) {
        return ecooperationMapper.insertSelective(record);
    }

    @Override
    public List<Ecooperation> selectCooinfo(Integer partnerNo) {

        return ecooperationMapper.selectCooinfo(partnerNo);
    }

    @Override
    public int updateByPartnerNoSelective(Ecooperation record) {
        return ecooperationMapper.updateByPartnerNoSelective(record);
    }

    @Override
    public int updateStatusByCid(int partnerNo) {
        return ecooperationMapper.updateStatusByCid(partnerNo);
    }

    @Override
    public List<Ecooperation> selectByPartnerNo(Integer partnerNo) {
        return ecooperationMapper.selectByPartnerNo(partnerNo);
    }

    @Override
    public int updateByCid(int status, int cid) {
        return ecooperationMapper.updateByCid(status,cid);
    }

    @Override
    public List<Ecooperation> selectecooperationsExcellAll() {
        return ecooperationMapper.selectecooperationsExcellAll();
    }

    @Override
    public int deleteByPrimaryKey2(Integer partnerNo) {
        return ecooperationMapper.deleteByPrimaryKey2(partnerNo);
    }

    @Override
    public List<Ecooperation> selectEcooperationByCidl(Integer cid) {
        return ecooperationMapper.selectEcooperationByCidl(cid);
    }
}
