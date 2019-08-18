package com.tsbg.ecosys.serviceImpl;

import com.tsbg.ecosys.dto.EcTotalDto;
import com.tsbg.ecosys.dto.EcTotalListDto;
import com.tsbg.ecosys.dto.condition.EcooperationConditionDto;
import com.tsbg.ecosys.mapper.EcInfoMapper;
import com.tsbg.ecosys.mapper.EccontactsMapper;
import com.tsbg.ecosys.mapper.EcooperationMapper;
import com.tsbg.ecosys.model.EcInfo;
import com.tsbg.ecosys.model.Eccontacts;
import com.tsbg.ecosys.model.Ecooperation;
import com.tsbg.ecosys.model.example.EcInfoExample;
import com.tsbg.ecosys.model.example.EccontactsExample;
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
    private EcInfoMapper ecInfoMapper;

    @Autowired
    private EccontactsMapper eccontactsMapper;

    @Autowired
    private EcooperationMapper ecooperationMapper;

    private static final Logger LOG = LoggerFactory.getLogger(com.tsbg.ecosys.service.EcooperationService.class);

    //查询合作伙伴信息
    public List<EcTotalDto> getEcooperationList() {
        EcInfoExample example = new EcInfoExample();
        example.createCriteria();
        List<EcInfo> ecInfoList = ecInfoMapper.selectByExample(example);
        List<EcTotalDto> ecTotalDtoList = new ArrayList<>();
        EcTotalDto ecTotalDto = null;
        for (EcInfo ecInfo : ecInfoList) {
            ecTotalDto = new EcTotalDto();
            ecTotalDto.setEcInfo(ecInfo);
            EccontactsExample example1 = new EccontactsExample();
            example1.createCriteria().andPartnerNoEqualTo(ecInfo.getCid());
            List<Eccontacts> eccontactsList = eccontactsMapper.selectByExample(example1);
            if (eccontactsList != null && eccontactsList.size() != 0) {
                ecTotalDto.setEccontacts(eccontactsList.get(0));
            }

            EcooperationExample example2 = new EcooperationExample();
            example2.createCriteria().andPartnerNoEqualTo(ecInfo.getCid());
            List<Ecooperation> ecooperationList = ecooperationMapper.selectByExample(example2);
            if (ecooperationList != null && ecooperationList.size() != 0) {
                ecTotalDto.setEcooperation(ecooperationList.get(0));
            }
            ecTotalDtoList.add(ecTotalDto);

        }
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
        Ecooperation ecooperation = getByPrimaryKey(ecTotalDto.getEcooperation().getCoid());
        Date createTime = ecooperation.getCreateTime();
        BeanUtils.copyProperties(ecTotalDto.getEcooperation(), ecooperation);
        ecooperation.setCreateTime(createTime);
        ecooperation.setUpdateTime(new Date());
        updateByPrimaryKey(ecooperation);

        EcInfo ecInfo = ecInfoMapper.selectByPrimaryKey(ecTotalDto.getEcInfo().getCid());
        Date createTime1 = ecInfo.getCreateTime();
        BeanUtils.copyProperties(ecTotalDto.getEcInfo(), ecInfo);
        ecInfo.setCreateTime(createTime1);
        ecInfo.setUpdateTime(new Date());
        ecInfoMapper.updateByPrimaryKey(ecInfo);

        Eccontacts eccontacts = eccontactsMapper.selectByPrimaryKey(ecTotalDto.getEccontacts().getCcid());
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
