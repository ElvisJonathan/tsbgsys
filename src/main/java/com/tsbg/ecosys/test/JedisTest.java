package com.tsbg.ecosys.test;

import redis.clients.jedis.Jedis;

public class JedisTest {
    public static void main(String [] args){
        //1.Redis服务器的ip地址
        String host = "192.168.80.200";
        //2.Redis服务器的端口号
        int port = 6379;
        //3.创建Jedis对象
        Jedis jedis = new Jedis(host, port);
        //4.设置连接Redis服务器的密码
        String password = "redis";
        jedis.auth(password);
        //5.执行操作
        String ping = jedis.ping();
        System.out.println(ping);
        //6.关闭连接
        jedis.close();
    }

}
