package com.tsbg.ecosys.controller;

import com.alibaba.fastjson.JSONObject;
import com.tsbg.ecosys.config.ResultResponse;
import com.tsbg.ecosys.model.*;
import com.tsbg.ecosys.model.bag.SearchPackage;
import com.tsbg.ecosys.service.*;
import com.tsbg.ecosys.util.PageRequest;
import com.tsbg.ecosys.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 公司相关
 */
@RestController
@RequestMapping("/tsbg/company")
public class CompanyController {

    @Autowired
    private EpartnerService epartnerService;
    @Autowired
    private EcooperationService ecooperationService;
    @Autowired
    private EccontactsService eccontactsService;
    @Autowired
    private FileInfoService fileInfoService;
    @Autowired
    private UserInfoService userInfoService;

    /**
     * 管理员隐藏/取消隐藏公司
     */
    @RequestMapping(value = "/hideCompany", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResultResponse hideCom(@RequestBody Epartner epartner){
        //初始化构造器
        ResultResponse resultResponse = null;
        //获取cid
        Integer cid = epartner.getPartnerNo();
        //获取STATUS
        Integer status = epartner.getStatus();
        //接受到的status为1则是隐藏公司,0则是取消隐藏公司
        if (status!=0 && status!=1){
            resultResponse = new ResultResponse(503,"提示信息：请输入正确的status！");
            return resultResponse;
        }
        if (cid!=null){
            //根据前端传过来的cid作为参数修改公司的状态
            int num = epartnerService.updateByCid(status,cid);
            //同步更新合作关系和联系人状态
            ecooperationService.updateByCid(status,cid);
            eccontactsService.updateByCid(status,cid);
            if (num>0 && status==1){
                resultResponse = new ResultResponse(1,"提示信息：隐藏公司成功！");
                return resultResponse;
            }else if (num>0){
                resultResponse = new ResultResponse(0,"提示信息：取消隐藏公司成功！");
                return resultResponse;
            }else{
                //如果没有修改成功则返回失败
                resultResponse = new ResultResponse(501,"提示信息：公司编号不存在或操作失败！");
                return resultResponse;
            }
        }
        //公司编号为空则返回异常信息
        resultResponse = new ResultResponse(502,"提示信息：公司编号为空异常！");
        return resultResponse;
    }

    /**
     * 合作伙伴信息、合作情况信息、公司联系人信息新增
     * 集成文件上传
     */
    @RequestMapping(value = "/addCompany", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResultResponse addCom(HttpServletRequest req,MultipartFile file)throws Exception {
        //初始化传参构造器
        ResultResponse resultResponse = null;
        //成功获取的对象数据
        String json = req.getParameter("userInfo");
        UserInfo userInfo = JSONObject.parseObject(json, UserInfo.class);
        String json2 = req.getParameter("epartner");
        Epartner epartner = JSONObject.parseObject(json2, Epartner.class);
        String json3 = req.getParameter("ecooperation");
        Ecooperation ecooperation = JSONObject.parseObject(json3, Ecooperation.class);
        String json4 = req.getParameter("eccontacts");
        Eccontacts eccontacts = JSONObject.parseObject(json4, Eccontacts.class);
        //新建arr数组用于存储成功值
        int []arr = new int[4];
        //获取当前添加人
        String userName= userInfo.getUserName();
        String userCode= userInfo.getUserCode();
        //初始化公司id为0
        int no = 0;
        //需要从前台获取合作伙伴信息、合作情况信息、公司联系人信息
        if (epartner.getPartnerName()!=null && epartner.getPartnerIndustry()!=null &&
                epartner.getPartnerRegion()!=null && epartner.getPartnerProduct()!=null
               ){
            //合作伙伴信息中:合作伙伴公司名称、行业、业务主要区域、主营产品/业务/服务不为空才可以进行添加
            //设置创建时间
            epartner.setCreateTime(new Date());
            //设置创建人
            epartner.setCreater(userName);
            //调用存储公司合作伙伴的业务逻辑存储
            int count = epartnerService.insertSelective(epartner);
            if (count>0){
                arr[0]=1;
                //需要把添加成功后的对应合作伙伴编号查询出来
                no = epartnerService.selectID();
            }
        }

        if(ecooperation.getPartnerName()!=null){
            //合作情况信息中：合作伙伴公司名称不能为空
            //设置创建时间
            ecooperation.setCreateTime(new Date());
            //设置创建人
            ecooperation.setCreater(userName);
            //设置合作伙伴编号
            if (no!=0){
                ecooperation.setPartnerNo(no);
            }
            //调用业务方法存储合作关系信息
            int num = ecooperationService.insertSelective(ecooperation);
            if (num>0){
                arr[1]=1;
            }
        }

        if (eccontacts.getName()!=null && eccontacts.getPhoneNumber()!=null
            && eccontacts.getPartnerName()!=null){
            //公司联系人信息中：联系人姓名、性别、电话和所属公司名称不能为空
            //设置创建时间
            eccontacts.setCreateTime(new Date());
            //设置创建人
            eccontacts.setCreater(userName);
            //设置合作伙伴编号
            if (no!=0){
                eccontacts.setPartnerNo(no);
            }
            //调用业务方法存储联系人信息
            int number = eccontactsService.insertSelective(eccontacts);
            if (number>0){
                arr[2]=1;
            }
        }

        //此处增加文件上传
        if (file!=null){
            //根据原始文件名的后缀进行文件类型判断
            String oldName = file.getOriginalFilename();
            System.out.println("原始文件名："+oldName);
            //进行重复文件名判断
            int count = fileInfoService.selectFileCountByFileName(oldName);
            if(count>0){
                return  new ResultResponse(503,"该文件已存在，请选择其他文件上传!");
            }
            String Suffix = oldName.substring(oldName.lastIndexOf("."));
            System.out.println("文件后缀："+Suffix);
            if (Suffix.equals(".xls") || Suffix.equals(".xlsx") || Suffix.equals(".xlsm") || Suffix.equals(".doc")
                    || Suffix.equals(".docx") || Suffix.equals(".pdf") || Suffix.equals(".ppt") || Suffix.equals(".pptx")
                    || Suffix.equals(".txt")){
                //String format = sdf.format(new Date());//用于转换当前日期
                String realPath = req.getServletContext().getRealPath("/ecoUpload");//此方法用于获取上传路径
                System.out.println("实际路径："+realPath);
                File folder = new File(realPath);
                if (!folder.exists()) {
                    folder.mkdirs();
                }//无报错则上传成功
                file.transferTo(new File(folder,oldName));
                String url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/ecoUpload"+"/" + oldName;
                System.out.println(url);
                String newUrl = req.getServletContext().getRealPath("/ecoUpload")+"\\" + oldName;
                System.out.println("真实URL："+newUrl);
                //进行文件上传记录的存储
                FileInfo fileInfo = new FileInfo();
                fileInfo.setFileName(oldName);
                fileInfo.setFilePath(newUrl);
                fileInfo.setRelDocId(no);
                fileInfo.setLastUpdateUser(userCode);
                fileInfo.setUpdatedTime(new Date());
                fileInfo.setKeyword(oldName);
                int num = fileInfoService.insertSelective(fileInfo);
                if (num>0){
                    arr[3]=1;
                }
            }else {
                return new ResultResponse(505,"上传文件格式不符合需求");
            }
          }

        if (arr[0]==1 && arr[1]==1 && arr[2]==1){
            resultResponse = new ResultResponse(0,"提示信息：新增成功！");
            return resultResponse;
        }
        resultResponse = new ResultResponse(501,"提示信息：新增失败或不完全成功！");
        return resultResponse;
    }

    /**
     * 分页查询公司信息+搜索
     */
    @RequestMapping(value = "/findPage", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResultResponse findPage(@RequestBody SearchPackage searchPackage) {
        ResultResponse resultResponse = null;
        //需要前台传参pageQuery:包含pageIndex和pageSize 即起始页码和页面容量 记得容量小于总条数才会有分页效果
        //接受分页参数pageRequest
        PageRequest pageRequest = searchPackage.getPageRequest();
        //接受搜索条件传参实体类epartner
        Epartner epartner = searchPackage.getEpartner();
        //获取当前用户身份是否为管理员，通过工号查询是否为管理
        UserInfo userInfo = searchPackage.getUserInfo();
        String userCode = userInfo.getUserCode();
        System.out.println("工号："+userCode);
       int identity =  userInfoService.selectIdentityByUserCode(userCode);
       System.out.println("身份："+identity);
       epartner.setCreaterIdentity(identity);
       System.out.println("身份2："+epartner.getCreaterIdentity());
        if (pageRequest.getPageIndex()!=0 && pageRequest.getPageSize()!=0){
            //根据给到的分页条件查询公司信息
            PageResult page = epartnerService.findPage(pageRequest, epartner);
            if (page!=null){
                resultResponse = new ResultResponse(0,"提示信息：成功查询到公司信息",page);
                return resultResponse;
            }
            resultResponse = new ResultResponse(501,"提示信息：未查询到公司信息");
            return resultResponse;
        }
        resultResponse = new ResultResponse(502,"提示信息：分页条件不明确异常");
        return resultResponse;
    }

    //查询公司信息
    @RequestMapping("/selectCinfo")
    @ResponseBody
    public ResultResponse searchCinfo(@RequestBody Epartner epartner) {
        ResultResponse resultResponse = null;
        //通过前端传来的信息查询对应的公司信息
        String partnerCname = epartner.getPartnerName();
        String partnerCregion = epartner.getPartnerRegion();
        String partnerCproduct = epartner.getPartnerProduct();
        String partnerCindustry = epartner.getPartnerIndustry();
        if (partnerCname != null || partnerCregion != null || partnerCproduct != null || partnerCindustry != null) {
            List<Epartner> infod1 = epartnerService.selectCinfo(epartner);
            List<Epartner> infod2 = epartnerService.selectCinfo(epartner);
            List<Epartner> infod3 = epartnerService.selectCinfo(epartner);
            List<Epartner> infod4 = epartnerService.selectCinfo(epartner);
            if (infod1 != null) {
                resultResponse = new ResultResponse(0, "提示信息：成功查询到公司信息",infod1);
                return resultResponse;
            }
            else if(infod2 != null){
                resultResponse = new ResultResponse(0, "提示信息：成功查询到公司信息",infod2);
                return resultResponse;
            }
            else if (infod3 != null){
                resultResponse = new ResultResponse(0, "提示信息：成功查询到公司信息",infod3);
                return resultResponse;
            }
            else if (infod4 != null){
                resultResponse = new ResultResponse(0, "提示信息：成功查询到公司信息",infod4);
                return resultResponse;
            }
            resultResponse = new ResultResponse(501, "提示信息：未查询到公司信息");
            return resultResponse;
        }
        resultResponse = new ResultResponse(502, "提示信息：未收到id信息");
        return resultResponse;
    }

    //查询合作关系
    @RequestMapping("/selectCooinfo")
    @ResponseBody
    public ResultResponse searchCooinfo(@RequestBody Ecooperation ecooperation)
    {
        ResultResponse resultResponse = null;
        //通过前端传来的公司id查询对应的合作关系
        Integer partnerNo = ecooperation.getPartnerNo();
        if(partnerNo != null) {
            List<Ecooperation> info = ecooperationService.selectCooinfo(partnerNo);
            if (info != null) {
                resultResponse = new ResultResponse(0, "提示信息：成功查询到合作关系信息", info);
                return resultResponse;
            }
            resultResponse = new ResultResponse(501, "提示信息：未查询到合作关系信息");
            return resultResponse;
        }
        resultResponse = new ResultResponse(502,"提示信息：未收到id信息");
        return resultResponse;
    }
    //查询联系人信息
    @RequestMapping("/selectContacts")
    @ResponseBody
    public ResultResponse searchContacts(@RequestBody Eccontacts eccontacts){
        ResultResponse resultResponse = null;
        //通过前端传来的公司id查询对应的联系人信息
        Integer partnerNo = eccontacts.getPartnerNo();
        if (partnerNo != null) {
            List<Eccontacts> info3 = eccontactsService.selectContacts(partnerNo);
            if (info3 != null) {
                resultResponse = new ResultResponse(0, "提示信息：成功查询到联系人信息", info3);
                return resultResponse;
            }
            resultResponse = new ResultResponse(501, "提示信息：未查询到联系人信息");
            return resultResponse;
        }
        resultResponse = new ResultResponse(502,"提示信息：未收到id信息");
        return resultResponse;
    }

    /**
     * 合作伙伴信息、合作情况信息、公司联系人信息修改
     * 集成文件上传和删除
     */
    @RequestMapping(value = "/updateCompany", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResultResponse modifyCom(MultipartFile file, HttpServletRequest req)throws IOException{
        ResultResponse resultResponse = null;
        //通过接受三个对象来进行修改 包含公司的partnerNo
        //成功获取的对象数据
        String json = req.getParameter("userInfo");
        UserInfo userInfo = JSONObject.parseObject(json, UserInfo.class);
        String json2 = req.getParameter("epartner");
        Epartner epartner = JSONObject.parseObject(json2, Epartner.class);
        String json3 = req.getParameter("ecooperation");
        Ecooperation ecooperation = JSONObject.parseObject(json3, Ecooperation.class);
        String json4 = req.getParameter("eccontacts");
        Eccontacts eccontacts = JSONObject.parseObject(json4, Eccontacts.class);
        Integer cid = epartner.getPartnerNo();
        System.out.println("接收到的partnerNo:"+cid);
        if (ecooperation==null){
            resultResponse = new ResultResponse(502,"提示信息：公司合作关系信息为空异常！");
            return resultResponse;
        }
        if (eccontacts==null){
            resultResponse = new ResultResponse(503,"提示信息：公司联系人信息为空异常！");
            return resultResponse;
        }
        //全都不为空的情况下才可以进行修改判断
        //新建arr数组用于存储成功值
        int []arr = new int[4];
        //获取当前修改人
        String userName= userInfo.getUserName();
        String userCode = userInfo.getUserCode();
        System.out.println("修改人："+userName);
        System.out.println("工号："+userCode);
        if(cid!=null){
            if (epartner.getPartnerName()!=null && epartner.getPartnerIndustry()!=null
                    && epartner.getPartnerRegion()!=null && epartner.getPartnerProduct()!=null){
                //只有当合作伙伴公司名称、行业、业务主要区域、主营产品/业务/服务不为空的情况下才可以进行修改
                //更新修改时间
                epartner.setUpdateTime(new Date());
                //更新修改人
                epartner.setUpdater(userName);
                int num = epartnerService.updateByPrimaryKeySelective(epartner);
                if (num>0){
                    arr[0]=1;
                }
            }

            if (ecooperation.getPartnerName()!=null){
                //只有当合作伙伴公司名称不为空才可以修改
                //更新修改时间
                ecooperation.setUpdateTime(new Date());
                //更新修改人
                ecooperation.setUpdater(userName);
                //需要赋值cid给partnerNo
                ecooperation.setPartnerNo(cid);
                int num = ecooperationService.updateByPartnerNoSelective(ecooperation);
                if (num>0){
                    arr[1]=1;
                }
            }

            if (eccontacts.getName()!=null && eccontacts.getPhoneNumber()!=null
            && eccontacts.getPartnerName()!=null){
                //只有当姓名、电话、所属公司都不为空才可以修改
                //更新修改时间
                eccontacts.setUpdateTime(new Date());
                //更新修改人
                eccontacts.setUpdater(userName);
                //需要赋值cid给partnerNo
                eccontacts.setPartnerNo(cid);
                int num = eccontactsService.updateByPartnerNoSelective(eccontacts);
                if (num>0){
                    arr[2]=1;
                }
            }

            if(file.getSize()>0){
                String oldName = file.getOriginalFilename();
                System.out.println("原始文件名："+oldName);
                //根据公司合作伙伴编号和文件名查询附件是否未修改
                int number  = fileInfoService.judgeIfFileChanged(cid,oldName);
                if (number>0){
                    //说明文件未修改
                    arr[3]=1;
                }
            }

            //此处进行文件的删除:如果没有收到文件证明文件已被删除
            if (file.getSize()==0){
                //修改文件的status
                int num = epartnerService.deleteFileByParNo(cid);
                if (num>0){
                    arr[3]=1;
                }
            }

            if (arr[3]==0){
                //说明文件被修改
                //根据原始文件名的后缀进行文件类型判断
                String oldName = file.getOriginalFilename();
                System.out.println("原始文件名："+oldName);
                //进行重复文件名判断
                int count = fileInfoService.selectFileCountByFileName(oldName);
                if(count>0){
                    return  new ResultResponse(505,"该文件已存在，请选择其他文件上传!");
                }
                String Suffix = oldName.substring(oldName.lastIndexOf("."));
                System.out.println("文件后缀："+Suffix);
                if (Suffix.equals(".xls") || Suffix.equals(".xlsx") || Suffix.equals(".xlsm") || Suffix.equals(".doc")
                        || Suffix.equals(".docx") || Suffix.equals(".pdf") || Suffix.equals(".ppt") || Suffix.equals(".pptx")
                        || Suffix.equals(".txt")){
                    //String format = sdf.format(new Date());//用于转换当前日期
                    String realPath = req.getServletContext().getRealPath("/ecoUpload");//此方法用于获取上传路径
                    System.out.println("实际路径："+realPath);
                    File folder = new File(realPath);
                    if (!folder.exists()) {
                        folder.mkdirs();
                    }//无报错则上传成功
                    file.transferTo(new File(folder,oldName));
                    String url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/ecoUpload"+"/"+ oldName;
                    System.out.println(url);//真实存储的url
                    String newUrl = req.getServletContext().getRealPath("/ecoUpload")+"\\" + oldName;
                    System.out.println("真实URL："+newUrl);
                    //进行文件上传记录的存储
                    FileInfo fileInfo = new FileInfo();
                    fileInfo.setFileName(oldName);
                    fileInfo.setFilePath(newUrl);
                    fileInfo.setRelDocId(cid);
                    fileInfo.setLastUpdateUser(userCode);
                    fileInfo.setUpdatedTime(new Date());
                    fileInfo.setKeyword(oldName);
                    int num = fileInfoService.insertSelective(fileInfo);
                    if (num>0){
                        arr[3]=1;
                    }
                }else{
                    return new ResultResponse(506,"上传文件格式不符合需求");
                }
            }

            if (arr[0]==1 && arr[1]==1 && arr[2]==1 && arr[3]==1){
                resultResponse = new ResultResponse(0,"提示信息：修改成功！");
                return resultResponse;
            }
            resultResponse = new ResultResponse(508,"提示信息：修改不彻底！");
            return resultResponse;
        }
        resultResponse = new ResultResponse(507,"提示信息：未查到对应公司合作伙伴编号！");
        return resultResponse;
    }

    /**
     * 管理员删除公司（软删除）
     */
    @RequestMapping(value = "/del", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResultResponse delCom(@RequestBody Epartner epartner){
        //通过接受partnerNo进行删除（隐藏） 需要对其合作伙伴以及联系人同时删除（隐藏）
        //需要同时修改三张表的状态
        ResultResponse resultResponse = null;
        //获取公司对应的合作伙伴编号partnerNo
        Integer cid = epartner.getPartnerNo();
        if (cid!=null){
            int num = epartnerService.updateStatusByCid(cid);
            if (num>0){
                //如果成功修改了公司状态则同步修改联系人和合作关系
                Integer partnerNo = cid;
                int count = eccontactsService.updateStatusByCid(partnerNo);
                int count2 = ecooperationService.updateStatusByCid(partnerNo);
                if (count>0 && count2>0){
                    resultResponse = new ResultResponse(0,"提示信息：删除成功！");
                    return resultResponse;
                }
                resultResponse = new ResultResponse(500,"提示信息：删除不彻底！");
                return resultResponse;
            }
            resultResponse = new ResultResponse(501,"提示信息：删除失败！");
            return resultResponse;
        }
        resultResponse = new ResultResponse(502,"提示信息：公司合作伙伴编号为空异常！");
        return resultResponse;
    }
}
