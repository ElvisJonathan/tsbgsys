package com.tsbg.ecosys.controller;

import com.tsbg.ecosys.config.ResultResponse;
import com.tsbg.ecosys.model.bag.PowerPackage;
import com.tsbg.ecosys.model.EuserInfo;
import com.tsbg.ecosys.service.EperRoleService;
import com.tsbg.ecosys.service.EpermissionService;
import com.tsbg.ecosys.service.EuserInfoService;
import com.tsbg.ecosys.service.EuserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    @Autowired
    private EpermissionService epermissionService;

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
                    if (pid!=null){
                        //根据权限id查询对应的权限详情
                        List<String> plist = epermissionService.selectPowerDetailByPid(pid);
                        //如果plist不为null,则把查询出的list返回给前端
                        if (plist != null){
                            //将用户名和权限详情返给前端
                            resultResponse = new ResultResponse(0,"提示信息：成功查询到权限信息",plist,uName);
                            return resultResponse;
                        }
                        //plist为null则是未查询到完整的权限信息只返回用户名给前端
                        resultResponse = new ResultResponse(500,"提示信息：权限信息不完整",uName);
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
    public ResultResponse updatePower(@RequestBody PowerPackage powerPackage){
        //初始化构造器
        ResultResponse resultResponse = null;
        //需要获取工号和修改后的四个权限ID
        //成功获取了工号
        String userCode = powerPackage.getEuserInfo().getUserCode();
        //成功获取了data数组
        Object[] data = powerPackage.getData();//data的长度为前端传来的字符个数
        //由于只能从前端获取OBJ类型的数组所以要转为String类型的做字符串截取
        String[] data2 = new String[data.length];
        for (int i=0;i<=data2.length-1;i++){
            if (data[i]!=null){
                data2[i]=data[i].toString();
            }
        }
        //创建alist用于存data值
        List<String> alist = new ArrayList<String>();
        //循环遍历加元素
        for (int i=0;i<=data2.length-1;i++){
            //不为空的情况下加
            if (data2[i]!=null){
                alist.add(data2[i].substring(6,7));//根据现有数据做截取，特定条件改数字
            }
        }
        //验证输出的alist
        System.out.println("输出alist值："+alist.toString());
        //根据传过来的userCode查询对应的uid
        Integer uid = euserInfoService.selectuidbyuserCode(userCode);
        if (uid!=null){
            //通过uid查询用户对应的角色rid
            Integer rid = euserRoleService.selectRidByUid(uid);
            if (rid!=null){
                //根据rid去查询对应的prid,通过prid去修改pid
                List prid = eperRoleService.selectPridByRid(rid);
                if (prid!=null){
                    //for循环修改权限
                    for (int i=0;i<prid.size();i++){
                        //调用修改逻辑循环遍历修改所有权限ID
                        int num = eperRoleService.updatePowerByPrid(alist.get(i),prid.get(i));
                        if (num==0){
                            //如果某个权限id修改失败返回失败信息并中止修改过程
                            resultResponse = new ResultResponse(501,"提示信息：修改失败,请检查权限设置！");
                            return resultResponse;
                        }
                    }
                    //最终返回修改成功的提示给前端
                    resultResponse = new ResultResponse(0,"提示信息：修改成功！");
                    return resultResponse;
                }
                //prid为空返回异常信息
                resultResponse = new ResultResponse(502,"提示信息：修改异常,未查到对应权限！");
                return resultResponse;
            }
            //rid为空返回异常信息
            resultResponse = new ResultResponse(503,"提示信息：未查询到用户角色信息！");
            return resultResponse;
        }
        //uid为空返回异常信息
        resultResponse = new ResultResponse(504,"提示信息：未查询到用户编号信息！");
        return resultResponse;
    }
}
