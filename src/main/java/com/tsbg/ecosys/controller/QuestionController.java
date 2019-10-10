package com.tsbg.ecosys.controller;

import com.tsbg.ecosys.dto.QuestionDto;
import com.tsbg.ecosys.serviceImpl.Questionsservice;
import com.tsbg.ecosys.util.ResultUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tsbg/qe")
@Api(value = "QuestionController", tags = "QuestionController")
public class QuestionController {
    @Autowired
    private Questionsservice questionsservice;

    //分开查询反馈用户信息
    @RequestMapping(value = "/getquestionall", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResultUtils getQuestionAll(){
        //初始化构造器
        ResultUtils resultUtils = null;
        QuestionDto questionDtos = questionsservice.getQuestionList();
        if (questionDtos != null){
            //如果查询出的用户列表不为空则返回给前端进行数据渲染
            resultUtils = new ResultUtils(0,"天啊，既然查询到了：成功返回用户反馈信息",questionDtos);
            return resultUtils;
        }
        //若未查询到用户信息则返回提示
        resultUtils = new ResultUtils(501,"偶哦，失败了：未查询到用户反馈信息");
        return resultUtils;
    }

    /*
    //关联查询反馈用户信息
    @RequestMapping(value = "/getQuestion", method = {RequestMethod.GET, RequestMethod.POST})
    public ResultUtils getquestion(){
        //初始化构造器
        ResultUtils resultUtils = null;
        List<Question> questionList = questionService.selectquestion();
        if (questionList != null){
            //如果查询出的用户列表不为空则返回给前端进行数据渲染
            resultUtils = new ResultUtils(0,"天啊，既然查询到了：成功返回用户反馈信息",questionList);
            return resultUtils;
        }
        //若未查询到用户信息则返回提示
        resultUtils = new ResultUtils(501,"偶哦，失败了：未查询到用户反馈信息");
        return resultUtils;
    }
*/


}
