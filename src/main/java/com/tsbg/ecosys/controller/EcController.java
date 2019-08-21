package com.tsbg.ecosys.controller;

import com.tsbg.ecosys.config.ResultResponse;
import com.tsbg.ecosys.dto.EcTotalDto;
import com.tsbg.ecosys.dto.EcTotalListDto;
import com.tsbg.ecosys.model.EcInfo;
import com.tsbg.ecosys.serviceImpl.EcooperationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    @GetMapping("/total")
    public ResultResponse getEcooperationList(@RequestBody EcInfo ecInfo){
        Integer cid = ecInfo.getCid();
        //根据cid查询
        if (cid!=null){
            List<EcTotalDto> ecTotalDtoList = ecooperationService.getEcooperationList(cid);
            return new ResultResponse(0,"根据id查询", ecTotalDtoList);
        }
        return new ResultResponse(500,"未接受到公司编号");
    }


   /* @ApiOperation(value = "批量更新合作伙伴信息", notes = "批量更新合作伙伴信息")
    @PutMapping("/total")
    public ResultResponse modifyClaimApply(
            @ApiParam(name = "ecTotalListDto", value = "ecTotalListDto", required = true)
            @RequestBody EcTotalListDto ecTotalListDto
    ) throws Exception {
        ecooperationService.updateEcooperationList(ecTotalListDto);

        return new ResultResponse(0,"");
    }*/
}
