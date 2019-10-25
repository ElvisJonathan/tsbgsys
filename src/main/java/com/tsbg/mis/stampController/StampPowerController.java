package com.tsbg.mis.stampController;

import com.tsbg.mis.annotation.PassToken;
import com.tsbg.mis.powerModel.UserInfo;
import com.tsbg.mis.stampService.StampPowerService;
import com.tsbg.mis.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用印系统权限管理
 */

@RestController
@RequestMapping("/tsbg/stamp")
public class StampPowerController {

    @Autowired
    private StampPowerService stampPowerService;

    /**
     * 查询用印权限群组列表
     */
    @RequestMapping(value = "/StampMemList", method = { RequestMethod.GET, RequestMethod.POST })
    @PassToken
    @ResponseBody
    public ResultUtils PowerMemList(){
        return stampPowerService.selectPowerList();
    }

    /**
     * 查询导出权限添加成员
     * 用工号带出姓名和部门信息
     * @return
     */
    @RequestMapping(value = "/powerManage", method = { RequestMethod.GET, RequestMethod.POST })
    @PassToken
    @ResponseBody
    public ResultUtils powerManage(@RequestBody UserInfo userInfo) {
       return stampPowerService.addPowerMembers(userInfo.getUserCode());
    }

    /**
     *查询导出权限添加成员
     * 添加成员
     */
    @RequestMapping(value = "/addStampMem", method = { RequestMethod.GET, RequestMethod.POST })
    @PassToken
    @ResponseBody
    public ResultUtils addPowerMem(@RequestBody UserInfo userInfo){
        return stampPowerService.selectRoleByUserCode(userInfo.getUserCode());
    }

    /**
     * 查询导出删除群组成员
     */
    @RequestMapping(value = "/delStampMem", method = { RequestMethod.GET, RequestMethod.POST })
    @PassToken
    @ResponseBody
    public ResultUtils delPowerMem(@RequestBody UserInfo userInfo){
        return stampPowerService.deleteStampMemByUserCode(userInfo.getUserCode());
    }
}
