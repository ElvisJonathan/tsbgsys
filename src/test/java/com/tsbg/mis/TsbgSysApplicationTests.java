package com.tsbg.mis;

import com.tsbg.mis.assetsService.AssetsTypeService;
import com.tsbg.mis.doorService.FriendLinkService;
import com.tsbg.mis.ecoModel.bag.PermRolePackage;
import com.tsbg.mis.ecoService.*;
import com.tsbg.mis.masterModel.StaffInfo;
import com.tsbg.mis.masterService.NationListService;
import com.tsbg.mis.masterService.StaffInfoService2;
import com.tsbg.mis.powerService.PermissionService2;
import com.tsbg.mis.questionService.QuesTypeService;
import com.tsbg.mis.stampService.StampTypeService;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TsbgSysApplicationTests {

    @Autowired
    private FileInfoService fileInfoService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private StaffInfoService staffInfoService;
    @Autowired
    private StaffInfoService2 staffInfoService2;
    @Autowired
    private EareaService eareaService;
    @Autowired
    private EpartnerService epartnerService;
    @Autowired
    StringEncryptor encryptor;
    @Autowired
    private FriendlyLinkService friendlyLinkService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleAndProJPackageService roleAndProJPackageService;
    @Autowired
    private NationListService nationListService;
    @Autowired
    private AssetsTypeService assetsTypeService;
    @Autowired
    private FriendLinkService friendLinkService;
    @Autowired
    private QuesTypeService quesTypeService;
    @Autowired
    private StampTypeService stampTypeService;
    @Autowired
    private PermissionService2 permissionService2;

    @Test
    public void contextLoads() {
        /*List<Integer> role = userRoleService.getRole(3);
        System.out.println(role.toString());
        *//*String result = StringUtils.join(role, ",");
        userInfoService.modifyRoleListByuserId(result, 2);
        System.out.println("成功！");*//*
        List<String> pList = new ArrayList<>();
        for (int i=0;i<=role.size()-1;i++){
            pList.add(permissionService.findPermissionByRoleId2(role.get(0)).toString()); *//*permissionService.findPermissionByRoleId2(role.get(i))*//*;
        }
        System.out.println(pList.toString());

        String result2 = StringUtils.join(pList, ";");
        System.out.println(result2);
        userInfoService.modifyPermListByuserId(result2.trim(),3);*/



            //处理前输出：我爱,,,,,,,,,,五星红旗
            String s = "我爱          五星红旗";
            System.out.println(s.replaceAll(" ", ",").trim());
            //处理后输出:我爱,五星红旗
            String regEx = "[' ']+"; // 一个或多个空格
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(s);
            System.out.println(m.replaceAll(",").trim());
    }

    @Test
    public void Test2() throws ParseException {
        String dateStr = "Mon Sep 02 16:47:52 CST 2019";
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        //java.util.Date对象
        Date date = (Date) sdf.parse(dateStr);
        //String formatStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        //System.out.println(formatStr);
        String formatStr2 = new SimpleDateFormat("yyyy-MM-dd").format(date);
        System.out.println(formatStr2);
    }

    @Test
    public void Test3() {
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<str.length();i++){
            int number=random.nextInt(6);
            sb.append(str.charAt(number));
        }
        System.out.println(sb);
    }

    @Test
    public void Test4() {
        String url = encryptor.encrypt("jdbc:mysql://localhost:3306/fiitsbg?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=true");
        String name = encryptor.encrypt("root");
        String password = encryptor.encrypt("root");
        System.out.println(url+"----------------");
        System.out.println(name+"----------------");
        System.out.println(password+"----------------");
        Assert.assertTrue(name.length() > 0);
        Assert.assertTrue(password.length() > 0);
    }

    @Test
    public void Test5() {
        /*String power = userInfoService.selectPowerByUserCode("A0248410");
        System.out.println(power);
        String newpower = power.trim().substring(1,power.length()-1);
        //s.replaceAll(" ", ",").trim()
        System.out.println(newpower);
        String s = newpower.replaceAll(", ",",").trim();
        System.out.println("处理后："+s);
        String[]arr = s.split(",");
        for (int i=0;i<=arr.length-1;i++){
            System.out.println(arr[i]);
        }*/
        /*List<String> gameids = Arrays.asList(s.split(","));
        System.out.println(gameids);
        System.out.println(gameids.get(0));*/

        System.out.println("------------------------------------------------------------------------");

        /*String power2 = userInfoService.selectPowerByUserCode("831736");
        System.out.println(power2);
        String []newpower2 = power2.split(";");
        System.out.println(newpower2[0]);
        System.out.println(newpower2[1]);

        String newpower3 = newpower2[0].substring(1,newpower2[0].length()-1);
        System.out.println(newpower3);

        List<String> gameids2 = java.util.Arrays.asList(newpower3.split(","));
        System.out.println(gameids2);
        System.out.println(gameids2.get(0));

        String newpower4 = newpower2[1].substring(1,newpower2[1].length()-1);
        System.out.println(newpower4);

        List<String> gameids3 = java.util.Arrays.asList(newpower4.split(","));
        System.out.println(gameids3);
        System.out.println(gameids3.get(0));*/
        /*List<String> gameids2 = java.util.Arrays.asList(power2.split(","));
        System.out.println(gameids2);
        System.out.println(gameids2.get(0));*/

    }

    @Test
    public void Test6() {
        // 加密
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword("password");
        String newPassword = textEncryptor.encrypt("123456dedcadbcaaffdfcabdbaadbdbdecdabeaeecdecdafdcadbbfecdedebbabfbe");
        System.out.println(newPassword);//GdKnaHlh+kkjPhkXjwZE/Q==
        // 解密
        BasicTextEncryptor textEncryptor2 = new BasicTextEncryptor();
        textEncryptor2.setPassword("password");
        String oldPassword = textEncryptor2.decrypt(newPassword);
        System.out.println(oldPassword);
        System.out.println("--------------------------");
    }

    @Test
    public void Test7() {
       /* String userCode= "F1336602";
        *//*if (userCode==null){
            System.out.println("工号不能为空！");
        }*//*
        //权限标识符
        Boolean powerFlag = false;
        //通过用户工号来查询相应权限进行权限判断  执行此功能必须要有add权限
        List<Integer> list= roleService.findRoleByUserCode2(userCode);
        if (list!=null){
            for (int i=0;i<=list.size()-1;i++){
                List<String> pList = permissionService.findPermissionByRoleId2(list.get(i));
                for (int j=0;j<=pList.size()-1;j++){
                    if (pList.get(j).contains("add")){
                        //权限标识符置为true
                        powerFlag = true;
                    }
                }
            }
        }
        if (powerFlag.equals(true)) {

        }*/
    }

    @Test
    public void Test8() {
        List<PermRolePackage> permissions = permissionService.selectRolePermMsg(1, 2);
        System.out.println(permissions.toString());

        List<String> strings = nationListService.selectNationName();
        System.out.println(strings.toString());

        List<String> strings1 = assetsTypeService.selectAssetsType();
        System.out.println(strings1.toString());

        List<String> strings2 = friendLinkService.selectLinkName();
        System.out.println(strings2.toString());

        List<String> strings3 = quesTypeService.selectTypeName();
        System.out.println(strings3.toString());

        List<String> strings4 = stampTypeService.selectTypeName();
        System.out.println(strings4.toString());

        List<String> strings5 = permissionService2.selectPermission();
        System.out.println(strings5.toString());
    }

    @Test
    public void Test9() {
        StaffInfo staffInfo = staffInfoService2.selectStaffMsg("009800");
        System.out.println(staffInfo.getAreaId());
    }
}