package com.tsbg.ecosys.serviceImpl;

import com.tsbg.ecosys.dto.condition.EccontactsConditionDto;
import com.tsbg.ecosys.mapper.EccontactsMapper;
import com.tsbg.ecosys.model.Eccontacts;
import com.tsbg.ecosys.model.example.EccontactsExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class EccontactsService {

    @Autowired
    private EccontactsMapper eccontactsMapper;

    private static final Logger LOG = LoggerFactory.getLogger(com.tsbg.ecosys.service.EccontactsService.class);

    public void insertSelective(Eccontacts eccontacts) {
        eccontacts.setCreateTime(new Date());
        eccontactsMapper.insertSelective(eccontacts);
    }

    /**
     * 主键修改
     *
     * @param eccontacts
     * @return
     * @throws
     */
    public int updateByPrimaryKey(Eccontacts eccontacts) throws Exception {
        return eccontactsMapper.updateByPrimaryKey(eccontacts);
    }

    /**
     * 主键修改
     *
     * @param eccontacts
     * @return
     * @throws
     */
    public int updateByPrimaryKeySelective(Eccontacts eccontacts) throws Exception {
        return eccontactsMapper.updateByPrimaryKeySelective(eccontacts);
    }

    /**
     * 主键获取
     *
     * @param id
     * @return
     */
    public Eccontacts getByPrimaryKey(int id) {
        return eccontactsMapper.selectByPrimaryKey(id);
    }


    public List<Eccontacts> selectByCondition(EccontactsConditionDto condition) {
        EccontactsExample example = createExampleByCondition(condition);
        return eccontactsMapper.selectByExample(example);
    }

    /**
     * 生成查询条件对象
     *
     * @param condition
     * @return
     */
    private EccontactsExample createExampleByCondition(EccontactsConditionDto condition) {
        EccontactsExample example = new EccontactsExample();
        EccontactsExample.Criteria criteria = example.createCriteria();

        //===============Base Condition Start==============
        if (condition.getCcid() != null) {
            criteria.andCcidEqualTo(condition.getCcid());
        }

        //===============Base Condition End==============



        return example;
    }


}
