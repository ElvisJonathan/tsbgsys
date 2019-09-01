package com.tsbg.ecosys.util;

import java.text.SimpleDateFormat;
import java.util.Date;

//Excel导出日期工具类
public  class ExcelTimeUtils {

    public static String getTimeString(Date time) {

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = formatter.format(time);
            return dateString;
         }
    public static String getCurrentTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateString = formatter.format(new Date());
        return dateString;
    }

    public static String getTimehhString(Date time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(time);
        return dateString;
    }

    public static String getDateString() {
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String temp_str=sdf.format(dt);
        return temp_str;
    }
}
