package com.tsbg.ecosys.controller;

import com.tsbg.ecosys.config.ResultResponse;
import com.tsbg.ecosys.model.EuserInfo;
import com.tsbg.ecosys.service.EperRoleService;
import com.tsbg.ecosys.service.EuserInfoService;
import com.tsbg.ecosys.service.EuserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 权限相关
 */
@RestController
@RequestMapping("/tsbg/power")
public class JurisdictionController {

    @Autowired
    private EuserInfoService euserInfoService;
    @Autowired
    private EuserRoleService euserRoleService;
    @Autowired
    private EperRoleService eperRoleService;
    /**
     * 权限详情
     */
    @RequestMapping(value = "/ecodetail", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResultResponse getPower(@RequestParam String userCode) {
        //初始化构造器
        ResultResponse resultResponse = null;
        //通过用户工号查询当前用户的uid和userName
        EuserInfo euserInfo = euserInfoService.selectUidAndName(userCode);
        int uid = euserInfo.getUid();
        //通过userCode查询当前用户用户名
        String uName = euserInfoService.selectUserNameByUserCode(userCode);
        //通过uid查询用户对应的角色rid
        int rid = euserRoleService.selectRidByUid(uid);
        //通过角色rid查询对应的权限id
        List pid = eperRoleService.selectPidByRid(rid);
        //如果pid不为null,则把查询出的list返回给前端
        if (pid != null){
            //将用户名放进list中一起返回
            pid.add(uName);
            resultResponse = new ResultResponse(0,"提示信息：成功查询到权限信息",pid);
            return resultResponse;
        }
        //pid为null则是未查询到权限只返回用户名给前端
        resultResponse = new ResultResponse(501,"提示信息：未查询到权限信息",uName);
        return resultResponse;
    }
}
