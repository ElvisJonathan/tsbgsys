package com.tsbg.ecosys.controller;

import com.tsbg.ecosys.config.ResultResponse;
import com.tsbg.ecosys.dto.EcTotalDto;
import com.tsbg.ecosys.model.Eccontacts;
import com.tsbg.ecosys.model.Ecooperation;
import com.tsbg.ecosys.model.Epartner;
import com.tsbg.ecosys.service.EccontactsService;
//import com.tsbg.ecosys.service.EcooperationService;
import com.tsbg.ecosys.service.EpartnerService;
import com.tsbg.ecosys.serviceImpl.EcooperationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 合作伙伴
 */
@RestController
@RequestMapping("/tsbg/ec")
@Api(value = "EcController", tags = "EcController")
public class EcController {

    @Autowired
    private EcooperationService ecooperationService;
    @Autowired
    private EpartnerService epartnerService;
    /*@Autowired
    private EccontactsService eccontactsService;*/

    @ApiOperation(value = "查询合作伙伴信息", notes = "查询合作伙伴信息")
    @RequestMapping(value = "/total", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResultResponse getEcooperationList(@RequestBody Epartner epartner){
        //获取公司合作伙伴编号
        Integer cid = epartner.getPartnerNo();
        //根据合作伙伴编号同步查询合作情况与联系人
        if (cid!=null){
            List<EcTotalDto> ecTotalDtoList = ecooperationService.getEcooperationList(cid);
            return new ResultResponse(0,"根据PartnerNo查询",ecTotalDtoList);
            //分开查询的方式
            /*Epartner epartnerdetail =  epartnerService.selectByPrimaryKey(cid);
            List<Ecooperation> ecooperation = ecooperationService.selectByPartnerNo(cid);
            List<Eccontacts> eccontacts = eccontactsService.selectByPartnerNo(cid);
            return new ResultResponse(0,"根据PartnerNo查询", epartnerdetail,ecooperation,eccontacts);*/
        }
        return new ResultResponse(500,"未收到PartnerNo");
    }

    /**
     * 查询公司文件
     */
    @RequestMapping(value = "/filedetail", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResultResponse getFileDetail(@RequestBody Epartner epartner){
        Integer partnerNo = epartner.getPartnerNo();
        if (partnerNo!=null){
            //查询当前公司的文件详情
            List<String> filelist = epartnerService.selectFileByParNo(partnerNo);
            if (filelist!=null){
                return new ResultResponse(0,"查询公司文件(公司隐藏情况下文件也隐藏)",filelist);
            }
            return new ResultResponse(500,"未查到相关文件");
        }
        return new ResultResponse(501,"未收到PartnerNo");
    }
}
