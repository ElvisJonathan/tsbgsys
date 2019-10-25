package com.tsbg.mis.util;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class InitPwdUtils {
    public static String createInitPwd(){
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
        return strPwd;
    }
}
