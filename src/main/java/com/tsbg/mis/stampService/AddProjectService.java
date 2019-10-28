package com.tsbg.mis.stampService;

import com.alibaba.fastjson.JSONObject;

import com.tsbg.mis.powerModel.FileInfo;
import com.tsbg.mis.stampMapper.AddProjectMapper;
import com.tsbg.mis.stampVo.AddProjectVo;
import com.tsbg.mis.util.GetBrowserNameUtils;
import com.tsbg.mis.util.ResultUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

@Service
public class AddProjectService {

    @Autowired
    private AddProjectMapper addProjectMapper;

    //根据当前登录人的user_code查询员工信息
    public AddProjectVo selectUserInfoByUserCode(String userCode){
        return addProjectMapper.selectUserInfoByUserCode(userCode);
    }

    //根据工号查询员工是否存在
    public int selectUserCode(String userCode){
        return addProjectMapper.selectUserCode(userCode);
    }

    //查询所有厂区
    public List<AddProjectVo> selectFactory(Map<String,Object> map){
        return  addProjectMapper.selectFactory(map);
    }

    //查询所有事业群
    public  List<AddProjectVo> selectBg(Map<String,Object> map){
        return  addProjectMapper.selectBg(map);
    }

    //查询所有事业处
    public  List<AddProjectVo> selectBu(Map<String,Object> map){
        return  addProjectMapper.selectBu(map);
    }

    //查询所有印章类别
    public List<AddProjectVo> selectTypeName(Map<String,Object> map){
        return addProjectMapper.selectTypeName(map);
    }

