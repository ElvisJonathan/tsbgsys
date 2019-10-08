package com.tsbg.ecosys.config;

import com.tsbg.ecosys.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置类
 * @author Administrator
 *
 */
@Configuration
public class AppConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//须要拦截的url及请求
		//addInterceptor(new AuthorityInterceptor())拦截器类
		//addPathPatterns("/user/**","/html/home/**") 要拦截的url 
		//excludePathPatterns("/user/login"); 在要拦截的url里面去除不需要拦截的
		//registry.addInterceptor(new AuthorityInterceptor()).addPathPatterns("/user/**","/html/home/**").excludePathPatterns("/user/login");
        /*.excludePathPatterns("/admin/login.do").excludePathPatterns("/admin")*/
		//LoginInterceptor loginInterceptor = new LoginInterceptor();
        // addPathPatterns 添加拦截url，     excludePathPatterns 排除拦截url
		//registry.addInterceptor(new LoginInterceptor());
		WebMvcConfigurer.super.addInterceptors(registry);
	}
}
