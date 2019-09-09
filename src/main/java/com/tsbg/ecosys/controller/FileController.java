package com.tsbg.ecosys.controller;

import com.tsbg.ecosys.config.ResultResponse;
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
    public ResultResponse importData(HttpServletRequest req) throws IOException {
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        if (isMultipart) {
            MultipartHttpServletRequest multipartRequest = WebUtils.getNativeRequest(req, MultipartHttpServletRequest.class);
            MultipartFile file = multipartRequest.getFile("file");
            if (file.isEmpty()) {
                return new ResultResponse(500, "上传文件为空！");
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
                    return new ResultResponse(0, "上传成功！");
                }
                return new ResultResponse(503, "上传失败！");
            }
            return new ResultResponse(505, "上传文件格式不符合需求");
        }
        return new ResultResponse(506, "没有文件");
    }

    //多文件的上传
    @RequestMapping(value = "/importmore", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResultResponse importMore(MultipartFile[] file,HttpServletRequest req) throws IOException {
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
                 return new ResultResponse(505, "上传文件格式不符合需求");
             }
         }
         return null;
    }

    @RequestMapping(value = "/download", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResultResponse downloadFile(@RequestBody CompanyPackage companyPackage){
        //通过接收公司的partnerNo和userCode来记录下载者 还需要获取文件名
        Epartner epartner = companyPackage.getEpartner();
        Integer partnerNo= epartner.getPartnerNo();
        UserInfo userInfo = companyPackage.getUserInfo();
        String userCode = userInfo.getUserCode();
        if (partnerNo!=null && userCode!=null){
           int num = epartnerService.logDownloader(userCode,partnerNo);
           if (num>0){
               return new ResultResponse(0,"记录下载者成功!");
           }
           return  new ResultResponse(500,"记录下载者失败！");
        }
        return new ResultResponse(501,"合作伙伴编号或工号为空异常！");
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
}
