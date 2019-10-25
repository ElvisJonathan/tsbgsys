package com.tsbg.mis.common;

import com.tsbg.mis.filter.RewriterFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 注册rewrite过滤器
 */
//@Configuration
public class WebFilterRegistration implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean registerFilter() {
            FilterRegistrationBean registration = new FilterRegistrationBean();
            registration.setFilter(new RewriterFilter());//注册rewrite过滤器
            registration.addUrlPatterns("/*");
            registration.addInitParameter(RewriterFilter.REWRITE_TO,"/index.html");
            registration.addInitParameter(RewriterFilter.REWRITE_PATTERNS,"/ui/*");
            registration.setName("rewriterFilter");
            registration.setOrder(1);
            return registration;
    }
}
