package com.tsbg.ecosys.ecoController;

import com.tsbg.ecosys.annotation.PassToken;
import com.tsbg.ecosys.ecoModel.bag.RoleAndProJPackage;
import com.tsbg.ecosys.ecoService.*;
import com.tsbg.ecosys.util.MD5Util2;
import com.tsbg.ecosys.util.ResultUtils;
import com.tsbg.ecosys.ecoModel.UserInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 注册
 */
@RestController
@RequestMapping("/tsbg/register")
public class RegisterController {

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private StaffInfoService staffInfoService;
    @Autowired
    private EareaService eareaService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RoleAndProJPackageService roleAndProJPackageService;

    /**
     *生态员工注册成为用户
     */
    @RequestMapping(value = "/ecosign", method = { RequestMethod.GET, RequestMethod.POST })
    @PassToken
    public ResultUtils register(@RequestBody UserInfo userInfo) {
        //初始化构造器
        ResultUtils resultUtils = null;
        //根据用户输入的工号去查询员工表是否存在该工号，存在并且为生态部门员工，则可注册，注册成功可以在用户表插入数据
        String userCode = userInfo.getUserCode();
        //通过工号查询员工表是否存在该工号 存在返回1 可以进行下一步注册
        int number = staffInfoService.selectisExistStaffCodeByuserCode(userCode);
        //通过工号查询员工表对应的部门代码编号，必须是生态部门编号才可进行下一步注册
        String deptCode = staffInfoService.selectDepartCodeByuserCode(userCode);
        //创建之前去用户表判断该用户是否已经注册，如果已经注册过提示错误信息不能重复注册
        int count = userInfoService.selectisExistUserCodeByStaffCode(userCode);
        if (count>0){
            resultUtils = new ResultUtils(502,"提示信息：已经注册，不可重复注册！");
            return resultUtils;
        }
        //如果存在该工号并且部门代码为生态部门代码则成功创建该用户
        if (number>0 && (deptCode.trim().equals("GE11000600") ||  deptCode.trim().equals("GE110006000") || deptCode.trim().equals("GE110006100") || deptCode.trim().equals("GE1100S6000")
        || deptCode.trim().equals("GE1100S6100") || deptCode.trim().equals("GE1100S6200") || deptCode.trim().equals("GE110006110")
        || deptCode.trim().equals("GE110006111") || deptCode.trim().equals("GE110006112") || deptCode.trim().equals("GE1100T6000")
        || deptCode.trim().equals("GE1100T6100") || deptCode.trim().equals("GE1100T6200") || deptCode.trim().equals("GE1100T6300")
        || deptCode.trim().equals("GE110006200") || deptCode.trim().equals("GE110006210") || deptCode.trim().equals("GE110006211")
        || deptCode.trim().equals("GE1100H6000") || deptCode.trim().equals("GE110006213") || deptCode.trim().equals("GE110006220")
        || deptCode.trim().equals("GE110006221") || deptCode.trim().equals("GE110006222") || deptCode.trim().equals("GE11200800")
        || deptCode.trim().equals("BCDCJ81000"))){
            //可以注册
            //设置创建人和创建时间
            userInfo.setCreater(userInfo.getUserName());
            userInfo.setCreateTime(new Date());
            //生成salt
            String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            Random random=new Random();
            StringBuffer sb=new StringBuffer();
            for(int i=0;i<str.length();i++){
                int nu=random.nextInt(6);
                sb.append(str.charAt(nu));
            }
            userInfo.setSalt(sb.toString());
            //加密密码
            String newPwd = userInfo.getUserPwd()+sb.toString();
            userInfo.setUserPwd(MD5Util2.encode(newPwd));
            //调用业务逻辑存储注册信息
            int num = userInfoService.insertSelective(userInfo);
            //成功注册则会增加一条记录
            if (num>0){
                //同步存储对应信息至euser_area和user_role
                //将user_info表的user_id查询出来
                int uid = userInfoService.selectuidbyuserCode(userCode);
                String name = userInfo.getUserName()+"是生态员工";
                //根据工号查询对应厂区
                String location = staffInfoService.selectlocationByUserCode(userCode);
                if (location!=null){
                    //根据厂区去查找对应的area_id
                    Integer id = eareaService.selectAreaNoByAreaName(location);
                    if (id!=null){
                        //将此user_id增加至euser_area
                        userInfoService.insertDatatoEuserArea(uid,id,name);
                    }
                }
                //将此uid和rid增加至user_role
                userInfoService.insertDatatoEuserRole(uid,2,name);
                //查询出该用户所拥有的角色信息
                List<Integer> roleList = userRoleService.getRole(uid);
                String result = StringUtils.join(roleList, ",");
                userInfoService.modifyRoleListByuserId(result, uid);
                //设置用户权限
                List<String> pList = new ArrayList<>();
                for (int i=0;i<=roleList.size()-1;i++){
                    if (permissionService.findPermissionByRoleId2(roleList.get(i)).toString()!=null
                    && !permissionService.findPermissionByRoleId2(roleList.get(i)).toString().equals("[]")){
                        pList.add(permissionService.findPermissionByRoleId2(roleList.get(i)).toString());
                    }else{
                        pList.add("[null]");
                    }
                }
                String result2 = StringUtils.join(pList, ";");
                userInfoService.modifyPermListByuserId(result2.trim(),uid);
                //返回成功码0并且提示成功注册
                resultUtils = new ResultUtils(0,"提示信息：成功注册！");
                return resultUtils;
            }
        }
        //不是生态员工返回错误码
        resultUtils = new ResultUtils(501,"提示信息：用户不是生态员工不能注册！");
        return resultUtils;
    }

