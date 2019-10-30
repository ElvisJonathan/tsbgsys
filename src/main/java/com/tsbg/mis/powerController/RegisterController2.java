package com.tsbg.mis.powerController;

import com.tsbg.mis.annotation.PassToken;
import com.tsbg.mis.masterModel.StaffInfo;
import com.tsbg.mis.masterService.StaffInfoService2;
import com.tsbg.mis.powerModel.UserInfo;
import com.tsbg.mis.powerModel.powerBag.RoleAndProJPackage;
import com.tsbg.mis.powerService.PermService2;
import com.tsbg.mis.powerService.RoleAndProJPackageService2;
import com.tsbg.mis.powerService.UserInfoService2;
import com.tsbg.mis.powerService.UserRoleService2;
import com.tsbg.mis.util.MD5Util2;
import com.tsbg.mis.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 注册
 */
@RestController
@RequestMapping("/tsbg/register")
public class RegisterController2 {

    @Autowired
    private UserInfoService2 userInfoService;
    @Autowired
    private StaffInfoService2 staffInfoService;
    @Autowired
    private PermService2 permissionService;
    @Autowired
    private RoleAndProJPackageService2 roleAndProJPackageService;
    @Autowired
    private UserRoleService2 userRoleService;

    /**
     * 统一注册
     */
    @RequestMapping(value = "/unifiedSign2", method = { RequestMethod.GET, RequestMethod.POST })
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
            //需要获取的额外数据
            StaffInfo staffInfo = staffInfoService.selectStaffMsg(userCode);
            if (staffInfo.getStaffTypeId()!=null){
                userInfo.setStaffTypeId(staffInfo.getStaffTypeId());
            }
            if (staffInfo.getNationId()!=null){
                userInfo.setNationId(staffInfo.getNationId());
            }
            if (staffInfo.getAreaId()!=null){
                userInfo.setAreaId(staffInfo.getAreaId());
            }
            if (staffInfo.getFactoryId()!=null){
                userInfo.setFactoryId(staffInfo.getFactoryId());
            }
            if (staffInfo.getFirstBgId()!=null){
                userInfo.setFirstBgId(staffInfo.getFirstBgId());
            }
            if (staffInfo.getSecondBgId()!=null){
                userInfo.setSecondBgId(staffInfo.getSecondBgId());
            }
            if (staffInfo.getBgId()!=null){
                userInfo.setBgId(staffInfo.getBgId());
            }
            if (staffInfo.getCostCode()!=null){
                userInfo.setCostCode(staffInfo.getCostCode());
            }
            if (staffInfo.getOrganizationName()!=null){
                userInfo.setOrganizationName(staffInfo.getOrganizationName());
            }
            if (staffInfo.getOrganizationCode()!=null){
                userInfo.setOrganizationCode(staffInfo.getOrganizationCode());
            }
            if (staffInfo.getBuId()!=null){
                userInfo.setBuId(staffInfo.getBuId());
            }
            if (staffInfo.getDepartId()!=null){
                userInfo.setDepartId(staffInfo.getDepartId());
            }
            if (staffInfo.getClassId()!=null){
                userInfo.setClassId(staffInfo.getClassId());
            }
            if (staffInfo.getGroupId()!=null){
                userInfo.setGroupId(staffInfo.getGroupId());
            }
            if (staffInfo.getLegalPersonId()!=null){
                userInfo.setLegalPersonId(staffInfo.getLegalPersonId());
            }
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
                    userRoleService.insertData(uid,roleAndProJPackages.get(i).getRoleid(),userCode,new Date(),roleAndProJPackages.get(i).getProjId());
                }
                //设置生态用户权限
                List<String> pList = new ArrayList<>();
                if (permissionService.findPermissionByRoleId2(3).toString()!=null
                            && !permissionService.findPermissionByRoleId2(3).toString().equals("[]")){
                    pList.add(permissionService.findPermissionByRoleId2(3).toString());
                }else{
                        pList.add("[null]");
                }
                userInfoService.modifyPermListByuserId(pList.get(0),uid);
                //返回成功码0并且提示成功注册
                return new ResultUtils(100,"提示信息：成功注册！");
            }
        }
        return new ResultUtils(501,"提示信息：您尚未录為Fii员工，请联系后台管理员！");
    }
}
