package com.tsbg.mis.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import com.tsbg.mis.annotation.PassToken;
import com.tsbg.mis.annotation.UserLoginToken;
import com.tsbg.mis.ecoService.TokenBlacklistService;
import com.tsbg.mis.ecoService.UserInfoService;
import com.tsbg.mis.ecoService.base.RedisService;
import com.tsbg.mis.powerService.TokenBlacklistService2;
import com.tsbg.mis.powerService.UserInfoService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * token拦截器
 */
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    private UserInfoService2 userInfoService;//UserInfoService2是转为挪出的
    @Autowired
    private RedisService redisService;
    @Autowired
    //private TokenBlacklistService tokenBlacklistService;
    private TokenBlacklistService2 tokenBlacklistService;//TokenBlacklistService2是转为挪出的token
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        System.out.println("拦截器：请求头中的token:"+token);
        // 如果不是映射到方法直接通过
        if(!(object instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)object;
        Method method=handlerMethod.getMethod();
        //检查是否有PassToken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                // 执行认证
                if (token == null) {
                    throw new RuntimeException("无token，请重新登录");
                }
                int i1 = tokenBlacklistService.selectCountFromTokenList(token);
                if (i1>0){
                    throw new RuntimeException("token已失效，请重新登录");
                }
                // 获取 token 中的 user id
                String userId;
                try {
                    userId = JWT.decode(token).getAudience().get(0);
                    System.out.println("userId:"+userId);
                } catch (JWTDecodeException j) {
                    throw new RuntimeException("token中的用户信息已失效");
                }
                int i = userInfoService.selectIfExistThisUser(Integer.parseInt(userId));
                if (i == 0) {
                    throw new RuntimeException("用户不存在，请重新登录");
                }
                try {
                    // 验证 token
                    JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(redisService.getCacheObject("pwd"+userId).toString())).build();
                    jwtVerifier.verify(token);
                }  catch (JWTVerificationException e) {
                    throw new RuntimeException("token验证失效请重新登录");
                }  catch(Exception e){
                    throw new RuntimeException("密码和token失效请重新登录");
                }
                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
