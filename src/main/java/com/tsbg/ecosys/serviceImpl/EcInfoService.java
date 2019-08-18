package com.tsbg.ecosys.serviceImpl;

import com.tsbg.ecosys.dto.condition.EcInfoConditionDto;
import com.tsbg.ecosys.mapper.EcInfoMapper;
import com.tsbg.ecosys.model.EcInfo;
import com.tsbg.ecosys.model.example.EcInfoExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author prince
 */
@Service
public class EcInfoService {

    @Autowired
    private EcInfoMapper ecInfoMapper;

    private static final Logger LOG = LoggerFactory.getLogger(com.tsbg.ecosys.service.EcInfoService.class);


    public void insertSelective(EcInfo ecInfo) {
        ecInfo.setCreateTime(new Date());
        ecInfoMapper.insertSelective(ecInfo);
    }

    /**
     * 主键修改
     *
     * @param ecInfo
     *
     * @return
     * @throws
     */
    public int updateByPrimaryKey(EcInfo ecInfo) throws Exception {
        return ecInfoMapper.updateByPrimaryKey(ecInfo);
    }

    /**
     * 主键修改
     *
     * @param ecInfo
     * @return
     * @throws
     */
    public int updateByPrimaryKeySelective(EcInfo ecInfo) throws Exception {
        return ecInfoMapper.updateByPrimaryKeySelective(ecInfo);
    }

    /**
     * 主键获取
     *
     * @param id
     * @return
     */
    public EcInfo getByPrimaryKey(int id) {
        return ecInfoMapper.selectByPrimaryKey(id);
    }


    public List<EcInfo> selectByCondition(EcInfoConditionDto condition) {
        EcInfoExample example = createExampleByCondition(condition);
        return ecInfoMapper.selectByExample(example);
    }

    /**
     * 生成查询条件对象
     *
     * @param condition
     * @return
     */
    private EcInfoExample createExampleByCondition(EcInfoConditionDto condition) {
        EcInfoExample example = new EcInfoExample();
        EcInfoExample.Criteria criteria = example.createCriteria();

        //===============Base Condition Start==============
        if (condition.getCid() != null) {
            criteria.andCidEqualTo(condition.getCid());
        }

        //===============Base Condition End==============



        return example;
    }


}
