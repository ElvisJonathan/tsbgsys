package com.tsbg.ecosys.filter;

import com.google.common.base.Strings;
import com.tsbg.ecosys.controller.BaseController;
import com.tsbg.ecosys.service.base.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//@Configuration
//@WebFilter(filterName = "sessionFilter", urlPatterns = "/*", asyncSupported = true)
public class SessionFilter extends BaseController implements Filter {
    private static final Logger log = LoggerFactory.getLogger(SessionFilter.class);
    @Autowired
    private RedisService redisService;
    @Value("${server.servlet.session.timeout}")
    private long sessionExpire;
    private List<String> ignoreUrlList;


    private static final Logger LOG = LoggerFactory.getLogger(SessionFilter.class);

    public SessionFilter() {
        ignoreUrlList = new ArrayList<>();
        //初始化放行的URL
        this.initIgnoreUrl();
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        String requestURI = httpServletRequest.getRequestURI();
        //放行URL列表

        //现在来到这了
        //swagger放行swagger请求
        if (ignoreUrlList.contains(requestURI) || "/v2/api-docs".equals(requestURI)||requestURI.indexOf("swagger") != -1) {
            LOG.info("Ignore url:{}", requestURI);
            super.setCorsHeader();
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String liteToken = httpServletRequest.getHeader("Token");

        LOG.info("liteToken = [{}]", liteToken);

        //检查sessionKey是否有效，true有效、false无效
        if (!Strings.isNullOrEmpty(liteToken)) {
            String tValue = redisService.getCacheObject(liteToken);
            if ("1".equals(tValue)){
                super.setCorsHeader();
                filterChain.doFilter(servletRequest, servletResponse);
                redisService.updateExpire(liteToken, sessionExpire);
                return;
            }
        }

        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

    }

    @Override
    public void destroy() {

    }

    private void initIgnoreUrl() {
        //login
        ignoreUrlList.add("http://localhost:8080/tsbg/login/ecologin");
    }
}

