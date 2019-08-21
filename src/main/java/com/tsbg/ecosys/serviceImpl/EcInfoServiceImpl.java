package com.tsbg.ecosys.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tsbg.ecosys.mapper.EcInfoMapper;
import com.tsbg.ecosys.model.EcInfo;
import com.tsbg.ecosys.service.EcInfoService;
import com.tsbg.ecosys.util.PageRequest;
import com.tsbg.ecosys.util.PageResult;
import com.tsbg.ecosys.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EcInfoServiceImpl implements EcInfoService {

    @Autowired
    private EcInfoMapper ecInfoMapper;


    @Override
    public int updateByCid(int status, int cid) {
        return ecInfoMapper.updateByCid(status,cid);
    }

    @Override
    public List<EcInfo> findAll() {
        return ecInfoMapper.selectAll();
    }

    @Override
    public List<EcInfo> selectCinfo(EcInfo ecinfo)
    {
        return ecInfoMapper.selectCinfo(ecinfo);
    }

    @Override
    public int insertSelective(EcInfo record) {
        return ecInfoMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(EcInfo record) {
        return ecInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateStatusByCid(int cid) {
        return ecInfoMapper.updateStatusByCid(cid);
    }

    @Override
    public List<EcInfo> selectCinfoBypartnerCindustry(String partnerCindustry) {

        return ecInfoMapper.selectCinfoBypartnerCindustry(partnerCindustry);
    }
    @Override
    public List<EcInfo> selectCinfoBypartnerCregion(String partnerCregion) {

        return ecInfoMapper.selectCinfoBypartnerCregion(partnerCregion);
    }
    @Override
    public List<EcInfo> selectCinfoBypartnerCproduct(String partnerCproduct) {
        return ecInfoMapper.selectCinfoBypartnerCproduct(partnerCproduct);
    }
    @Override
    public List<EcInfo> selectCinfoBypartnerCname(String partnerCname) {
        return ecInfoMapper.selectCinfoBypartnerCname(partnerCname);
    }

    /**
     * 调用分页插件完成分页
     */
    private PageInfo<EcInfo> getPageInfo(PageRequest pageRequest,EcInfo ecInfo) {
        int pageIndex = pageRequest.getPageIndex();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageIndex, pageSize);
        //此处实现了加搜索条件情况下的分页,如果不需要搜索则不需要加实体类传参
        List<EcInfo> sysMenus = ecInfoMapper.selectPage(ecInfo);
        return new PageInfo<EcInfo>(sysMenus);
    }

    /**
     *该方法才是真正需要调用的分页方法
     * 同时将搜索条件也加入进行分页
     * 如果无需搜索则去除实体类传参
     */
    @Override
    public PageResult findPage(PageRequest pageRequest, EcInfo ecInfo) {
        return PageUtils.getPageResult(pageRequest, getPageInfo(pageRequest,ecInfo));
    }
}
