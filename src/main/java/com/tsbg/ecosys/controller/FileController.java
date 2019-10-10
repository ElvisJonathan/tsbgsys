package com.tsbg.ecosys.controller;

import com.tsbg.ecosys.util.ResultUtils;
import com.tsbg.ecosys.model.Epartner;
import com.tsbg.ecosys.model.FileInfo;
import com.tsbg.ecosys.model.UserInfo;
import com.tsbg.ecosys.model.bag.CompanyPackage;
import com.tsbg.ecosys.service.EpartnerService;
import com.tsbg.ecosys.service.FileInfoService;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 文件上传与下载
 */
@Controller
@RequestMapping("/tsbg/upload")
public class FileController {

    @Autowired
    private FileInfoService fileInfoService;
    @Autowired
    private EpartnerService epartnerService;

    //跳转到上传文件的页面
    @RequestMapping(value = "/gouploadimg", method = RequestMethod.GET)
    public String goUploadImg() {
        //跳转到 templates 目录下的 uploadimg.html
        return "uploadimg";
    }

    SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");

    //新增时的上传
    @RequestMapping(value = "/import", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResultUtils importData(HttpServletRequest req) throws IOException {
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        if (isMultipart) {
            MultipartHttpServletRequest multipartRequest = WebUtils.getNativeRequest(req, MultipartHttpServletRequest.class);
            MultipartFile file = multipartRequest.getFile("file");
            if (file.isEmpty()) {
                return new ResultUtils(500, "上传文件为空！");
            }
            //根据原始文件名的后缀进行文件类型判断
            String oldName = file.getOriginalFilename();
            System.out.println("原始文件名：" + oldName);
            //进行重复文件名判断
            /*int count = fileInfoService.selectFileCountByFileName(oldName);
            if (count > 0) {
                return new ResultResponse(501, "该文件已存在，请选择其他文件上传!");
            }*/
            String Suffix = oldName.substring(oldName.lastIndexOf("."));
            System.out.println("文件后缀：" + Suffix);
            if (Suffix.equals(".xls") || Suffix.equals(".xlsx") || Suffix.equals(".xlsm") || Suffix.equals(".doc")
                    || Suffix.equals(".docx") || Suffix.equals(".pdf") || Suffix.equals(".ppt") || Suffix.equals(".pptx")
                    || Suffix.equals(".txt")) {
                //String format = sdf.format(new Date());//用于转换当前日期
                String realPath = req.getServletContext().getRealPath("/ecoUpload");//此方法用于获取上传路径
                System.out.println("实际路径：" + realPath);
                File folder = new File(realPath);
                if (!folder.exists()) {
                    folder.mkdirs();
                }//无报错则上传成功
                //获取上传者
                file.transferTo(new File(folder, oldName));
                String url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/ecoUpload"+"/" + oldName;
                System.out.println(url);//真实存储的url
                //进行文件上传记录的存储
                FileInfo fileInfo = new FileInfo();
                fileInfo.setFileName(oldName);
                fileInfo.setFilePath(url);
                fileInfo.setUpdatedTime(new Date());
                fileInfo.setKeyword(oldName);
                int num = fileInfoService.insertSelective(fileInfo);
                //查询出当前成功文件的编号
                int number = epartnerService.selectID();
                System.out.println("刚刚增加成功的记录的编号为：" + number);
                if (num > 0) {
                    return new ResultUtils(0, "上传成功！");
                }
                return new ResultUtils(503, "上传失败！");
            }
            return new ResultUtils(505, "上传文件格式不符合需求");
        }
        return new ResultUtils(506, "没有文件");
    }

    //多文件的上传
    @RequestMapping(value = "/importmore", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResultUtils importMore(MultipartFile[] file, HttpServletRequest req) throws IOException {
         StringBuffer buffer = new StringBuffer();
        // int[] array = new int[file.length];
         for (MultipartFile multipartFile : file) {
             //重复文件名判断
             /*int count = fileInfoService.selectFileCountByFileName(multipartFile.getOriginalFilename());
             if (count > 0) {
                 return new ResultResponse(501, "有文件已存在，请选择其他文件上传!");
             }*/
             buffer.append(multipartFile.getOriginalFilename());
             buffer.append(",");
             String all = buffer.substring(0, buffer.length() - 1);
             System.out.println("所有文件："+all);
             String Suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
             System.out.println("文件后缀：" + Suffix);
             //根据原始文件名的后缀进行文件类型判断
             if (Suffix.equals(".xls") || Suffix.equals(".xlsx") || Suffix.equals(".xlsm") || Suffix.equals(".doc")
                     || Suffix.equals(".docx") || Suffix.equals(".pdf") || Suffix.equals(".ppt") || Suffix.equals(".pptx")
                     || Suffix.equals(".txt")) {
                 String realPath = req.getServletContext().getRealPath("/ecoUpload");//此方法用于获取上传路径
                 System.out.println("实际路径：" + realPath);
                 File folder = new File(realPath);
                 if (!folder.exists()) {
                     folder.mkdirs();
                 }//无报错则上传成功
                 //获取上传者
                 multipartFile.transferTo(new File(folder, multipartFile.getOriginalFilename()));
                 String url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/ecoUpload"+"/"+ multipartFile.getOriginalFilename();
                 System.out.println(url);//真实存储的url
                 String newUrl = req.getServletContext().getRealPath("/ecoUpload")+"/" + multipartFile.getOriginalFilename();
                 System.out.println("真实URL："+newUrl);
                 //进行文件上传记录的存储
                 FileInfo fileInfo = new FileInfo();
                 fileInfo.setFileName(multipartFile.getOriginalFilename());
                 fileInfo.setFilePath(newUrl);
                 fileInfo.setUpdatedTime(new Date());
                 fileInfo.setKeyword(multipartFile.getOriginalFilename());
                 int num = fileInfoService.insertSelective(fileInfo);
                 //查询出当前成功文件的编号
                 int number = epartnerService.selectID();
                 System.out.println("刚刚增加成功的记录的编号为：" + number);
             }else{
                 return new ResultUtils(505, "上传文件格式不符合需求");
             }
         }
         return null;
    }

    @RequestMapping(value = "/download", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResultUtils downloadFile(@RequestBody CompanyPackage companyPackage){
        //通过接收公司的partnerNo和userCode来记录下载者 还需要获取文件名
        Epartner epartner = companyPackage.getEpartner();
        Integer partnerNo= epartner.getPartnerNo();
        UserInfo userInfo = companyPackage.getUserInfo();
        String userCode = userInfo.getUserCode();
        if (partnerNo!=null && userCode!=null){
           int num = epartnerService.logDownloader(userCode,partnerNo);
           if (num>0){
               return new ResultUtils(0,"记录下载者成功!");
           }
           return  new ResultUtils(500,"记录下载者失败！");
        }
        return new ResultUtils(501,"合作伙伴编号或工号为空异常！");
    }

    /**
     * 下载
     */
    @RequestMapping(value = "/testdownload", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Object downloadFile(HttpServletResponse response, HttpServletRequest request){
        String fileName = request.getParameter("fileName");
        if (fileName==null){
            return "未收到文件名";
        }
        System.out.println("文件名："+fileName);
        Integer partnerNo=Integer.parseInt(request.getParameter("partnerNo"));
        System.out.println("收到公司编号为："+partnerNo);
        //获取userCode用于记录最后下载者  让前端传
        String userCode = request.getParameter("userCode");
        System.out.println("获取到的userCode:"+userCode);
        //根据文件名去数据库查询URL
        String name = fileInfoService.selectRealPathByName(fileName,partnerNo);
        System.out.println("真实URL："+name);
        if (name==null){
            System.out.println("下载附件失败，请检查文件“" + fileName + "”是否存在");
            return "下载附件失败，请检查文件“" + fileName + "”是否存在";
        }
        OutputStream os = null;
        InputStream is= null;
        try {
            // 取得输出流
            os = response.getOutputStream();
            // 清空输出流
            response.reset();
            response.setContentType("application/x-download;charset=GBK");
            response.setHeader("Content-Disposition", "attachment;filename="+ new String(fileName.getBytes("utf-8"), "iso-8859-1"));
            //读取流
            File f = new File(name);
            is = new FileInputStream(f);
            //复制
            IOUtils.copy(is, response.getOutputStream());
            response.getOutputStream().flush();
            //通过公司编号和文件名定位文件编号
            Integer fileNo = fileInfoService.selectFileNo(partnerNo,fileName);
            if (fileNo!=null){
                //根据公司编号和用户工号去修改最后下载者
                fileInfoService.updateDownloader(userCode,fileNo);
            }
        } catch (IOException e) {
            return "下载附件失败,error:"+e.getMessage();
        }
            //文件的关闭放在finally中
        finally
        {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                System.out.println(ExceptionUtils.getFullStackTrace(e));
            }
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                System.out.println(ExceptionUtils.getFullStackTrace(e));
            }
        }
        return "下载成功！";
    }


    /**
     * 下载
     */
    @RequestMapping(value = "/testdownload1", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Object downloadQuestionHandleFile(HttpServletResponse response, HttpServletRequest request){
        String fileName = request.getParameter("fileName");
        if (fileName==null){
            return "文件名為空！";
        }
        System.out.println("文件名："+fileName);
        Integer questionHandleId=Integer.parseInt(request.getParameter("questionHandleId"));
        System.out.println("收到處理反饋附件編號為："+questionHandleId);
        //獲取userCode用於記錄最后下载者  让前端传
        String userCode = request.getParameter("userCode");
        System.out.println("獲取到的userCode:"+userCode);
        //根據文件名去數據庫查詢URL
        String name = fileInfoService.selectRealPathByNameAndQuestionHandleId(fileName,questionHandleId);
        System.out.println("真实URL："+name);
        if (name==null){
            System.out.println("下载附件失败，请检查文件“" + fileName + "”是否存在");
            return "下载附件失败，请检查文件“" + fileName + "”是否存在";
        }
        OutputStream os = null;
        InputStream is= null;
        try {
            // 取得输出流
            os = response.getOutputStream();
            // 清空输出流
            response.reset();
            response.setContentType("application/x-download;charset=GBK");
            response.setHeader("Content-Disposition", "attachment;filename="+ new String(fileName.getBytes("utf-8"), "iso-8859-1"));
            //读取流
            File f = new File(name);
            is = new FileInputStream(f);
            //复制
            IOUtils.copy(is, response.getOutputStream());
            response.getOutputStream().flush();
            //通过公司编号和文件名定位文件编号
            Integer fileNo = fileInfoService.selectFileNoByQuestionHandleIdAdnFileName(questionHandleId,fileName);
            if (fileNo!=null){
                //根据公司编号和用户工号去修改最后下载者
                fileInfoService.updateDownloader(userCode,fileNo);
            }
        } catch (IOException e) {
            return "下载附件失败,error:"+e.getMessage();
        }
        //文件的关闭放在finally中
        finally
        {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                System.out.println(ExceptionUtils.getFullStackTrace(e));
            }
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                System.out.println(ExceptionUtils.getFullStackTrace(e));
            }
        }
        return "下载成功！";
    }

    /**
     * 下载
     */
    @RequestMapping(value = "/testdownload2", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Object downloadQuestionFeedBackFile(HttpServletResponse response, HttpServletRequest request){
        String fileName = request.getParameter("fileName");
        if (fileName==null){
            return "文件名為空！";
        }
        System.out.println("文件名："+fileName);
        Integer questionFeedbackId=Integer.parseInt(request.getParameter("questionFeedbackId"));
        System.out.println("收到處理反饋附件編號為："+questionFeedbackId);
        //獲取userCode用於記錄最后下载者  让前端传
        String userCode = request.getParameter("userCode");
        System.out.println("獲取到的userCode:"+userCode);
        //根據文件名去數據庫查詢URL
        String name = fileInfoService.selectRealPathByNameAndQuestionFeedBackId(fileName,questionFeedbackId);
        System.out.println("真实URL："+name);
        if (name==null){
            System.out.println("下载附件失败，请检查文件“" + fileName + "”是否存在");
            return "下载附件失败，请检查文件“" + fileName + "”是否存在";
        }
        OutputStream os = null;
        InputStream is= null;
        try {
            // 取得输出流
            os = response.getOutputStream();
            // 清空输出流
            response.reset();
            response.setContentType("application/x-download;charset=GBK");
            response.setHeader("Content-Disposition", "attachment;filename="+ new String(fileName.getBytes("utf-8"), "iso-8859-1"));
            //读取流
            File f = new File(name);
            is = new FileInputStream(f);
            //复制
            IOUtils.copy(is, response.getOutputStream());
            response.getOutputStream().flush();
            //通过问题反馈编号和文件名定位文件编号
            //Integer fileNo = fileInfoService.selectFileNoByQuestionFeedbackIdAdnFileName(questionFeedbackId,fileName);
            /*if (fileNo!=null){
                //根据问题反馈编号和用户工号去修改最后下载者
                fileInfoService.updateDownloader(userCode,fileNo);
            }*/
        } catch (IOException e) {
            return "下载附件失败,error:"+e.getMessage();
        }
        //文件的关闭放在finally中
        finally
        {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                System.out.println(ExceptionUtils.getFullStackTrace(e));
            }
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                System.out.println(ExceptionUtils.getFullStackTrace(e));
            }
        }
        return "下载成功！";
    }


