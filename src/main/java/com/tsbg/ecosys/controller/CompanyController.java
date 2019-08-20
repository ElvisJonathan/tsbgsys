package com.tsbg.ecosys.controller;

import com.tsbg.ecosys.config.ResultResponse;
import com.tsbg.ecosys.model.bag.CompanyPackage;
import com.tsbg.ecosys.model.EcInfo;
import com.tsbg.ecosys.model.Eccontacts;
import com.tsbg.ecosys.model.Ecooperation;
import com.tsbg.ecosys.model.bag.SearchPackage;
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
     * 管理员隐藏/取消隐藏公司
     */
    @RequestMapping(value = "/hideCompany", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResultResponse hideCom(@RequestBody EcInfo ecInfo){
        //初始化构造器
        ResultResponse resultResponse = null;
        //获取cid
        Integer cid = ecInfo.getCid();
        //获取STATUS
        Integer status = ecInfo.getStatus();
        //接受到的status为1则是隐藏公司,0则是取消隐藏公司
        if (status!=0 && status!=1){
            resultResponse = new ResultResponse(503,"提示信息：请输入正确的status！");
            return resultResponse;
        }
        if (cid!=null){
            //根据前端传过来的cid作为参数修改公司的状态
            int num = ecInfoService.updateByCid(status,cid);
            if (num>0 && status==1){
                resultResponse = new ResultResponse(0,"提示信息：隐藏公司成功！");
                return resultResponse;
            }else if (num>0 && status==0){
                resultResponse = new ResultResponse(0,"提示信息：取消隐藏公司成功！");
                return resultResponse;
            }else{
                //如果没有修改成功则返回失败
                resultResponse = new ResultResponse(501,"提示信息：公司编号不存在或操作失败！");
                return resultResponse;
            }
        }
        //公司编号为空则返回异常信息
        resultResponse = new ResultResponse(502,"提示信息：公司编号为空异常！");
        return resultResponse;
    }

    /**
     * 合作伙伴信息、合作情况信息、公司联系人信息新增
     */
    @RequestMapping(value = "/addCompany", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResultResponse addCom(@RequestBody CompanyPackage companyPackage){
        //这是一堆模拟的数据
        /*Ecooperation ecooperation = new Ecooperation("腾讯",new Date(),"test"
        ,"合作开始阶段",0,"昨天",1,"腾讯",0,"生态系统",
                "强强合作","进展顺利","mis",new Date(),"张三");
        EcInfo ecInfo = new EcInfo("富士康",new Date(),"一个亿",0,"全球","龙华区","WWW.huawei.com","5G","手机","直营店和网售","一百个亿","全国",
                "互联网",new Date());
        Eccontacts eccontacts = new Eccontacts("富士康","郭台铭","总裁",1,"991","XXX");*/
        //初始化传参构造器
        ResultResponse resultResponse = null;
        //新建arr数组用于存储成功值
        int []arr = new int[3];
        //需要从前台获取合作伙伴信息、合作情况信息、公司联系人信息
        if (companyPackage.getEcInfo().getPartnerCname()!=null && companyPackage.getEcInfo().getPartnerCindustry()!=null
        && companyPackage.getEcInfo().getPartnerCregion()!=null && companyPackage.getEcInfo().getPartnerCproduct()!=null
               /* ecInfo.getPartnerCname()!=null && ecInfo.getPartnerCindustry()!=null && ecInfo.getPartnerCregion()!=null
        && ecInfo.getPartnerCproduct()!=null*/){
            //合作伙伴信息中:合作伙伴公司名称、行业、业务主要区域、主营产品/业务/服务不为空才可以进行添加
            EcInfo ecInfo = companyPackage.getEcInfo();
            //设置创建时间
            ecInfo.setCreateTime(new Date());
            //调用存储公司合作伙伴的业务逻辑存储
            int count = ecInfoService.insertSelective(ecInfo);
            if (count>0){
                arr[0]=1;
            }
        }

        if(companyPackage.getEcooperation().getPartnerCname()!=null/*ecooperation.getPartnerCname()!=null*/){
            //合作情况信息中：合作伙伴公司名称不能为空
            Ecooperation ecooperation = companyPackage.getEcooperation();
            //设置创建时间
            ecooperation.setCreateTime(new Date());
            //调用业务方法存储合作关系信息
            int num = ecooperationService.insertSelective(ecooperation);
            if (num>0){
                arr[1]=1;
            }
        }

        if (companyPackage.getEccontacts().getName()!=null && companyPackage.getEccontacts().getPhoneNumber()!=null
            && companyPackage.getEccontacts().getPartnerCname()!=null
                /*eccontacts.getName()!=null && eccontacts.getPhoneNumber()!=null && eccontacts.getPartnerCname()!=null*/){
            //公司联系人信息中：联系人姓名、性别、电话和所属公司名称不能为空
            Eccontacts eccontacts = companyPackage.getEccontacts();
            //设置创建时间
            eccontacts.setCreateTime(new Date());
            //调用业务方法存储联系人信息
            int number = eccontactsService.insertSelective(eccontacts);
            if (number>0){
                arr[2]=1;
            }
        }

        if (arr[0]==1 && arr[1]==1 && arr[2]==1){
            resultResponse = new ResultResponse(0,"提示信息：新增信息成功！");
            return resultResponse;
        }

        resultResponse = new ResultResponse(501,"提示信息：未能成功添加");
        return resultResponse;
    }

    /**
     * 分页查询公司信息+搜索
     */
    @RequestMapping(value = "/findPage", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResultResponse findPage(@RequestBody SearchPackage searchPackage) {
        ResultResponse resultResponse = null;
        //需要前台传参pageQuery:包含pageIndex和pageSize 即起始页码和页面容量 记得容量小于总条数才会有分页效果
        //接受分页参数pageRequest
        PageRequest pageRequest = searchPackage.getPageRequest();
        //接受搜索条件传参实体类ecInfo
        EcInfo ecInfo = searchPackage.getEcInfo();
        if (pageRequest.getPageIndex()!=0 && pageRequest.getPageSize()!=0){
            //根据给到的分页条件查询公司信息
            PageResult page = ecInfoService.findPage(pageRequest,ecInfo);
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