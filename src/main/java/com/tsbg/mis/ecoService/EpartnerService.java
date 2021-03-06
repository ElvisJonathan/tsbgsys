package com.tsbg.mis.ecoService;

import com.tsbg.mis.ecoModel.Eccontacts;
import com.tsbg.mis.ecoModel.Epartner;
import com.tsbg.mis.util.PageRequest;
import com.tsbg.mis.util.PageResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EpartnerService {

    //管理员隐藏/取消隐藏个别公司
    int updateByCid(@Param("status") int status, @Param("cid") int cid);

    //查询公司列表
    List<Epartner> findAll();

    /**
     * 分页查询接口
     * 这里统一封装了分页请求和结果，避免直接引入具体框架的分页对象, 如MyBatis或JPA的分页对象
     * 从而避免因为替换ORM框架而导致服务层、控制层的分页接口也需要变动的情况，替换ORM框架也不会
     * 影响服务层以上的分页接口，起到了解耦的作用
     * 如果只需要单一的分页则不需要后面的实体类传参，只需要传pageRequest
     */
    PageResult findPage(@Param("pageRequest") PageRequest pageRequest, @Param("epartner") Epartner epartner,
                        @Param("eccontacts") Eccontacts eccontacts);

    List<Epartner> selectCinfoBypartnerCname(String partnerCname);
    List<Epartner> selectCinfoBypartnerCproduct(String partnerCproduct);
    List<Epartner> selectCinfoBypartnerCregion(String partnerCregion);
    List<Epartner> selectCinfoBypartnerCindustry(String partnerCindustry);
    List<Epartner> selectCinfo(Epartner ecinfo);

    int insertSelective(Epartner record);

    int updateByPrimaryKeySelective(Epartner record);

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

    Epartner selectByPrimaryKey(Integer partnerNo);
    //根据查询条件导出Excel
    List<Epartner> selectByPrimaryKeyl(Epartner epartner);
    //查询全部导出Excel
    List<Epartner> selectepartnerExcellAll();

    int deleteByPrimaryKey(Integer partnerNo);

    List<String> selectColumnName();

    List<String> selectColumnName2();

    List<String> selectColumnName3();
}