    /**
     *  頁面點擊刪除文件
     */
    @RequestMapping(value = "/deleteFileByFileNameAndQuestionFeedBackId", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String deleteFileByFileNameAndQuestionFeedBackId(@RequestBody FileInfo fileInfo){

        String message="文件刪除失敗！";
        String fileName = fileInfo.getFileName();
        if (fileName==null){
            return "文件名為空！";
        }
        System.out.println("文件名："+fileName);
        Integer questionFeedbackId=fileInfo.getQuestionFeedbackId();
        int i = fileInfoService.UpdateFileByFileNameAndQuestionFeedBackId(fileName, questionFeedbackId);
        System.out.println("i:"+i);
        if(i>0){
            message="文件刪除成功！";
            System.out.println("刪除成功！："+questionFeedbackId);
        }
        return message;
    }





    /**
     *  頁面點擊刪除文件
     */
    @RequestMapping(value = "/deleteFileByFileNameAndQuestionHandleId", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String deleteFileByFileNameAndQuestionHandleId(@RequestBody FileInfo fileInfo){
        String message="文件刪除失敗！";
        String fileName = fileInfo.getFileName();
        if (fileName==null){
            return "文件名為空！";
        }
        System.out.println("文件名："+fileName);
        Integer questionHandleId=fileInfo.getQuestionHandleId();
        System.out.println("test:"+fileInfoService.UpdateFileByFileNameAndQuestionHandleId(fileName,questionHandleId));
        if(fileInfoService.UpdateFileByFileNameAndQuestionHandleId(fileName,questionHandleId)>0){
            message="文件刪除成功！";
            System.out.println("刪除成功！："+questionHandleId);
        }

        return message;
    }
}
