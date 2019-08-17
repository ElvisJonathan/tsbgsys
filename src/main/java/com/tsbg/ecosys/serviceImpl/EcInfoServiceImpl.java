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
    public int updateByCid(int cid) {
        return ecInfoMapper.updateByCid(cid);
    }

    @Override
    public List<EcInfo> findAll() {
        return ecInfoMapper.selectAll();
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return PageUtils.getPageResult(pageRequest, getPageInfo(pageRequest));
    }




    @Override
    public List<EcInfo> selectCinfo(EcInfo ecinfo)
    {
        return ecInfoMapper.selectCinfo(ecinfo);
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
     * @param pageRequest
     * @return
     */
    private PageInfo<EcInfo> getPageInfo(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<EcInfo> sysMenus = ecInfoMapper.selectPage();
        return new PageInfo<EcInfo>(sysMenus);
    }
}
