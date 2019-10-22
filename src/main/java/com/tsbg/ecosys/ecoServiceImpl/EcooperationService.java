package com.tsbg.ecosys.ecoServiceImpl;

import com.tsbg.ecosys.ecoDto.EcTotalDto;
import com.tsbg.ecosys.ecoDto.EcTotal_Excel;
import com.tsbg.ecosys.ecoDto.condition.EcooperationConditionDto;
import com.tsbg.ecosys.ecoMapper.EccontactsMapper;
import com.tsbg.ecosys.ecoMapper.EcooperationMapper;
import com.tsbg.ecosys.ecoMapper.EpartnerMapper;
import com.tsbg.ecosys.ecoModel.Epartner;
import com.tsbg.ecosys.ecoModel.Eccontacts;
import com.tsbg.ecosys.ecoModel.Ecooperation;
import com.tsbg.ecosys.ecoModel.example.EcooperationExample;
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

    private static final Logger LOG = LoggerFactory.getLogger(com.tsbg.ecosys.ecoService.EcooperationService.class);
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

    //根据查询条件导出(根据epartner表中的四个条件ep.partner_name,ep.partner_product,ep.partner_region,ep.partner_industry from eccontacts et,ecooperation ecp,epartner)
    public List<Epartner> getEcooperationListall(Epartner epartner) {
        List<Epartner> epartners = epartnerMapper.selectByPrimaryKeyl(epartner);
        return epartners;
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
