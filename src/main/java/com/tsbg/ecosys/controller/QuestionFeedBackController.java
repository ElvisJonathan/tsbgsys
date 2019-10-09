package com.tsbg.ecosys.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tsbg.ecosys.model.*;
import com.tsbg.ecosys.service.*;
import com.tsbg.ecosys.util.JWTUtils;
import com.tsbg.ecosys.util.ResultUtils;
import com.tsbg.ecosys.util.SendMailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tsbg.ecosys.service.QuestionTypeService;
import com.tsbg.ecosys.service.UserInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/tsbg/questionFeedBack")
public class QuestionFeedBackController {
    @Autowired
    private QuestionFeedBackService feedBackService;
    @Autowired
    private QuestionHandleService questionHandleService;
    @Autowired
    private QuestionTypeService questionTypeService;
    @Autowired
    private FileInfoService fileInfoService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserInfoService userInfoService;


    private final String Kefu_UserCode="F1336602";





    /**
     * 新增問題反饋
     * 集成文件上传(当文件数量等于或多于当前文件数量时调用)
     */
    @RequestMapping(value = "/createFeedBack", method = RequestMethod.POST )
    @ResponseBody
    public ResultUtils addFeedBack(HttpServletRequest req, MultipartFile[] file)throws Exception {//
        //初始化參數構造器
        ResultUtils resultUtils = null;
        //獲取數據


        String json = req.getParameter("questionFeedback");
        System.out.println(json);
        QuestionFeedback questionFeedback = JSONObject.parseObject(json, QuestionFeedback.class);
//        String json2 = req.getParameter("epartner");
//        Epartner epartner = JSONObject.parseObject(json2, Epartner.class);
//
//        QuestionFeedback questionFeedback=new QuestionFeedback();
//        questionFeedback.setQuestionName(req.getParameter("questionName"));
//
//        questionFeedback.setQuestionDescribe(req.getParameter("questionDescribe"));
//        questionFeedback.setUserCode(req.getParameter("userCode"));
//        questionFeedback.setUserName(req.getParameter("userName"));

        //获取当前添加人

        if (questionFeedback.getUserCode()==null){
            resultUtils = new ResultUtils(510, "提示信息：工號不能為空！您输入的工号为："+questionFeedback.getUserCode()+"您输入的questionName为："+questionFeedback.getQuestionName());
            return resultUtils;
        }



        //從前臺獲得的系統名稱、問題類別、反餽人賬號、反餽人姓名不能為空(系統自動獲取)
        if (questionFeedback.getQuestionName()!=null &&
                questionFeedback.getUserCode()!=null && questionFeedback.getUserName()!=null
        ){
            Date time =new Date();
            SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" );
            String timeFormat = sdf.format(time);
            //System.out.println(timeFormat);
            questionFeedback.setApplicationDate(sdf.parse(timeFormat));
        }

        questionFeedback.setApplyStatusId(0);


        //此处增加文件上传
        if (file!=null) {

            StringBuffer buffer = new StringBuffer();
            for (MultipartFile multipartFile : file) {

                buffer.append(multipartFile.getOriginalFilename());
                buffer.append(",");
                String all = buffer.substring(0, buffer.length() - 1);
                System.out.println("所有文件：" + all);
                String Suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
                System.out.println("文件後綴：" + Suffix);
                //根據原始文件的文件名進行文件類型判斷
                if (Suffix.equals(".xls") || Suffix.equals(".xlsx") || Suffix.equals(".xlsm") || Suffix.equals(".doc")
                        || Suffix.equals(".docx") || Suffix.equals(".pdf") || Suffix.equals(".ppt") || Suffix.equals(".pptx")
                        || Suffix.equals(".png") || Suffix.equals(".jpg")|| Suffix.equals(".jpeg")) {
                    Date time =new Date();
                    SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd hh:mm:ss" );
                    String date = sdf.format(time);
                    date=date.replaceAll(":","");
                    //String realPath = req.getServletContext().getRealPath("/ecoUpload/"+questionFeedBack.getUserCode()+"/"+date;//此方法用于获取上传路径
                    //本地路径测试文件上传
                    String Path = "D:/66/testUpload/ecoUpload/questionFeedBack/file/"+questionFeedback.getUserCode()+"/"+date;
                    System.out.println("本地實際路徑：" + Path);
                    //服务器路径测试文件上传
                    String Path2 = "/tmp/ecoUpload/"+questionFeedback.getUserCode()+"/"+date;
                    System.out.println("服務器實際路徑：" + Path2);
                    File folder = new File(Path);//此处打包上去之前需要置换路径
                    if (!folder.exists()) {
                        folder.mkdirs();
                    }//无报错则上传成功
                    //获取上传者
                    multipartFile.transferTo(new File(folder, multipartFile.getOriginalFilename()));
                    String url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/ecoUpload" + "/" + multipartFile.getOriginalFilename();
                    System.out.println(url);//真实存储的url
                    //String newUrl = req.getServletContext().getRealPath("/ecoUpload") +"/"+epartner.getPartnerName()+"/" + multipartFile.getOriginalFilename();
                    //System.out.println("真实URL：" + newUrl);
                    //本地路径测试文件上传
                    String URL = "D:/66/testUpload/ecoUpload/questionFeedBack/file/"+questionFeedback.getUserCode()+"/"+date+"/"+multipartFile.getOriginalFilename();
                    System.out.println("本地存儲URL:"+URL);
                    //服务器路径测试文件上传
                    String URL2 = "/tmp/ecoUpload/"+questionFeedback.getUserCode()+"/"+date +"/"+ multipartFile.getOriginalFilename();
                    System.out.println("服務器存儲URL:"+URL2);
                    //进行文件上传记录的存储
                    FileInfo fileInfo = new FileInfo();
                    fileInfo.setFileName(multipartFile.getOriginalFilename());
                    //本地存储
                    fileInfo.setFilePath(URL);
                    //服务器存储   打包上去前需要置换
                    //fileInfo.setFilePath(URL2);

                    fileInfo.setUpdatedTime(new Date());
                    fileInfo.setLastUpdateUser(questionFeedback.getUserCode());
                    fileInfo.setKeyword(multipartFile.getOriginalFilename());
                    fileInfo.setQuestionFeedbackId(questionFeedback.getQuestionFeedbackId());
                    fileInfo.setRelDocId(2);
                    String timeFormat = sdf.format(new Date());

                    fileInfo.setUpdatedTime(sdf.parse(timeFormat));
                    fileInfo.setStatus(0);
                    fileInfo.setQuestionFeedbackId(questionFeedback.getQuestionFeedbackId());
                    System.out.println(questionFeedback.getQuestionFeedbackId());
                    int num = fileInfoService.insertSelective(fileInfo);
                    //查询出当前成功文件的编号
                    //int number = QuestionFeedback.selectID();
                    System.out.println("刚刚增加成功的记录的编号为：" + num);
                } else {
                    //上傳的附件格式不符合要求
                    return new ResultUtils(505, "上傳的附件格式不符合要求");
                }
            }

        }




        if (feedBackService.insertSelective(questionFeedback)>0) {
            resultUtils = new ResultUtils(100, "提示信息：新增成功！您输入的工号为："+questionFeedback.getUserCode());
            QuestionHandle questionHandle=new QuestionHandle();
            questionHandle.setQuestionFeedbackId(questionFeedback.getQuestionFeedbackId());
            System.out.println(questionFeedback.getQuestionFeedbackId());
            questionHandle.setIsHandle(0);
            questionHandle.setIsComplete(0);
            questionHandle.setProjId(questionFeedback.getProjId());
            //questionHandle.setStartDate(questionFeedback.getApplicationDate());
            if (questionHandleService.insertSelective(questionHandle)>0) {
                resultUtils = new ResultUtils(100, "提示信息：新增問題處理狀態成功！");
                System.out.println(questionHandle.getQuestionFeedbackId());


                String email="";
                JSONObject Message=new JSONObject();
                String user_code=Kefu_UserCode;
                String email_address=userInfoService.selectEmailByUserCode(user_code);
                if(!user_code.isEmpty() && !email_address.isEmpty()){//提交的Form表單數據user_code、email_address均不爲空
                    email=userInfoService.selectEmailByUserCode(user_code);
                    System.out.println(email);

                    String username=userInfoService.selectUserNameByUserCode(user_code);
                    UserInfo u=new UserInfo();
                    u.setUserCode(user_code);
                    u.setUserName(username);
                    u.setEmailAddress(email_address);
                    Message.put("工號",user_code);
                    Message.put("姓名",username);
                    Message.put("郵箱",email_address);
                    //String token = JWTUtils.sign(jsonInfo, 30L * 24L * 3600L * 1000L);//一個月
                    String token = JWTUtils.sign(u, 1L * 1L * 1L * 60000L);//60秒的時間
                    if (token != null) {
                        Message.put("Toekn",token);
                        String subject="問題反饋通知郵件";
                        String url="http://localhost:8080/testToken?token="+token;
                        String mailContent="<p>Dear "+username+",您好:      </p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;申請人"+questionFeedback.getUserName()+"在["+projectService.selectByPrimaryKey(questionFeedback.getProjId()).getProName()+"]申請的["+questionFeedback.getQuestionName()+"]，單號為["+questionFeedback.getQuestionFeedbackId()+"]的申請單需要您處理。</br></p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您可以點擊此鏈接直接進行處理："+url+"</p>";
                        SendMailUtils.send(username,email_address,subject,mailContent);

                    }else{
                        Message.put("Token","Token生成失敗，請稍後重試！");

                    }

                }else{


                }

            }

        }else{
            resultUtils = new ResultUtils(501, "提示信息：新增失敗！");

        }


        return resultUtils;
    }

