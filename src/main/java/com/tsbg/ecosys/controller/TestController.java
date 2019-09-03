package com.tsbg.ecosys.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {

    @RequestMapping(value = "/test", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String testcode(){
        return "success";
    }
}
