package com.tsbg.ecosys;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EcosysApplicationTests {

    @Test
    public void contextLoads() {
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
}
