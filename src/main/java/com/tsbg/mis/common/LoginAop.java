package com.tsbg.mis.common;

import com.tsbg.mis.ecoModel.UserInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;


@Aspect
@Component
public class LoginAop {

    public LoginAop() {
    }

    @Pointcut("@annotation(com.tsbg.mis.annotation.NeedLogin)")
    public void pointCut() {

    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint pjd) throws Throwable {
        Object object = null;
        //获取request
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = null;
        if (servletRequestAttributes != null) {
            request = servletRequestAttributes.getRequest();
        }
        if (request != null) {
            // 获取用户信息
            UserInfo userInfo = (UserInfo) request.getSession().getAttribute("session_user");
            System.out.println(userInfo);
            // 这里判断用户信息是否为空
            if (userInfo==null) {
                HttpServletResponse response = servletRequestAttributes.getResponse();
                response.setCharacterEncoding("utf-8");
                response.setContentType("application/json");
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                PrintWriter out = response.getWriter();
                String msg = "{\"code\":401,\"message\":权限不足，请登录}";
                out.print(msg);
                 return null;
            }else{
                System.out.println(userInfo.getUserCode());
            }
        }
        object = pjd.proceed();
        return object;
    }
}
