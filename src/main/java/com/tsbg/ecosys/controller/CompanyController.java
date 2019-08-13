package com.tsbg.ecosys.controller;

import com.tsbg.ecosys.config.ResultResponse;
import com.tsbg.ecosys.model.EcInfo;
import com.tsbg.ecosys.model.Ecooperation;
import com.tsbg.ecosys.service.EcInfoService;
import com.tsbg.ecosys.service.EcooperationService;
import com.tsbg.ecosys.util.PageRequest;
import com.tsbg.ecosys.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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
     * @param pageQuery
     * @return
     */
    @RequestMapping(value = "/findPage", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResultResponse findPage(@RequestBody PageRequest pageQuery) {
        ResultResponse resultResponse = null;
        //需要前台传参pageQuery:包含pageNum和pageSize 即起始页码和页面容量
        /*PageRequest pageQuery = new PageRequest();
        pageQuery.setPageNum(1);
        pageQuery.setPageSize(5);*/
        if (pageQuery.getPageNum()!=0 && pageQuery.getPageSize()!=0){
            //根据给到的分页条件查询公司信息
            PageResult page = ecInfoService.findPage(pageQuery);
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
}