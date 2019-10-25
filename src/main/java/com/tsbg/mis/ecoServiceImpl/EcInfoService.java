package com.tsbg.mis.ecoServiceImpl;

import com.tsbg.mis.ecoDto.condition.EcInfoConditionDto;
import com.tsbg.mis.ecoMapper.EpartnerMapper;
import com.tsbg.mis.ecoModel.Epartner;
import com.tsbg.mis.ecoModel.example.EcInfoExample;
import com.tsbg.mis.ecoService.EpartnerService;
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
    private EpartnerMapper epartnerMapper;

    private static final Logger LOG = LoggerFactory.getLogger(EpartnerService.class);


    public void insertSelective(Epartner epartner) {
        epartner.setCreateTime(new Date());
        epartnerMapper.insertSelective(epartner);
    }

    /**
     * 主键修改
     *
     * @param epartner
     *
     * @return
     * @throws
     */
    public int updateByPrimaryKey(Epartner epartner) throws Exception {
        return epartnerMapper.updateByPrimaryKey(epartner);
    }

    /**
     * 主键修改
     *
     * @param epartner
     * @return
     * @throws
     */
    public int updateByPrimaryKeySelective(Epartner epartner) throws Exception {
        return epartnerMapper.updateByPrimaryKeySelective(epartner);
    }

    /**
     * 主键获取
     *
     * @param id
     * @return
     */
    public Epartner getByPrimaryKey(int id) {
        return epartnerMapper.selectByPrimaryKey(id);
    }


    public List<Epartner> selectByCondition(EcInfoConditionDto condition) {
        EcInfoExample example = createExampleByCondition(condition);
        return epartnerMapper.selectByExample(example);
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

        if (condition.getCid() != null) {
            criteria.andCidEqualTo(condition.getCid());
        }
        return example;
    }


}