    /**
     * 根據proj_id查詢系統（項目）名稱
     *
     */
    @RequestMapping(value = "/getProjectName", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String getProjectName(@RequestBody QuestionFeedback questionFeedback){
        int projId=questionFeedback.getProjId();
        return projectService.selectByPrimaryKey(projId).getProName();
    }


    /**
     * 查詢所有問題反饋
     *
     */
    @RequestMapping(value = "/getAllQuestionFeedBack", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public List<QuestionFeedback> getAllQuestionFeedBack(){
        //初始化參數構造器
        ResultUtils resultUtils = null;
        //獲取數據

        return feedBackService.selectAll();
    }

    /**
     * 查詢某一個問題反饋
     *
     */
    @RequestMapping(value = "/getQuestionFeedBackById", method =RequestMethod.POST)
    @ResponseBody
    public List getQuestionFeedBackById(@RequestBody QuestionFeedback questionFeedback){
        //初始化參數構造器
        ResultUtils resultUtils = null;
        //獲取數據
        List lst=new ArrayList();

        JSONObject json=new JSONObject();

        //JSONObject questionFeedBack=new JSONObject();
        int questionFeedbackId=questionFeedback.getQuestionFeedbackId();
        json.put("questionFeedBack",feedBackService.selectByPrimaryKey(questionFeedbackId));


        //lst.add(questionFeedBack);

        JSONObject jobFiles=new JSONObject();

        List<String> qfbfileLst=new ArrayList<>();

        for(int i=0;i<fileInfoService.selectFileNameByQuestionFeedBackId(questionFeedbackId).size();i++){
            qfbfileLst.add(fileInfoService.selectFileNameByQuestionFeedBackId(questionFeedbackId).get(i).getFileName());

        }
        jobFiles.put("qfbfileLst",qfbfileLst);

        //lst.add(questionTypeService.selectByPrimaryKey(questionHandleService.selectByQuestionFeedBackId(questionFeedbackId).getQuestionType()));


        //JSONObject questionHandle=new JSONObject();

        json.put("questionHandle",questionHandleService.selectByQuestionFeedBackId(questionFeedbackId));

        //lst.add(questionHandle);
        //System.out.println(lst.toString());
        List<String> qhfileLst=new ArrayList<>();

        for(int i=0;i<fileInfoService.selectFileNameByQuestionHandleId(questionHandleService.selectByQuestionFeedBackId(questionFeedbackId).getQuestionHandleId()).size();i++){
            qhfileLst.add(fileInfoService.selectFileNameByQuestionHandleId(questionHandleService.selectByQuestionFeedBackId(questionFeedbackId).getQuestionHandleId()).get(i).getFileName());

        }
        jobFiles.put("qhfileLst",qhfileLst);

        json.put("files",jobFiles);
        lst.add(json);

        //QuestionFeedback qfb= (QuestionFeedback) lst.get(0);
        //System.out.println(qfb.getQuestionName());
        return lst;
    }

    /**
     * 關閉某一個問題反饋
     *
     */
    @RequestMapping(value = "/closeQuestionFeedBackById", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResultUtils closeQuestionFeedBackById(@RequestBody QuestionFeedback questionFeedback){
        //初始化參數構造器
        ResultUtils resultUtils = null;
        //判斷是否登錄且具有權限


        int questionFeedbackId=questionFeedback.getQuestionFeedbackId();
        if(feedBackService.selectByPrimaryKey(questionFeedbackId).getApplyStatusId()==0){
            resultUtils = new ResultUtils(100, "提示信息：問題已經關閉，無需再關閉！");
        }else{
            if(feedBackService.updateCloseByPrimaryKey(questionFeedbackId)>0){
                resultUtils = new ResultUtils(100, "提示信息：問題關閉成功！");
            }else{
                resultUtils = new ResultUtils(501, "提示信息：問題關閉失敗！");
            }
        }



        return resultUtils;
    }

    /**
     * 查詢某一個問題反饋處理信息
     *
     */
    @RequestMapping(value = "/getQuestionHandleId", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public QuestionHandle getQuestionHandleId(@RequestBody QuestionFeedback questionFeedback)throws Exception {
        //初始化參數構造器
        ResultUtils resultUtils = null;
        //獲取數據

        int questionFeedbackId=questionFeedback.getQuestionFeedbackId();
        return questionHandleService.selectByPrimaryKey(questionFeedbackId);
    }

    /**
     * 處理問題反饋
     *
     */
    @RequestMapping(value = "/questionHandle", method = RequestMethod.POST )
    @ResponseBody
    public ResultUtils questionHandle(HttpServletRequest req, MultipartFile[] file)throws Exception {//

        //初始化參數構造器
        ResultUtils resultUtils = null;
        //獲取數據


        String json = req.getParameter("questionHandle");
        System.out.println(json);

        QuestionHandle questionHandle = JSONObject.parseObject(json, QuestionHandle.class);
        System.out.println("questionHandle"+questionHandle.getHandleName());
        System.out.println("HandleCode"+questionHandle.getHandleCode());




        //從前臺獲得的系統名稱、問題類別、處理人賬號、處理人姓名不能為空(系統自動獲取)
        if (questionHandle.getQuestionFeedbackId()!=null
        ){
            Date time =new Date();
            SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" );
            String timeFormat = sdf.format(time);
            System.out.println(timeFormat);
            questionHandle.setStartDate(sdf.parse(timeFormat));
        }



        if (questionHandleService.updateByFeedBackIdSelective(questionHandle)>0) {
            resultUtils = new ResultUtils(100, "提示信息：處理狀態更新成功！");
            System.out.println(questionHandle.getQuestionFeedbackId());


        }else{
            resultUtils = new ResultUtils(501, "提示信息：新增失敗！");

        }

        //此处增加文件上传
        if (file!=null&&file.length>0) {

            StringBuffer buffer = new StringBuffer();
            System.out.println("上傳的文件個數為：" + file.length);
            for (MultipartFile multipartFile : file) {

                buffer.append(multipartFile.getOriginalFilename());
                buffer.append(",");
                String all = buffer.substring(0, buffer.length() - 1);
                System.out.println("所有文件：" + all);
                String Suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
                System.out.println("文件後綴：" + Suffix);
                //根據原始文件的文件名進行文件類型判斷
                if (Suffix.equals(".xls") || Suffix.equals(".xlsx") || Suffix.equals(".xlsm") || Suffix.equals(".doc")
                        || Suffix.equals(".docx") || Suffix.equals(".pdf") || Suffix.equals(".ppt") || Suffix.equals(".pptx")
                        || Suffix.equals(".png") || Suffix.equals(".jpg") || Suffix.equals(".jpeg") || Suffix.equals(".txt")) {
                    Date time = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    String date = sdf.format(time);
                    date = date.replaceAll(":", "");
                    //String realPath = req.getServletContext().getRealPath("/ecoUpload/"+questionFeedBack.getUserCode()+"/"+date;//此方法用于获取上传路径
                    //本地路径测试文件上传
                    String Path = "D:/66/testUpload/ecoUpload/questionHandle/file/" + questionHandle.getHandleCode() + "/" + date;
                    System.out.println("本地實際路徑：" + Path);
                    //服务器路径测试文件上传
                    String Path2 = "/tmp/ecoUpload/" + questionHandle.getHandleCode() + "/" + date;
                    System.out.println("服務器實際路徑：" + Path2);
                    File folder = new File(Path);//此处打包上去之前需要置换路径
                    if (!folder.exists()) {
                        folder.mkdirs();
                    }//无报错则上传成功
                    //获取上传者
                    multipartFile.transferTo(new File(folder, multipartFile.getOriginalFilename()));
                    String url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/ecoUpload" + "/" + multipartFile.getOriginalFilename();
                    System.out.println(url);//真实存储的url
                    //String newUrl = req.getServletContext().getRealPath("/ecoUpload") +"/"+epartner.getPartnerName()+"/" + multipartFile.getOriginalFilename();
                    //System.out.println("真实URL：" + newUrl);
                    //本地路径测试文件上传
                    String URL = "D:/66/testUpload/ecoUpload/questionHandle/file/" + questionHandle.getHandleCode() + "/" + date + "/" + multipartFile.getOriginalFilename();
                    System.out.println("本地存儲URL:" + URL);
                    //服务器路径测试文件上传
                    String URL2 = "/tmp/ecoUpload/" + questionHandle.getHandleCode() + "/" + date + "/" + multipartFile.getOriginalFilename();
                    System.out.println("服務器存儲URL:" + URL2);
                    //进行文件上传记录的存储
                    FileInfo fileInfo = new FileInfo();
                    fileInfo.setFileName(multipartFile.getOriginalFilename());
                    //本地存储
                    fileInfo.setFilePath(URL);
                    //服务器存储   打包上去前需要置换
                    //fileInfo.setFilePath(URL2);

                    fileInfo.setUpdatedTime(new Date());
                    fileInfo.setLastUpdateUser(questionHandle.getHandleCode());
                    fileInfo.setKeyword(multipartFile.getOriginalFilename());
                    fileInfo.setQuestionFeedbackId(questionHandle.getQuestionFeedbackId());
                    fileInfo.setRelDocId(2);
                    String timeFormat = sdf.format(new Date());

                    fileInfo.setUpdatedTime(sdf.parse(timeFormat));
                    fileInfo.setStatus(0);
                    fileInfo.setQuestionHandleId(questionHandle.getQuestionHandleId());
                    fileInfoService.insertSelective(fileInfo);
                    System.out.println(questionHandle.getQuestionFeedbackId());
                    System.out.println(questionHandle.getQuestionHandleId());

                } else {
                    //上傳的附件格式不符合要求
                    return new ResultUtils(505, "上傳的附件格式不符合要求");
                }
            }
        }
//        }else{
//            System.out.println(file.length);
//            fileInfoService.updateAllFileStatusByQuestionHandleId(questionHandle.getQuestionHandleId());
//        }
        return resultUtils;



    }


}
