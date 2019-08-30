package com.tsbg.ecosys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
public class EcosysApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcosysApplication.class, args);
    }

    /**
     * 文件上传配置
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //单个文件最大
        factory.setMaxFileSize("30MB"); //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("30MB");
        return factory.createMultipartConfig();
    }

}
