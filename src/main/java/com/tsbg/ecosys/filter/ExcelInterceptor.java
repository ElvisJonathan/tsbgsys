package com.tsbg.ecosys.filter;

import com.tsbg.ecosys.controller.EcController;
import com.tsbg.ecosys.model.UserInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ExcelInterceptor implements HandlerInterceptor {
   @Override
    public  boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//      boolean isAssignbleFromHandlerMethod = handler.getClass().isAssignbleFrom(HandlerMethod.class);
//      System.out.println("当前方法的注解:"+isAssignbleFromHandlerMethod);
//       if(isAssignbleFromHandlerMethod){
////           EcController ecController = ((HandlerMethod)handler).getMethodAnnotation(EcController.class);
//        }
       return true;

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }

}
