package com.tsbg.ecosys.ecoController;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public abstract class BaseController {

    @Autowired
    public HttpServletRequest request;

    @Autowired
    public HttpServletResponse response;

    private static final Logger LOG = LoggerFactory.getLogger(BaseController.class);


    /**
     * 获取FlowId
     *
     * @return
     */
    public String getToken() {
        String token = request.getHeader("Token");
        LOG.info("getToken:{}", token);
        return request.getHeader("Token");
    }

    /**
     * 设置token
     *
     * @return
     */
    public void setToken(String token) {
        response.setHeader("Token", token);
    }

    public void setCorsHeader() {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
    }

}
