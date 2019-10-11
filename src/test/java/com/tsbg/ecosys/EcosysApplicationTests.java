package com.tsbg.ecosys;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EcosysApplicationTests {

    @Test
    public void contextLoads() {
//        //生成salt
//        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
//        Random random=new Random();
//        StringBuffer sb=new StringBuffer();
//        for(int i=0;i<8;i++){
//            int nu=random.nextInt(6);
//            sb.append(str.charAt(nu));
//        }
//        System.out.println(sb);
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
    public void createInitPwd(){
        List<String> lst=new ArrayList<>();
        lst.add("abcdefghijklmnopqrstuvwxyz");
        lst.add("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        lst.add("0123456789");
        lst.add("@#$%&*!");

        char[] pwdStr = new char[16];
        String jsonStr="{'arr0':[2,2,3,9],'arr1':[2,2,4,8],'arr2':[2,2,5,7],'arr3':[2,2,6,6],"+
                "'arr4':[2,3,3,8],'arr5':[2,3,4,7],'arr6':[2,3,5,6],'arr7':[2,4,4,6],'arr8':[2,4,5,5]}";
        JSONObject jsonArr=JSONObject.parseObject(jsonStr);


        Random random=new Random();
        int patternType=random.nextInt(9);
        System.out.println(patternType);
        String[] pattern=jsonArr.getString("arr"+patternType).substring(1,8).split(",");
        int tempNum=0;

        for(int i=lst.size(),k=0;i>0;i--){
            tempNum=random.nextInt(i);

            String tempStr=lst.get(tempNum);
            for(int j=0;j<Integer.parseInt(pattern[i-1]);j++,k++){
                pwdStr[k]=tempStr.charAt(random.nextInt(lst.get(tempNum).length()));
//                System.out.println("第"+i+"次："+j+"     : "+pwdStr[k]);
            }
            lst.remove(tempNum);
        }
        List templist=new ArrayList<Character>();
        for (int i = 0; i < 16; i++)
            templist.add(pwdStr[i]);
        Collections.shuffle(templist);
        String strPwd="";
        for (int i=0;i<templist.size();i++) {
            strPwd+=templist.get(i);
        }
        System.out.println(strPwd);
        //return strPwd;
    }
}


