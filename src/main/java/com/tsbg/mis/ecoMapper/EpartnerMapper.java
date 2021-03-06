package com.tsbg.mis.ecoMapper;

import com.tsbg.mis.ecoModel.Eccontacts;
import com.tsbg.mis.ecoModel.Epartner;
import com.tsbg.mis.ecoModel.example.EcInfoExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EpartnerMapper {

    int deleteByPrimaryKey(Integer partnerNo);

    int insert(Epartner record);

    int insertSelective(Epartner record);

    Epartner selectByPrimaryKey(Integer partnerNo);

    int updateByPrimaryKeySelective(Epartner record);

    int updateByPrimaryKey(Epartner record);

    //管理员隐藏/取消隐藏个别公司
    int updateByCid(@Param("status") int status, @Param("cid") int cid);

    //查询公司列表
    List<Epartner> selectAll();

    //分页查询公司列表 + 搜索(如果不用搜索则不需要加参数)
    List<Epartner> selectPage(@Param("epartner")Epartner epartner, @Param("eccontacts")Eccontacts eccontacts);

    //查询公司信息
    List<Epartner> selectCinfoBypartnerCname(String partnerCname);
    List<Epartner> selectCinfoBypartnerCproduct(String partnerCproduct);
    List<Epartner> selectCinfoBypartnerCregion(String partnerCregion);
    List<Epartner> selectCinfoBypartnerCindustry(String partnerCindustry);
    List<Epartner> selectCinfo(Epartner ecinfo);

    List<Epartner> selectByExample(EcInfoExample example);

    long countByExample(EcInfoExample example);

    int deleteByExample(EcInfoExample example);

    int updateByExampleSelective(@Param("record") Epartner record, @Param("example") EcInfoExample example);

    int updateByExample(@Param("record") Epartner record, @Param("example") EcInfoExample example);

    //管理员删除公司（软删除）
    int updateStatusByCid(int cid);

    //查询新插入记录的ID
    int selectID();

    //查询当前公司的文件详情
    List<String> selectFileByParNo(Integer partnerNo);

    //查询当前公司的文件详情
    List<String> selectFileByParNo2(Integer partnerNo);
    //根据partnerNo删除文件
    int deleteFileByParNo(@Param("partnerNo") Integer partnerNo);

    //下载文件记录下载者
    int logDownloader(@Param("userCode") String userCode, @Param("partnerNo") Integer partnerNo);
    //查询全部导出Excel
    List<Epartner> selectepartnerExcellAll();
    //根据查询条件导出Excel
    List<Epartner> selectByPrimaryKeyl(Epartner epartner);

    List<String> selectColumnName();

    List<String> selectColumnName2();

    List<String> selectColumnName3();
}