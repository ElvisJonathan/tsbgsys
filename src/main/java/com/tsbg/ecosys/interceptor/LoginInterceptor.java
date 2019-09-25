package com.tsbg.ecosys.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


public class LoginInterceptor  implements HandlerInterceptor{
	//private List<String> url = new ArrayList<String>();

	/**
	 * 开始进入地址请求拦截
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("进入登录拦截器！！！");
		HttpSession session = request.getSession();
		if(session.getAttribute("userCode") != null){
			return true;
		}else{
			System.out.println("非常规方式进入管理员后台！请登录后再试");
			response.sendRedirect("index.html");	//未登录，跳转到登录页
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
		url.add("/admin/login");      //登录页
        url.add("/admin/login/in");   //登录action URL

        //网站静态资源
        url.add("/css/**");
        url.add("/js/**");
        url.add("/lib/**");
        url.add("/fonts/**");
        url.add("/images/**");	
		return url;
	}	*/
}
