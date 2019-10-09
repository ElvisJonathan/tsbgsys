package com.tsbg.ecosys.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {

    @RequiresPermissions("add")
    @RequestMapping(value = "/test", method = { RequestMethod.GET, RequestMethod.POST })
    public String testCode(){
        return "success";
    }
}
