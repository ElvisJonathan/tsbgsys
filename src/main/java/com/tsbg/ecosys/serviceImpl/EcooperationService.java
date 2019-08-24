package com.tsbg.ecosys.serviceImpl;

import com.tsbg.ecosys.dto.EcTotalDto;
import com.tsbg.ecosys.dto.EcTotalListDto;
import com.tsbg.ecosys.dto.condition.EcooperationConditionDto;
import com.tsbg.ecosys.mapper.EpartnerMapper;
import com.tsbg.ecosys.mapper.EccontactsMapper;
import com.tsbg.ecosys.mapper.EcooperationMapper;
import com.tsbg.ecosys.model.Epartner;
import com.tsbg.ecosys.model.Eccontacts;
import com.tsbg.ecosys.model.Ecooperation;
import com.tsbg.ecosys.model.example.EcooperationExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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
            ecTotalDto.setEccontacts(eccontacts.get(0));
        }
        if(ecooperations != null && ecooperations.size()>0){
            ecTotalDto.setEcooperation(ecooperations.get(0));
        }
        List<EcTotalDto> ecTotalDtoList = new ArrayList<>();
        ecTotalDtoList.add(ecTotalDto);

        return ecTotalDtoList;
    }

    //批量更新合作伙伴信息
    public void updateEcooperationList(EcTotalListDto ecTotalListDto) throws Exception {
        if (ecTotalListDto == null || ecTotalListDto.getEcTotalDtoList() == null || ecTotalListDto.getEcTotalDtoList().size() == 0) {
            return;
        }
        for (EcTotalDto ecTotalDto : ecTotalListDto.getEcTotalDtoList()) {
            updateEcooperation(ecTotalDto);
        }
    }


    //更新合作伙伴信息
    public void updateEcooperation(EcTotalDto ecTotalDto) throws Exception {
        Ecooperation ecooperation = getByPrimaryKey(ecTotalDto.getEcooperation().getCoopId());
        Date createTime = ecooperation.getCreateTime();
        BeanUtils.copyProperties(ecTotalDto.getEcooperation(), ecooperation);
        ecooperation.setCreateTime(createTime);
        ecooperation.setUpdateTime(new Date());
        updateByPrimaryKey(ecooperation);

        Epartner epartner = epartnerMapper.selectByPrimaryKey(ecTotalDto.getEpartner().getPartnerNo());
        Date createTime1 = epartner.getCreateTime();
        BeanUtils.copyProperties(ecTotalDto.getEpartner(), epartner);
        epartner.setCreateTime(createTime1);
        epartner.setUpdateTime(new Date());
        epartnerMapper.updateByPrimaryKey(epartner);

        Eccontacts eccontacts = eccontactsMapper.selectByPrimaryKey(ecTotalDto.getEccontacts().getContactId());
        Date createTime2 = eccontacts.getCreateTime();
        BeanUtils.copyProperties(ecTotalDto.getEccontacts(), eccontacts);
        eccontacts.setCreateTime(createTime2);
        eccontacts.setUpdateTime(new Date());
        eccontactsMapper.updateByPrimaryKey(eccontacts);

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

        //===============Base Condition Start==============
        if (condition.getCoid() != null) {
            criteria.andCoidEqualTo(condition.getCoid());
        }

        //===============Base Condition End==============


        return example;
    }


}
