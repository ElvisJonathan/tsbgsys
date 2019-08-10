package com.tsbg.ecosys.controller;

import com.tsbg.ecosys.config.ResultResponse;
import com.tsbg.ecosys.model.EperRole;
import com.tsbg.ecosys.model.EuserInfo;
import com.tsbg.ecosys.service.EperRoleService;
import com.tsbg.ecosys.service.EuserInfoService;
import com.tsbg.ecosys.service.EuserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public ResultResponse getPower(@RequestBody EuserInfo euserInfo) {
        //初始化构造器
        ResultResponse resultResponse = null;
        //获取前端传来的工号
        String userCode = euserInfo.getUserCode();
        System.out.println("收到的userCode为："+userCode);
        if (userCode!=null){
            //通过userCode查询当前用户的uid
            Integer uid = euserInfoService.selectuidbyuserCode(userCode);
            //通过userCode查询当前用户的userName
            String uName = euserInfoService.selectUserNameByUserCode(userCode);
            if (uid!=null){
                //通过uid查询用户对应的角色rid
                Integer rid = euserRoleService.selectRidByUid(uid);
                if (rid!=null){
                    //通过角色rid查询对应的权限id
                    List<Integer> pid = eperRoleService.selectPidByRid(rid);
                    //如果pid不为null,则把查询出的list返回给前端
                    if (pid != null){
                        //将list数据存到数组返回
                        /*int[] Array = new int[5];
                        for (int i=0;i<=pid.size()-1;i++){
                            //循环遍历赋值
                            Array[i]=pid.get(i);
                        }*/
                        //将用户名和pid返给前端
                        resultResponse = new ResultResponse(0,"提示信息：成功查询到权限信息",pid,uName);
                        return resultResponse;
                    }
                    //pid为null则是未查询到权限只返回用户名给前端
                    resultResponse = new ResultResponse(501,"提示信息：未查询到权限信息",uName);
                    return resultResponse;
                }
                //未查询到rid只返回用户名给前端
                resultResponse = new ResultResponse(502,"提示信息：未查询到用户角色信息",uName);
                return resultResponse;
            }
            //未查询到uid只返回用户名给前端
            resultResponse = new ResultResponse(503,"提示信息：未查询到用户编号信息",uName);
            return resultResponse;
        }
        //userCode为空则返回错误提示
        resultResponse = new ResultResponse(504,"提示信息：未接收到工号");
        return resultResponse;
    }

    /**
     * 修改权限
     */
    @RequestMapping(value = "/updatepower", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResultResponse updatePower(@RequestBody List pod){
        //初始化构造器
        ResultResponse resultResponse = null;
        //pid中包含工号和修改后的四个权限ID
        //模拟一波传过来的List 假设list中有[1,6,6,6,"F1336602"]五个值
        //此模拟修改以最终与前端调试结果为准
        List<Object> pid = new ArrayList<Object>();
        pid.add(1);pid.add(6);pid.add(6);pid.add(6);pid.add("F1336602");
        //根据传过来的userCode查询对应的uid
        String userCode = pid.get(pid.size()-1).toString();
        int uid = euserInfoService.selectuidbyuserCode(userCode);
        //通过uid查询用户对应的角色rid
        int rid = euserRoleService.selectRidByUid(uid);
        //根据rid去查询对应的prid,通过prid去修改pid
        List prid = eperRoleService.selectPridByRid(rid);
        //for循环修改权限
        for (int i=0;i<prid.size();i++){
            //调用修改方式循环遍历修改所有权限ID
            int num = eperRoleService.updatePowerByPrid(pid.get(i),prid.get(i));
            if (num>0){
                //遍历次数决定输出的成功次数，有一次失败则修改不完全
                System.out.println("修改成功一次");
            }else{
                //如果某个权限id修改失败返回失败信息并中止修改过程
                resultResponse = new ResultResponse(501,"提示信息：修改失败,请检查权限设置！");
                return resultResponse;
            }
        }
        //最终返回修改成功的提示给前端
        resultResponse = new ResultResponse(0,"提示信息：修改成功！");
        return resultResponse;
    }
}