    /**
     * 统一注册
     */
    @RequestMapping(value = "/unifiedSign", method = { RequestMethod.GET, RequestMethod.POST })
    @PassToken
    public ResultUtils UnifiedRegister(@RequestBody UserInfo userInfo) {
        //根据用户输入的工号去查询员工表是否存在该工号，存在则可注册，注册成功可以在用户表插入数据
        String userCode = userInfo.getUserCode();
        //通过工号查询员工表是否存在该工号 存在返回1 可以进行下一步注册
        int number = staffInfoService.selectisExistStaffCodeByuserCode(userCode);
        //创建之前去用户表判断该用户是否已经注册，如果已经注册过提示错误信息不能重复注册
        int count = userInfoService.selectisExistUserCodeByStaffCode(userCode);
        if (count>0){
            return new ResultUtils(502,"提示信息：已经注册，不可重复注册！");
        }
        //如果存在该工号成功创建该用户
        if (number>0){
            //可以注册
            //设置创建人和创建时间
            userInfo.setCreater(userInfo.getUserName());
            userInfo.setCreateTime(new Date());
            //生成salt
            String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            Random random=new Random();
            StringBuffer sb=new StringBuffer();
            for(int i=0;i<str.length();i++){
                int nu=random.nextInt(6);
                sb.append(str.charAt(nu));
            }
            userInfo.setSalt(sb.toString());
            //加密密码
            String newPwd = userInfo.getUserPwd()+sb.toString();
            userInfo.setUserPwd(MD5Util2.encode(newPwd));
            //调用业务逻辑存储注册信息
            int num = userInfoService.insertSelective(userInfo);
            //成功注册则会增加一条记录
            if (num>0){
                //适配生态系统的个人权限生成
                int uid = userInfoService.selectuidbyuserCode(userCode);
                //配置映射user_role
                List<RoleAndProJPackage> roleAndProJPackages = roleAndProJPackageService.selectRoleAndProj2();
                for (int i=0;i<roleAndProJPackages.size();i++){
                    //在user_role插入数据  最初注册都是普通用户的角色
                    userRoleService.insertData(uid,roleAndProJPackages.get(i).getRoleid(),roleAndProJPackages.get(i).getProjId());
                }
                //设置用户权限
                List<String> pList = new ArrayList<>();
                if (permissionService.findPermissionByRoleId2(2).toString()!=null
                            && !permissionService.findPermissionByRoleId2(2).toString().equals("[]")){
                    pList.add(permissionService.findPermissionByRoleId2(2).toString());
                }else{
                        pList.add("[null]");
                }
                userInfoService.modifyPermListByuserId(pList.get(0),uid);
                //返回成功码0并且提示成功注册
                return new ResultUtils(0,"提示信息：成功注册！");
            }
        }
        return new ResultUtils(501,"提示信息：您不是员工不能注册！");
    }
}
