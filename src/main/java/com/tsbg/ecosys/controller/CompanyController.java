package com.tsbg.ecosys.controller;

import com.tsbg.ecosys.config.ResultResponse;
import com.tsbg.ecosys.model.EcInfo;
import com.tsbg.ecosys.model.Eccontacts;
import com.tsbg.ecosys.model.Ecooperation;
import com.tsbg.ecosys.service.EcInfoService;
import com.tsbg.ecosys.service.EccontactsService;
import com.tsbg.ecosys.service.EcooperationService;
import com.tsbg.ecosys.util.PageRequest;
import com.tsbg.ecosys.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 公司相关
 */
@RestController
@RequestMapping("/tsbg/company")
public class CompanyController {

    @Autowired
    private EcInfoService ecInfoService;
    @Autowired
    private EcooperationService ecooperationService;
    @Autowired
    private EccontactsService eccontactsService;

    /**
     * 管理员隐藏公司
     */
    @RequestMapping(value = "/hideCompany", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResultResponse hideCom(@RequestBody EcInfo ecInfo){
        //初始化构造器
        ResultResponse resultResponse = null;
        //获取cid
        Integer cid = ecInfo.getCid();
        if (cid!=null){
            //根据前端传过来的cid作为参数修改公司的状态
            int num = ecInfoService.updateByCid(cid);
            if (num>0){
                resultResponse = new ResultResponse(0,"提示信息：隐藏公司成功！");
                return resultResponse;
            }
            //如果没有修改成功则返回失败
            resultResponse = new ResultResponse(501,"提示信息：隐藏公司失败！");
            return resultResponse;
        }
        //公司编号为空则返回异常信息
        resultResponse = new ResultResponse(502,"提示信息：公司编号为空异常！");
        return resultResponse;
    }

    /**
     * 合作情况信息新增
     */
    @RequestMapping(value = "/addCooperation", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResultResponse addCoo(@RequestBody Ecooperation ecooperation){
        /*Ecooperation ecooperation = new Ecooperation("阿里巴巴",new Date(),"test"
        ,"合作开始阶段",0,"昨天",1,"腾讯",0,"生态系统",
                "强强合作","进展顺利","mis",new Date(),"张三");*/
        //初始化传参构造器
        ResultResponse resultResponse = null;
        //需要从前台获取合作伙伴编号和其他合作情况信息
        if (ecooperation!=null){
            //设置当前时间为创建时间，暂时忽略创建人
            ecooperation.setCreateTime(new Date());
            //调用业务逻辑增加前端传来的信息至数据库
            int num = ecooperationService.insertSelective(ecooperation);
            if (num>0){
                resultResponse = new ResultResponse(0,"提示信息：增加成功");
                return resultResponse;
            }
            resultResponse = new ResultResponse(501,"提示信息：新增失败");
            return resultResponse;
        }
        resultResponse = new ResultResponse(502,"提示信息：请输入信息");
        return resultResponse;
    }

    /**
     * 分页查询公司信息
     */
    @RequestMapping(value = "/findPage", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResultResponse findPage(@RequestBody PageRequest pageRequest) {
        ResultResponse resultResponse = null;
        //需要前台传参pageQuery:包含pageIndex和pageSize 即起始页码和页面容量 记得容量小于总条数才会有分页效果
        if (pageRequest.getPageIndex()!=0 && pageRequest.getPageSize()!=0){
            //根据给到的分页条件查询公司信息
            PageResult page = ecInfoService.findPage(pageRequest);
            if (page!=null){
                resultResponse = new ResultResponse(0,"提示信息：成功查询到公司信息",page);
                return resultResponse;
            }
            resultResponse = new ResultResponse(501,"提示信息：未查询到公司信息");
            return resultResponse;
        }
        resultResponse = new ResultResponse(502,"提示信息：分页条件不明确异常");
        return resultResponse;
    }

    //查询公司信息
    @RequestMapping("/selectCinfo")
    @ResponseBody
    public ResultResponse searchCinfo(@RequestBody EcInfo ecInfo) {
        ResultResponse resultResponse = null;
        //通过前端传来的信息查询对应的公司信息
        String partnerCname = ecInfo.getPartnerCname();
        String partnerCregion = ecInfo.getPartnerCregion();
        String partnerCproduct = ecInfo.getPartnerCproduct();
        String partnerCindustry = ecInfo.getPartnerCindustry();
        if (partnerCname != null || partnerCregion != null || partnerCproduct != null || partnerCindustry != null) {
            List<EcInfo> infod1 = ecInfoService.selectCinfo(ecInfo);
            List<EcInfo> infod2 = ecInfoService.selectCinfo(ecInfo);
            List<EcInfo> infod3 = ecInfoService.selectCinfo(ecInfo);
            List<EcInfo> infod4 = ecInfoService.selectCinfo(ecInfo);
            if (infod1 != null) {
                resultResponse = new ResultResponse(0, "提示信息：成功查询到公司信息",infod1);
                return resultResponse;
            }
            else if(infod2 != null){
                resultResponse = new ResultResponse(0, "提示信息：成功查询到公司信息",infod2);
                return resultResponse;
            }
            else if (infod3 != null){
                resultResponse = new ResultResponse(0, "提示信息：成功查询到公司信息",infod3);
                return resultResponse;
            }
            else if (infod4 != null){
                resultResponse = new ResultResponse(0, "提示信息：成功查询到公司信息",infod4);
                return resultResponse;
            }
            resultResponse = new ResultResponse(501, "提示信息：未查询到公司信息");
            return resultResponse;
        }
        resultResponse = new ResultResponse(502, "提示信息：未收到id信息");
        return resultResponse;
    }

    //查询合作关系
    @RequestMapping("/selectCooinfo")
    @ResponseBody
    public ResultResponse searchCooinfo(@RequestBody Ecooperation ecooperation)
    {
        ResultResponse resultResponse = null;
        //通过前端传来的公司id查询对应的合作关系
        Integer partnerNo = ecooperation.getPartnerNo();
        if(partnerNo != null) {
            List<Ecooperation> info = ecooperationService.selectCooinfo(partnerNo);
            if (info != null) {
                resultResponse = new ResultResponse(0, "提示信息：成功查询到合作关系信息", info);
                return resultResponse;
            }
            resultResponse = new ResultResponse(501, "提示信息：未查询到合作关系信息");
            return resultResponse;
        }
        resultResponse = new ResultResponse(502,"提示信息：未收到id信息");
        return resultResponse;
    }
    //查询联系人信息
    @RequestMapping("/selectContacts")
    @ResponseBody
    public ResultResponse searchContacts(@RequestBody Eccontacts eccontacts){
        ResultResponse resultResponse = null;
        //通过前端传来的公司id查询对应的联系人信息
        Integer partnerNo = eccontacts.getPartnerNo();
        if (partnerNo != null) {
            List<Eccontacts> info3 = eccontactsService.selectContacts(partnerNo);
            if (info3 != null) {
                resultResponse = new ResultResponse(0, "提示信息：成功查询到联系人信息", info3);
                return resultResponse;
            }
            resultResponse = new ResultResponse(501, "提示信息：未查询到联系人信息");
            return resultResponse;
        }
        resultResponse = new ResultResponse(502,"提示信息：未收到id信息");
        return resultResponse;
    }
}