package com.tsbg.ecosys.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tsbg.ecosys.service.base.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


public class LoginInterceptor  implements HandlerInterceptor{
	//private List<String> url = new ArrayList<>();
	@Autowired
	private RedisService redisService;

	/**
	 * 开始进入地址请求拦截
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("进入登录拦截器！！！");
		String tokenHeader = request.getHeader("token");
		System.out.println("前端header传来的token:"+tokenHeader);
		String token = redisService.getCacheObject("token");
		if(token.contains(tokenHeader)){
			return true;
		}else{
			System.out.println("token失效");
			//  后端响应头设置
			response.addHeader("REDIRECT", "REDIRECT");//告诉ajax这是重定向
			response.addHeader("CONTEXTPATH", "index.html");//重定向地址
			//response.sendRedirect("index.html");	//未登录，跳转到登录页
			return false;
		}
	}

	/**
	 * 处理请求完成后视图渲染之前的处理操作
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	/**
	 * 视图渲染之后的操作
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		 
	}
	
	/**
	 * 定义排除拦截URL
	 * @return
	 */
	/*public List<String> getUrl(){
		url.add("/index");      //登录页
        url.add("/admin/login/in");   //登录action URL

        //网站静态资源
        url.add("/css/**");
        url.add("/js/**");
        url.add("/lib/**");
        url.add("/fonts/**");
        url.add("/img/**");
		return url;
	}*/
}
