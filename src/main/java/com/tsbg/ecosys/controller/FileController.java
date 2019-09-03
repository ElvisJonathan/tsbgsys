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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    /*private final static String rootPath =
            System.getProperty("user.home")+File.separator+fileDir+File.separator;*/

    //跳转到上传文件的页面
    @RequestMapping(value="/gouploadimg", method = RequestMethod.GET)
    public String goUploadImg() {
        //跳转到 templates 目录下的 uploadimg.html
        return "uploadimg";
    }

    SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");

    @RequestMapping(value = "/import", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResultResponse importData(MultipartFile file, HttpServletRequest req, @RequestBody CompanyPackage companyPackage) throws IOException {
        //获取对应公司的合作伙伴编号
        Epartner epartner = companyPackage.getEpartner();
        Integer partnerNo = epartner.getPartnerNo();
        if (file.isEmpty()) {
            return new ResultResponse(500,"上传文件为空！");
        }
        //根据原始文件名的后缀进行文件类型判断
        String oldName = file.getOriginalFilename();
        System.out.println("原始文件名："+oldName);
        //进行重复文件名判断
        int count = fileInfoService.selectFileCountByFileName(oldName);
        if(count>0){
            return  new ResultResponse(501,"该文件已存在，请选择其他文件上传!");
        }
        String Suffix = oldName.substring(oldName.lastIndexOf("."));
        System.out.println("文件后缀："+Suffix);
        if (Suffix.equals(".xls") || Suffix.equals(".xlsx") || Suffix.equals(".xlsm") || Suffix.equals(".doc")
        || Suffix.equals(".docx") || Suffix.equals(".pdf") || Suffix.equals(".ppt") || Suffix.equals(".pptx")
        || Suffix.equals(".txt")){
            String format = sdf.format(new Date());//用于转换当前日期
            String realPath = req.getServletContext().getRealPath("/ecoUpload") + format;//此方法用于获取上传路径
            System.out.println("实际路径："+realPath);
            File folder = new File(realPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }//无报错则上传成功
            //获取上传者
            UserInfo userInfo = companyPackage.getUserInfo();
            String userCode = userInfo.getUserCode();
            System.out.println("上传者："+userCode);
            file.transferTo(new File(folder,oldName));
            String url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/ecoUpload" + format + oldName;
            System.out.println(url);//真实存储的url
            //进行文件上传记录的存储
            FileInfo fileInfo = new FileInfo();
            fileInfo.setFileName(oldName);
            fileInfo.setFilePath(url);
            fileInfo.setRelDocId(partnerNo);//与epartner表的partnerNo对应 要集成到新增和修改页面
            fileInfo.setLastUpdateUser(userCode);
            fileInfo.setUpdatedTime(new Date());
            fileInfo.setKeyword(oldName);
            int num = fileInfoService.insertSelective(fileInfo);
            if (num>0){
                return new ResultResponse(0,"上传成功！");
            }
            return new ResultResponse(503,"上传失败！");
        }
        return new ResultResponse(505,"上传文件格式不符合需求");
    }


    @RequestMapping(value = "/download", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResultResponse downloadFile(@RequestBody CompanyPackage companyPackage){
        //通过接收公司的partnerNo和userCode来记录下载者
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
    @RequestMapping("/testdownload")
    public Object downloadFile(@RequestParam String fileName, final HttpServletResponse response, final HttpServletRequest request){
        OutputStream os = null;
        InputStream is= null;
        try {
            // 取得输出流
            os = response.getOutputStream();
            // 清空输出流
            response.reset();
            response.setContentType("application/x-download;charset=GBK");
            response.setHeader("Content-Disposition", "attachment;filename="+ new String(fileName.getBytes("utf-8"), "iso-8859-1"));
            String rootPath = "http://localhost:8080/ecoUpload/";
            //读取流
            File f = new File(rootPath+fileName);
            is = new FileInputStream(f);
            if (is == null) {
                System.out.println("下载附件失败，请检查文件“" + fileName + "”是否存在");
                return "下载附件失败，请检查文件“" + fileName + "”是否存在";
            }
            //复制
            IOUtils.copy(is, response.getOutputStream());
            response.getOutputStream().flush();
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