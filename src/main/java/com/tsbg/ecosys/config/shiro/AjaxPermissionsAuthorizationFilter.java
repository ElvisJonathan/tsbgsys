package com.tsbg.ecosys.config.shiro;

import com.alibaba.fastjson.JSONObject;
import com.tsbg.ecosys.util.constants.ErrorEnum;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 对没有登录的请求进行拦截, 全部返回json信息. 覆盖掉shiro原本的跳转login.jsp的拦截方式
 */
public class AjaxPermissionsAuthorizationFilter extends FormAuthenticationFilter {

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) {
		/*HttpServletResponse httpResp = WebUtils.toHttp(response);
		HttpServletRequest httpReq = WebUtils.toHttp(request);
		*//*系统重定向会默认把请求头清空，这里通过拦截器重新设置请求头，解决跨域问题*//*
		httpResp.addHeader("Access-Control-Allow-Origin", "*");
		httpResp.addHeader("Access-Control-Allow-Headers", "*");
		httpResp.addHeader("Access-Control-Allow-Methods", "*");
		httpResp.addHeader("Access-Control-Allow-Credentials", "true");*/
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", ErrorEnum.E_20011.getErrorCode());
		jsonObject.put("msg", ErrorEnum.E_20011.getErrorMsg());
		PrintWriter out = null;
		HttpServletResponse res = (HttpServletResponse) response;
		try {
			res.setCharacterEncoding("UTF-8");
			res.setContentType("application/json");
			out = response.getWriter();
			out.println(jsonObject);
		} catch (Exception e) {
		} finally {
			if (null != out) {
				out.flush();
				out.close();
			}
		}
		return false;
	}

	@Bean
	public FilterRegistrationBean registration(AjaxPermissionsAuthorizationFilter filter) {
		FilterRegistrationBean registration = new FilterRegistrationBean(filter);
		registration.setEnabled(false);
		return registration;
	}
}
