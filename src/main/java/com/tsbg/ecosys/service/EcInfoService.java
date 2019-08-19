package com.tsbg.ecosys.service;

import com.tsbg.ecosys.model.EcInfo;
import com.tsbg.ecosys.util.PageRequest;
import com.tsbg.ecosys.util.PageResult;

import java.util.List;

public interface EcInfoService {

    //管理员隐藏个别公司
    int updateByCid(int cid);

    //查询公司列表
    List<EcInfo> findAll();

    /**
     * 分页查询接口
     * 这里统一封装了分页请求和结果，避免直接引入具体框架的分页对象, 如MyBatis或JPA的分页对象
     * 从而避免因为替换ORM框架而导致服务层、控制层的分页接口也需要变动的情况，替换ORM框架也不会
     * 影响服务层以上的分页接口，起到了解耦的作用
     */
    PageResult findPage(PageRequest pageRequest);


    List<EcInfo> selectCinfoBypartnerCname(String partnerCname);
    List<EcInfo> selectCinfoBypartnerCproduct(String partnerCproduct);
    List<EcInfo> selectCinfoBypartnerCregion(String partnerCregion);
    List<EcInfo> selectCinfoBypartnerCindustry(String partnerCindustry);
    List<EcInfo> selectCinfo(EcInfo ecinfo);

    int insertSelective(EcInfo record);
}
