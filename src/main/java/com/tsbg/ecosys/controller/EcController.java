package com.tsbg.ecosys.controller;

import com.tsbg.ecosys.config.ResultResponse;
import com.tsbg.ecosys.dto.EcTotalDto;
import com.tsbg.ecosys.model.Epartner;
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

    @ApiOperation(value = "查询合作伙伴信息", notes = "查询合作伙伴信息")
    @RequestMapping(value = "/total", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResultResponse getEcooperationList(@RequestBody Epartner epartner){
        //获取公司合作伙伴编号
        Integer cid = epartner.getPartnerNo();
        //根据合作伙伴编号同步查询合作情况与联系人
        if (cid!=null){
            List<EcTotalDto> ecTotalDtoList = ecooperationService.getEcooperationList(cid);
            return new ResultResponse(0,"根据id查询", ecTotalDtoList);
        }
        return new ResultResponse(500,"未接受到公司合作伙伴编号");
    }
}
