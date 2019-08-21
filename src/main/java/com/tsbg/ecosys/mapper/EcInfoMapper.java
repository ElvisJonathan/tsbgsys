package com.tsbg.ecosys.mapper;

import com.tsbg.ecosys.model.EcInfo;
import com.tsbg.ecosys.model.example.EcInfoExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EcInfoMapper {
    int deleteByPrimaryKey(Integer cid);

    int insert(EcInfo record);

    int insertSelective(EcInfo record);

    EcInfo selectByPrimaryKey(Integer cid);

    int updateByPrimaryKeySelective(EcInfo record);

    int updateByPrimaryKey(EcInfo record);

    //管理员隐藏/取消隐藏个别公司
    int updateByCid(@Param("status") int status,@Param("cid") int cid);

    //查询公司列表
    List<EcInfo> selectAll();

    //分页查询公司列表 + 搜索(如果不用搜索则不需要加参数)
    List<EcInfo> selectPage(EcInfo ecInfo);

    //查询公司信息
    List<EcInfo> selectCinfoBypartnerCname(String partnerCname);
    List<EcInfo> selectCinfoBypartnerCproduct(String partnerCproduct);
    List<EcInfo> selectCinfoBypartnerCregion(String partnerCregion);
    List<EcInfo> selectCinfoBypartnerCindustry(String partnerCindustry);
    List<EcInfo> selectCinfo(EcInfo ecinfo);

    List<EcInfo> selectByExample(EcInfoExample example);

    long countByExample(EcInfoExample example);

    int deleteByExample(EcInfoExample example);

    int updateByExampleSelective(@Param("record") EcInfo record, @Param("example") EcInfoExample example);

    int updateByExample(@Param("record") EcInfo record, @Param("example") EcInfoExample example);

    //管理员删除公司（软删除）
    int updateStatusByCid(int cid);
}