    //新增用印信息
    public ResultUtils insert(HttpServletRequest req, MultipartFile[] file) throws Exception{
        String json = req.getParameter("addProjectVo");
        AddProjectVo addProjectVo = JSONObject.parseObject(json, AddProjectVo.class);
        ResultUtils resultUtils = new ResultUtils();
        String substring2 = null;
        //校验表单数据
        if(!StringUtils.isBlank(addProjectVo.getUserCode()) && !StringUtils.isBlank(addProjectVo.getUserName()) && !StringUtils.isBlank(addProjectVo.getEmailAddress()) &&null != addProjectVo.getFactoryId() && null != addProjectVo.getBGId()
                && null != addProjectVo.getBUId() && !StringUtils.isBlank(addProjectVo.getStampUseFileName()) && null != addProjectVo.getStampTypeId() && null != addProjectVo.getUseStampNum()){
            addProjectVo.setStatus(1);
            addProjectVo.setSubmitDate(new Date());
            addProjectVo.setLastUpdateDate(new Date());
            //int count = addProjectMapper.selectCountApplyNum();
            //将当前时间转成String类型
            Date currentTime = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            String dateString = format.format(currentTime);
            //获取用印信息表中项目编号最后一条数据
            String lastApplyNum = addProjectMapper.selectLastApplyNum();
            String substring = lastApplyNum.substring(0,8);
            //String dateStringTemp = dateString.substring(0,8);
            //获取项目编号后三位
            substring2 = lastApplyNum.substring(8,11);
            //判断最后一条编号如果不是当天，则后三位归0
            if(!substring.equals(dateString)){
                substring2 = "001";
                addProjectVo.setApplyNum(dateString+substring2);
            }else{//如果是当天，则后三位+1
                //将字符串转换为int类型
                int s = Integer.parseInt(substring2);
                //实现递增
                s = ++s;
                s=s==1000?1:s;
                String result = s<1000?(s<10?("00"+s):(s<100?"0"+s:""+s)):"001";
                //System.out.println("result-->"+result);
                String newApplyNum = dateString + result;
                addProjectVo.setApplyNum(newApplyNum);
            }
            //文件上传
            if(file.length>0){
                Map<String,Object> map =getFile(file);
                StringBuffer buffer = new StringBuffer();
                if("0".equals(map.get("code"))){
                    int code = 501;
                    String message = map.get("message").toString();
                    resultUtils.setCode(code);
                    resultUtils.setMessage(message);
                    return resultUtils;
                }else{
                    for (MultipartFile multipartFile: file) {
                        //获取文件后缀
                        String Suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
                        String agent = req.getHeader("User-Agent").toLowerCase();
                        //获取浏览器版本
                        String browserName = GetBrowserNameUtils.getBrowserName(agent);
                        System.out.println("当前浏览器版本："+browserName);
                        String prefixName = null;
                        if(browserName.contains("ie") || browserName.contains("edge") || browserName.contains("webkit")){//若爲IE或者Edge瀏覽器，則對上傳的文件名做處理
                            prefixName = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("\\") + 1, multipartFile.getOriginalFilename().lastIndexOf("."));
                        }
                        Date time = new Date();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String date = dateFormat.format(time);
                        date = date.replaceAll(":","");
                        //本地文件上传路径
                        String localPath = "F:/updateFlie/" + addProjectVo.getUserCode() + "/" + date;
                        //服务器文件上传路径
                        String serverPath = "/tmp/stampUpload/file/" + addProjectVo.getUserCode() + "/" +date;
                        //项目打包部署前要将文件上传路径改为serverPath
                        File folder = new File(serverPath);
                        if(!folder.exists()){
                            folder.mkdirs();
                        }
                        //无报错则上传成功，并获取上传人工号
                        if(prefixName==null){
                            multipartFile.transferTo(new File(folder, multipartFile.getOriginalFilename()));
                            String url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/stampUpload" + "/" + multipartFile.getOriginalFilename();
                            System.out.println(url);//真实存储的url

                            //本地路径测试文件上传
                            String localURL = "F:/updateFlie/" + addProjectVo.getUserCode() + "/" + date + "/" + multipartFile.getOriginalFilename();
                            System.out.println("本地存儲URL:" + localURL);
                            //服务器路径测试文件上传
                            String serverURL = "/tmp/stampUpload/file/" + addProjectVo.getUserCode() + "/" + date + "/" + multipartFile.getOriginalFilename();
                            System.out.println("服務器存儲URL:" + serverURL);
                            //进行文件上传记录的存储
                            FileInfo fileInfo = new FileInfo();
                            fileInfo.setFileName(multipartFile.getOriginalFilename());

                            fileInfo.setUpdatedTime(new Date());
                            fileInfo.setLastUpdateUser(addProjectVo.getUserCode());
                            fileInfo.setKeyword(multipartFile.getOriginalFilename());
                            fileInfo.setRelTableName("stamp_use_management");
                            String timeFormat = dateFormat.format(new Date());
                            fileInfo.setUpdatedTime(dateFormat.parse(timeFormat));
                            addProjectMapper.insert(addProjectVo);
                            fileInfo.setStatus(0);
                            fileInfo.setProjId(4);
                            fileInfo.setRelDocId(addProjectMapper.selectLastStampUseId());
                            //本地存储
                            //fileInfo.setFilePath(localURL);
                            //服务器存储   打包上去前需要置换
                            fileInfo.setFilePath(serverURL);
                            addProjectMapper.insertFileInfo(fileInfo);
                        }else{
                            multipartFile.transferTo(new File(folder, prefixName + date +Suffix));
                            String url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/stampUpload" + "/" + prefixName + date +Suffix;
                            System.out.println(url);//真实存储的url

                            //本地路径测试文件上传
                            String localURL = "F:/updateFlie/" + addProjectVo.getUserCode() + "/" + date + "/" + prefixName + date +Suffix;
                            System.out.println("本地存儲URL:" + localURL);
                            //服务器路径测试文件上传
                            String serverURL = "/tmp/stampUpload/file/" + addProjectVo.getUserCode() + "/" + date + "/" + prefixName + date +Suffix;
                            System.out.println("服務器存儲URL:" + serverURL);
                            //进行文件上传记录的存储
                            FileInfo fileInfo = new FileInfo();
                            fileInfo.setFileName(prefixName + date +Suffix);

                            fileInfo.setUpdatedTime(new Date());
                            fileInfo.setLastUpdateUser(addProjectVo.getUserCode());
                            fileInfo.setKeyword(prefixName + date +Suffix);
                            fileInfo.setRelTableName("stamp_use_management");
                            String timeFormat = dateFormat.format(new Date());
                            fileInfo.setUpdatedTime(dateFormat.parse(timeFormat));
                            addProjectMapper.insert(addProjectVo);
                            fileInfo.setStatus(0);
                            fileInfo.setProjId(4);
                            fileInfo.setRelDocId(addProjectMapper.selectLastStampUseId());
                            //本地存储
                            //fileInfo.setFilePath(localURL);
                            //服务器存储   打包上去前需要置换
                            fileInfo.setFilePath(serverURL);
                            addProjectMapper.insertFileInfo(fileInfo);
                        }
                    }
                }
            }else{
                return new ResultUtils(501,"提交失敗，請選擇用印附件");
            }
            return new ResultUtils(100,"提交成功");
        }else {
            return new ResultUtils(501,"提交失敗，請填寫完整信息");
        }
    }

    //对上传的文件格式进行判断
    public Map<String,Object> getFile(MultipartFile[] file) throws Exception{
        Map<String,Object> map = new HashMap<>();
        for (MultipartFile multipartFile : file) {
            //获取文件后缀
            String Suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
            //判断上传的文件类型是否合法
            if(Suffix.equals(".xls") || Suffix.equals(".xlsx") || Suffix.equals(".xlsm") || Suffix.equals(".doc")
                    || Suffix.equals(".docx") || Suffix.equals(".pdf") || Suffix.equals(".ppt") || Suffix.equals(".pptx")
                    || Suffix.equals(".XLS") || Suffix.equals(".XLSX") || Suffix.equals(".XLSM") || Suffix.equals(".DOC")
                    || Suffix.equals(".DOCX") || Suffix.equals(".PDF") || Suffix.equals(".PPT") || Suffix.equals(".PPTX")){
                map.put("code","1");
            }else{
                map.put("code","0");
                map.put("message","提交失敗，上傳的文件格式不符合要求");
                return map;
            }
        }
        return map;
    }

}
