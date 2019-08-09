package com.tsbg.ecosys.controller;

import com.tsbg.ecosys.config.ResultResponse;
import com.tsbg.ecosys.service.EcInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 公司相关
 */
@RestController
@RequestMapping("/tsbg/company")
public class CompanyController {

    @Autowired
    private EcInfoService ecInfoService;

    /**
     * 管理员隐藏公司
     */
    @RequestMapping(value = "/hideCompany", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResultResponse hideCom(@RequestParam int cid){
        //初始化构造器
        ResultResponse resultResponse = null;
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
}
