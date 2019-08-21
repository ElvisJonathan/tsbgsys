package com.tsbg.ecosys.service;

import com.tsbg.ecosys.model.EcInfo;
import com.tsbg.ecosys.util.PageRequest;
import com.tsbg.ecosys.util.PageResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EcInfoService {

    //管理员隐藏/取消隐藏个别公司
    int updateByCid(@Param("status") int status,@Param("cid") int cid);

    //查询公司列表
    List<EcInfo> findAll();

    /**
     * 分页查询接口
     * 这里统一封装了分页请求和结果，避免直接引入具体框架的分页对象, 如MyBatis或JPA的分页对象
     * 从而避免因为替换ORM框架而导致服务层、控制层的分页接口也需要变动的情况，替换ORM框架也不会
     * 影响服务层以上的分页接口，起到了解耦的作用
     * 如果只需要单一的分页则不需要后面的实体类传参，只需要传pageRequest
     */
    PageResult findPage(@Param("pageRequest") PageRequest pageRequest, @Param("ecInfo") EcInfo ecInfo);


    List<EcInfo> selectCinfoBypartnerCname(String partnerCname);
    List<EcInfo> selectCinfoBypartnerCproduct(String partnerCproduct);
    List<EcInfo> selectCinfoBypartnerCregion(String partnerCregion);
    List<EcInfo> selectCinfoBypartnerCindustry(String partnerCindustry);
    List<EcInfo> selectCinfo(EcInfo ecinfo);

    int insertSelective(EcInfo record);

    int updateByPrimaryKeySelective(EcInfo record);
}
