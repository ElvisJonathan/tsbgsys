package com.tsbg.ecosys.controller;

import com.tsbg.ecosys.model.bag.ProjectqfPackage;
import com.tsbg.ecosys.service.ProjectqfService;
import com.tsbg.ecosys.util.PageRequest;
import com.tsbg.ecosys.util.ResultUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tsbg/qe")
@Api(value = "QuestionController", tags = "QuestionController")
public class QuestionController {
    @Autowired
    private ProjectqfService projectqfService;

    //查询反馈、处理、文件等信息
    @RequestMapping(value = "/getquestionall", method = {RequestMethod.GET, RequestMethod.POST})
    public ResultUtils getquestionall(){
        ResultUtils resultUtils = null;
        List<ProjectqfPackage> projectqfPackages = projectqfService.selectProjectqfall();
        if (projectqfPackages != null){
            //如果查询出的用户列表不为空则返回给前端进行数据渲染
            resultUtils = new ResultUtils(0,"天啊，既然查询到了：成功返回用户反馈信息",projectqfPackages);
            return resultUtils;
        }
        //若未查询到用户信息则返回提示
        resultUtils = new ResultUtils(501,"偶哦，失败了：未查询到用户反馈信息");
        return resultUtils;
    }

   /* //分页查询反馈、处理、文件等信息
    @RequestMapping(value = "/getquestionsybase", method = {RequestMethod.GET, RequestMethod.POST})
    public ResultUtils getquestionsybase(){
        ResultUtils resultUtils = null;
        //需要前台传参pageQuery:包含pageIndex和pageSize 即起始页码和页面容量 记得容量小于总条数才会有分页效果
        //接受分页参数pageRequest
        PageRequest pageRequest = searchPackage.getPageRequest();
        ResultUtils resultUtils = null;
        List<ProjectqfPackage> projectqfPackages = projectqfService.selectProjectqfall();
        if (projectqfPackages != null){
            //如果查询出的用户列表不为空则返回给前端进行数据渲染
            resultUtils = new ResultUtils(0,"天啊，既然查询到了：成功返回用户反馈信息",projectqfPackages);
            return resultUtils;
        }
        //若未查询到用户信息则返回提示
        resultUtils = new ResultUtils(501,"偶哦，失败了：未查询到用户反馈信息");
        return resultUtils;
    }*/
}
