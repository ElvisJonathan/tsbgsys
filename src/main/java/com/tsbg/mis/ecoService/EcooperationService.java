package com.tsbg.mis.ecoService;

import com.tsbg.mis.ecoModel.Ecooperation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EcooperationService {

    int insertSelective(Ecooperation record);

    List<Ecooperation> selectCooinfo(Integer partnerNo);

    //用合作伙伴编号进行修改
    int updateByPartnerNoSelective(Ecooperation record);

    //管理员删除公司（软删除）
    int updateStatusByCid(int partnerNo);

    List<Ecooperation> selectByPartnerNo(Integer partnerNo);

    int updateByCid(@Param("status") int status, @Param("cid") int cid);
    //查询全部导出Excel
    List<Ecooperation> selectecooperationsExcellAll();

    int deleteByPrimaryKey2(Integer partnerNo);
    //根据查询条件导出Excel
    List<Ecooperation> selectEcooperationByCidl(Integer cid);
}
