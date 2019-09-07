package com.tsbg.ecosys.serviceImpl;

import com.tsbg.ecosys.dto.EcTotalDto;
import com.tsbg.ecosys.dto.EcTotalDtol;
import com.tsbg.ecosys.dto.EcTotal_Excel;
import com.tsbg.ecosys.dto.condition.EcooperationConditionDto;
import com.tsbg.ecosys.mapper.EccontactsMapper;
import com.tsbg.ecosys.mapper.EcooperationMapper;
import com.tsbg.ecosys.mapper.EpartnerMapper;
import com.tsbg.ecosys.model.Epartner;
import com.tsbg.ecosys.model.Eccontacts;
import com.tsbg.ecosys.model.Ecooperation;
import com.tsbg.ecosys.model.example.EcooperationExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author prince
 */
@Service
public class EcooperationService {

    @Autowired
    private EpartnerMapper epartnerMapper;

    @Autowired
    private EccontactsMapper eccontactsMapper;

    @Autowired
    private EcooperationMapper ecooperationMapper;

    private static final Logger LOG = LoggerFactory.getLogger(com.tsbg.ecosys.service.EcooperationService.class);
    //查询合作伙伴信息
    public List<EcTotalDto> getEcooperationList(Integer cid) {
        //根据cid查询出来
        Epartner epartner1 = epartnerMapper.selectByPrimaryKey(cid);
        //根据cid查找eccontact
        List<Eccontacts> eccontacts = eccontactsMapper.selectEccontactsByCid(cid);
        //根据cid查在查询所有的Ecooperation
        List<Ecooperation> ecooperations = ecooperationMapper.selectEcooperationByCid(cid);
        EcTotalDto ecTotalDto = new EcTotalDto();
        ecTotalDto.setEpartner(epartner1);
        if(eccontacts != null && eccontacts.size()>0){
            ecTotalDto.setEccontacts(eccontacts);
        }
        if(ecooperations != null && ecooperations.size()>0){
            ecTotalDto.setEcooperation(ecooperations);
        }
        List<EcTotalDto> ecTotalDtoList = new ArrayList<>();
        ecTotalDtoList.add(ecTotalDto);
        return ecTotalDtoList;
    }

    //根据查询条件导出
    public List<EcTotalDtol> getEcooperationListall(Epartner epartner) {
        int cid = epartner.getPartnerNo();
        Epartner epartner1 = epartnerMapper.selectByPrimaryKeyl(epartner);
        //根据cid查找eccontact  selectEccontactsByCidl();
        List<Eccontacts> eccontacts = eccontactsMapper.selectEccontactsByCidl(cid);
        //根据cid查在查询所有的Ecooperation
        List<Ecooperation> ecooperations = ecooperationMapper.selectEcooperationByCidl(cid);
        EcTotalDtol ecTotalDtol = new EcTotalDtol();
        ecTotalDtol.setEpartner(epartner1);
       if (eccontacts != null && eccontacts.size() > 0) {
            ecTotalDtol.setEccontacts(eccontacts.get(0));
        }
        if (ecooperations != null && ecooperations.size() > 0) {
            ecTotalDtol.setEcooperation(ecooperations.get(0));
        }
        List<EcTotalDtol> ecTotalDtoList = new ArrayList<>();
        ecTotalDtoList.add(ecTotalDtol);
        return ecTotalDtoList;
    }

    //全部导出Excel（只导出status=0 and del_status=0）
    public EcTotal_Excel getEpartnerList() {
        List<Epartner> epartner1 = epartnerMapper.selectepartnerExcellAll();
        List<Eccontacts> eccontacts = eccontactsMapper.selecteccontactsExcellAll();
        List<Ecooperation> ecooperations = ecooperationMapper.selectecooperationsExcellAll();
        EcTotal_Excel ecTotal_excel = new EcTotal_Excel();
        ecTotal_excel.setEpartnerList(epartner1);
        ecTotal_excel.setEccontactsList(eccontacts);
        ecTotal_excel.setEcooperationList(ecooperations);
        return ecTotal_excel;
    }

    public void insertSelective(Ecooperation ecooperation) {
        ecooperation.setCreateTime(new Date());
        ecooperationMapper.insertSelective(ecooperation);
    }

    /**
     * 主键修改
     *
     * @param ecooperation
     * @return
     * @throws
     */
    public int updateByPrimaryKey(Ecooperation ecooperation) throws Exception {
        return ecooperationMapper.updateByPrimaryKey(ecooperation);
    }

    /**
     * 主键修改
     *
     * @param ecooperation
     * @return
     * @throws
     */
    public int updateByPrimaryKeySelective(Ecooperation ecooperation) throws Exception {
        return ecooperationMapper.updateByPrimaryKeySelective(ecooperation);
    }

    /**
     * 主键获取
     *
     * @param id
     * @return
     */
    public Ecooperation getByPrimaryKey(int id) {
        return ecooperationMapper.selectByPrimaryKey(id);
    }


    public List<Ecooperation> selectByCondition(EcooperationConditionDto condition) {
        EcooperationExample example = createExampleByCondition(condition);
        return ecooperationMapper.selectByExample(example);
    }

    /**
     * 生成查询条件对象
     *
     * @param condition
     * @return
     */
    private EcooperationExample createExampleByCondition(EcooperationConditionDto condition) {
        EcooperationExample example = new EcooperationExample();
        EcooperationExample.Criteria criteria = example.createCriteria();

        if (condition.getCoid() != null) {
            criteria.andCoidEqualTo(condition.getCoid());
        }
        return example;
    }


}
