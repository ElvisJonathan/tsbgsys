package com.tsbg.mis.ecoController;

import com.tsbg.mis.annotation.PassToken;
import com.tsbg.mis.annotation.UserLoginToken;
import com.tsbg.mis.ecoModel.bag.PermRolePackage;
import com.tsbg.mis.ecoModel.bag.RoleProJNoPackage;
import com.tsbg.mis.util.MD5Util2;
import com.tsbg.mis.util.ResultUtils;
import com.tsbg.mis.ecoModel.bag.PowerPackage;
import com.tsbg.mis.ecoModel.UserInfo;
import com.tsbg.mis.ecoService.*;
import com.tsbg.mis.ecoVo.PowerVo;
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
    private UserInfoService userInfoService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private PermRoleService permRoleService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RoleService roleService;

    /**
     * 权限详情
     */
    @RequestMapping(value = "/ecodetail", method = { RequestMethod.GET, RequestMethod.POST })
    @UserLoginToken
    @ResponseBody
    public ResultUtils getPower(@RequestBody UserInfo userInfo) {
        //初始化构造器
        ResultUtils resultUtils = null;
        //获取前端传来的工号
        String userCode = userInfo.getUserCode();
        if (userCode!=null){
            //根据工号查询用户状态
            Integer status = userInfoService.selectStatusByUserCode(userCode);
            //通过userCode查询当前用户的user_id
            Integer uid = userInfoService.selectuidbyuserCode(userCode);
            //通过userCode查询当前用户的userName
            String uName = userInfoService.selectUserNameByUserCode(userCode);
            if (uid!=null){
                //通过uid查询用户对应的角色rid
                Integer rid = userRoleService.selectRidByUid(uid);
                System.out.println("角色编号："+rid);
                if (rid!=null){
                    //通过角色rid查询对应的权限id
                    List<Integer> pid = permRoleService.selectPidByRid(rid);
                    if (pid!=null){
                        //根据权限id查询对应的权限详情
                        List<String> plist = permissionService.selectPowerDetailByPid(pid);
                        //如果plist不为null,则把查询出的list返回给前端
                        if (plist != null){
                            //将用户名和权限详情返给前端
                            resultUtils = new ResultUtils(0,"提示信息：成功查询到权限信息",plist,uName,status);
                            return resultUtils;
                        }
                        //plist为null则是未查询到完整的权限信息只返回用户名给前端
                        resultUtils = new ResultUtils(500,"提示信息：权限信息不完整",uName);
                        return resultUtils;
                    }
                    //pid为null则是未查询到权限只返回用户名给前端
                    resultUtils = new ResultUtils(501,"提示信息：未查询到权限信息",uName);
                    return resultUtils;
                }
                //未查询到rid只返回用户名给前端
                resultUtils = new ResultUtils(502,"提示信息：未查询到用户角色信息",uName);
                return resultUtils;
            }
            //未查询到uid只返回用户名给前端
            resultUtils = new ResultUtils(503,"提示信息：未查询到用户编号信息",uName);
            return resultUtils;
        }
        //userCode为空则返回错误提示
        resultUtils = new ResultUtils(504,"提示信息：未接收到工号");
        return resultUtils;
    }

    /**
     * 修改权限
     * 整合管理员重置密码和停启用用户
     * 此方法用于修改某个角色的权限，后续无需再获取工号
     */
    @RequestMapping(value = "/updatepower", method = { RequestMethod.GET, RequestMethod.POST })
    @UserLoginToken
    @ResponseBody
    public ResultUtils updatePower(@RequestBody PowerPackage powerPackage){
        //初始化构造器
        ResultUtils resultUtils = null;
        //需要获取工号和修改后的四个权限ID
        //成功获取了工号
        String userCode = powerPackage.getUserInfo().getUserCode();
        if (userCode==null){
            resultUtils = new ResultUtils(510, "提示信息：工号不能为空！");
            return resultUtils;
        }
        String ChangedUserCode = powerPackage.getUserCode().toString();
        //权限标识符
        Boolean powerFlag = false;
        //通过用户工号来查询相应权限进行权限判断  执行此功能必须要有power权限
        List<Integer> list= roleService.findRoleByUserCode2(ChangedUserCode);
        if (list!=null){
            for (int i=0;i<=list.size()-1;i++){
                List<String> pList = permissionService.findPermissionByRoleId2(list.get(i));
                for (int j=0;j<=pList.size()-1;j++){
                    if (pList.get(j).contains("power")){
                        //权限标识符置为true
                        powerFlag = true;
                    }
                }
            }
        }
        if (powerFlag.equals(true)){
            //后续增加的用户状态
            int status = powerPackage.getUserInfo().getStatus();
            //创建数组保存成功值
            int[]arr = new int[3];
                //需要接收前端是否重置密码的提示：否0，是1
                Object sign = powerPackage.getSign();
                if (sign.equals("1")){
                    //重新生成salt
                    String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
                    Random random=new Random();
                    StringBuffer sb=new StringBuffer();
                    for(int i=0;i<str.length();i++){
                        int nu=random.nextInt(6);
                        sb.append(str.charAt(nu));
                    }
                    userInfoService.resetUserSalt(sb.toString(),userCode);
                    String userPwd = MD5Util2.encode(userCode+"123"+sb.toString());//用于重置用户密码
                    userInfoService.reSetPwdByUserCode(userPwd,userCode);
                }
                //调用方法修改用户状态：状态0为启用用户，1为停用用户
                int num2 = userInfoService.setEcoUserByUserCode(status,userCode);
                //如果修改成功将数组下标第一位赋值为1，默认为0
                if (num2>0){
                    arr[0]=1;
                }
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
            //根据传过来的userCode查询对应的uid
            Integer uid = userInfoService.selectuidbyuserCode(userCode);
            if (uid!=null){
                //通过uid查询用户对应的角色rid
                Integer rid = userRoleService.selectRidByUid(uid);
                if (rid!=null){
                    //根据rid去查询对应的prid,通过prid去修改pid
                    List prid = permRoleService.selectPridByRid(rid);
                    if (prid!=null){
                        //for循环修改权限
                        for (int i=0;i<prid.size();i++){
                            //调用修改逻辑循环遍历修改所有权限ID
                            int num = permRoleService.updatePowerByPrid(alist.get(i),prid.get(i));
                            if (num==0 || arr[0]==0){
                                //如果某个权限id修改失败返回失败信息并中止修改过程
                                resultUtils = new ResultUtils(501,"提示信息：修改失败,请检查权限设置！");
                                return resultUtils;
                            }
                        }
                        //最终返回修改成功的提示给前端
                        resultUtils = new ResultUtils(0,"提示信息：修改成功！");
                        return resultUtils;
                    }
                    //prid为空返回异常信息
                    resultUtils = new ResultUtils(502,"提示信息：修改异常,未查到对应权限！");
                    return resultUtils;
                }
                //rid为空返回异常信息
                resultUtils = new ResultUtils(503,"提示信息：未查询到用户角色信息！");
                return resultUtils;
            }
            //uid为空返回异常信息
            resultUtils = new ResultUtils(504,"提示信息：未查询到用户编号信息！");
            return resultUtils;
          }
        resultUtils = new ResultUtils(509, "提示信息：无此权限！");
        return resultUtils;
    }

    /**
     * 查询用户个人权限
     * 适配生态系统
     */
    @RequestMapping(value = "/powerdetail", method = { RequestMethod.GET, RequestMethod.POST })
    @UserLoginToken
    @ResponseBody
    public ResultUtils getUserPower(@RequestBody UserInfo userInfo) {
        //获取前端传来的工号
        String userCode = userInfo.getUserCode();
        //根据工号查询用户状态
        Integer status = userInfoService.selectStatusByUserCode(userCode);
        //通过userCode查询当前用户的userName
        String uName = userInfoService.selectUserNameByUserCode(userCode);
        //返回权限列表
        String power = userInfoService.selectPowerByUserCode(userCode);
        String newPower = power.substring(1,power.length()-1);
        String s = newPower.replaceAll(", ",",").trim();
        String[]arr = s.split(",");
        //返回对应的权限编号
        List<Integer> list = permissionService.selectPermIdByPerm(arr);
        //返回对应的中文名
        String[]arr2 = new String[arr.length];
        for (int i=0;i<=arr.length-1;i++){
            arr2[i]=permissionService.selectPermission(arr[i]);
        }
        return new ResultUtils(0,"查询权限成功",new PowerVo(list,arr2),uName,status);
    }

    /**
     * 修改用户权限
     * 适配生态系统
     */
    @RequestMapping(value = "/powerModify", method = { RequestMethod.GET, RequestMethod.POST })
    @UserLoginToken
    @ResponseBody
    public ResultUtils updateUserPower(@RequestBody PowerPackage powerPackage){
        //需要返回工号、修改后的权限数组[add,view]等、用户状态和重置密码标识
        //成功获取了工号
        String userCode = powerPackage.getUserInfo().getUserCode();
        if (userCode==null){
            return new ResultUtils(510, "提示信息：工号不能为空！");
        }
        String ChangedUserCode = powerPackage.getUserCode().toString();
        //这个版本可以简化为当前修改人是否有power权限
        //权限标识符
        Boolean powerFlag = false;
        //通过用户工号来查询相应权限进行权限判断  执行此功能必须要有power权限
        /*List<Integer> list= roleService.findRoleByUserCode2(ChangedUserCode);
        if (list!=null){
            for (int i=0;i<=list.size()-1;i++){
                List<String> pList = permissionService.findPermissionByRoleId2(list.get(i));
                for (int j=0;j<=pList.size()-1;j++){
                    if (pList.get(j).contains("power")){
                        //权限标识符置为true
                        powerFlag = true;
                    }
                }
            }
        }*/
        //此处为验证个人权限 集成shiro后就可以不用写 可以用注解
        String powerFirst = userInfoService.selectPowerByUserCode(ChangedUserCode);
        String newPowerFirst = powerFirst.substring(1,powerFirst.length()-1);
        String sFirst = newPowerFirst.replaceAll(", ",",").trim();
        String[]arr1 = sFirst.split(",");
        for (int i=0;i<arr1.length;i++){
            if (arr1[i].trim().equals("power")){
                //权限标识符置为true
                powerFlag = true;
            }
        }
        if (powerFlag.equals(true)){
            //后续增加的用户状态
            int status = powerPackage.getUserInfo().getStatus();
            //需要接收前端是否重置密码的提示：否0，是1
            Object sign = powerPackage.getSign();
            if (sign.equals("1")){
                //重新生成salt
                String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
                Random random=new Random();
                StringBuffer sb=new StringBuffer();
                for(int i=0;i<str.length();i++){
                    int nu=random.nextInt(6);
                    sb.append(str.charAt(nu));
                }
                userInfoService.resetUserSalt(sb.toString(),userCode);
                String userPwd = MD5Util2.encode(userCode+"123"+sb.toString());//用于重置用户密码
                userInfoService.reSetPwdByUserCode(userPwd,userCode);
            }
            //调用方法修改用户状态：状态0为启用用户，1为停用用户
            userInfoService.setEcoUserByUserCode(status,userCode);
            //成功获取了data数组
            Object[] data = powerPackage.getData();//data的长度为前端传来的字符个数
            for (int i=0;i<=data.length-1;i++){
                System.out.println(data[i]);//输出接收到的数组查看，判断怎么截取
            }
            //由于只能从前端获取OBJ类型的数组所以要转为String类型的做字符串截取
            String[] data2 = new String[data.length];
            for (int i=0;i<=data2.length-1;i++){
                if (data[i]!=null){
                    data2[i]=data[i].toString();
                }
            }
            //创建aList用于存data值
            List<String> aList = new ArrayList<String>();
            //循环遍历加元素
            for (int i=0;i<=data2.length-1;i++){
                //不为空的情况下加
                if (data2[i]!=null){
                    //aList.add(data2[i].substring(6,7));//根据现有数据做截取，特定条件改数字
                    //但是如果前端传的是数字1,2,3,4那么需要判断后加
                    if (data2[i].substring(6,7).equals("1")){
                        aList.add("add");
                    }
                    if (data2[i].substring(6,7).equals("2")){
                        aList.add("del");
                    }
                    if (data2[i].substring(6,7).equals("3")){
                        aList.add("update");
                    }
                    if (data2[i].substring(6,7).equals("4")){
                        aList.add("view");
                    }
                    if (data2[i].substring(6,7).equals("5")){
                        aList.add("power");
                    }
                }
            }
            //根据传过来的userCode查询对应的uid
            Integer uid = userInfoService.selectuidbyuserCode(userCode);
            //此时可以修改用户权限数组
            userInfoService.modifyPermListByuserId(aList.toString(),uid);
            //返回权限列表
            String power = userInfoService.selectPowerByUserCode(userCode);
            String newPower = power.substring(1,power.length()-1);
            String s = newPower.replaceAll(", ",",").trim();
            String[]arr3 = s.split(",");
            return new ResultUtils(0,"修改权限成功",arr3);
        }
        return new ResultUtils(509, "提示信息：无此权限！");
    }

    /**
     * 统一 权限查询
     * 根据项目编号或角色编号查询权限
     * 生态系统不要用这个了，本身就已经细化到个人了
     */
    @RequestMapping(value = "/unifiedPower", method = { RequestMethod.GET, RequestMethod.POST })
    //@UserLoginToken
    @PassToken
    @ResponseBody
    public ResultUtils getUnifiedPower(@RequestBody RoleProJNoPackage roleProJNoPackage) {
        Integer projId = roleProJNoPackage.getProjId();
        Integer roleId = roleProJNoPackage.getRoleId();
        System.out.println("项目编号："+projId);
        System.out.println("角色编号："+roleId);
        List<PermRolePackage> permissions = permissionService.selectRolePermMsg(projId, roleId);
        return new ResultUtils(100,"成功",permissions);
    }

    /**
     * 统一 权限修改
     * 修改用户的角色
     * 生态系统不要用这个了，本身就已经细化到个人了
     */
    @RequestMapping(value = "/modifyUnifiedPower", method = { RequestMethod.GET, RequestMethod.POST })
    //@UserLoginToken
    @PassToken
    @ResponseBody
    public ResultUtils modifyUnifiedPower(@RequestBody RoleProJNoPackage roleProJNoPackage) {
        //需要获取参数userCode/projId/想要修改成的角色编号
        String userCode = roleProJNoPackage.getUserCode();
        System.out.println("工号："+userCode);
        Integer uid = userInfoService.selectuidbyuserCode(userCode);
        System.out.println("用户编号："+uid);
        Integer projId = roleProJNoPackage.getProjId();
        System.out.println("项目编号："+projId);
        Integer roleId = roleProJNoPackage.getRoleId();
        System.out.println("角色编号："+roleId);
        //通过角色的变更可以控制该用户角色对应的权限，而不是去修改perm_role（会导致一批人权限的变更）
        int i = userRoleService.updateUserRoleByProAndRoleId(roleId, uid, projId);
        if (i>0){
            return new ResultUtils(100,"修改角色成功");
        }
        return new ResultUtils(500,"修改角色失败,请确认该用户角色后修改");
    }
}
