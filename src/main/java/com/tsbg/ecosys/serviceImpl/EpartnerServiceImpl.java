package com.tsbg.ecosys.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tsbg.ecosys.mapper.EpartnerMapper;
import com.tsbg.ecosys.model.Epartner;
import com.tsbg.ecosys.service.EpartnerService;
import com.tsbg.ecosys.util.PageRequest;
import com.tsbg.ecosys.util.PageResult;
import com.tsbg.ecosys.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EpartnerServiceImpl implements EpartnerService {

    @Autowired
    private EpartnerMapper epartnerMapper;


    @Override
    public int updateByCid(int status, int cid) {
        return epartnerMapper.updateByCid(status,cid);
    }

    @Override
    public List<Epartner> findAll() {
        return epartnerMapper.selectAll();
    }

    @Override
    public List<Epartner> selectCinfo(Epartner ecinfo)
    {
        return epartnerMapper.selectCinfo(ecinfo);
    }

    @Override
    public int insertSelective(Epartner record) {
        return epartnerMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(Epartner record) {
        return epartnerMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateStatusByCid(int cid) {
        return epartnerMapper.updateStatusByCid(cid);
    }

    @Override
    public int selectID() {
        return epartnerMapper.selectID();
    }

    @Override
    public List<String> selectFileByParNo(Integer partnerNo) {
        return epartnerMapper.selectFileByParNo(partnerNo);
    }

    @Override
    public int deleteFileByParNo(Integer partnerNo) {
        return epartnerMapper.deleteFileByParNo(partnerNo);
    }

    @Override
    public List<Epartner> selectCinfoBypartnerCindustry(String partnerCindustry) {

        return epartnerMapper.selectCinfoBypartnerCindustry(partnerCindustry);
    }
    @Override
    public List<Epartner> selectCinfoBypartnerCregion(String partnerCregion) {

        return epartnerMapper.selectCinfoBypartnerCregion(partnerCregion);
    }
    @Override
    public List<Epartner> selectCinfoBypartnerCproduct(String partnerCproduct) {
        return epartnerMapper.selectCinfoBypartnerCproduct(partnerCproduct);
    }
    @Override
    public List<Epartner> selectCinfoBypartnerCname(String partnerCname) {
        return epartnerMapper.selectCinfoBypartnerCname(partnerCname);
    }

    /**
     * 调用分页插件完成分页
     */
    private PageInfo<Epartner> getPageInfo(PageRequest pageRequest, Epartner epartner) {
        int pageIndex = pageRequest.getPageIndex();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageIndex, pageSize);
        //此处实现了加搜索条件情况下的分页,如果不需要搜索则不需要加实体类传参
        List<Epartner> sysMenus = epartnerMapper.selectPage(epartner);
        return new PageInfo<Epartner>(sysMenus);
    }

    /**
     *该方法才是真正需要调用的分页方法
     * 同时将搜索条件也加入进行分页
     * 如果无需搜索则去除实体类传参
     */
    @Override
    public PageResult findPage(PageRequest pageRequest, Epartner epartner) {
        return PageUtils.getPageResult(pageRequest, getPageInfo(pageRequest, epartner));
    }
}
