package com.tsbg.ecosys.controller;

import com.alibaba.fastjson.JSONObject;
import com.tsbg.ecosys.config.ResultResponse;
import com.tsbg.ecosys.model.*;
import com.tsbg.ecosys.model.bag.CompanyPackage;
import com.tsbg.ecosys.model.bag.HidePackage;
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
        //获取cid 此处需要改为获取数组
        /*Object[] data = hidePackage.getData();
        for (int i =0;i<=data.length-1;i++){
            System.out.println("公司数组："+data[i]);
        }
        int[] data2 = new int[data.length];
        for (int i =0;i<=data.length-1;i++){
            data2[i]=(int)data[i];
        }*/
        Integer cid = epartner.getPartnerNo();
        //获取STATUS
        Integer status = epartner.getStatus();
        System.out.println("修改的状态："+status);
        //接受到的status为1则是隐藏公司,0则是取消隐藏公司
        if (status!=0 && status!=1){
            resultResponse = new ResultResponse(503,"提示信息：请输入正确的status！");
            return resultResponse;
        }
        //int num = 0;
        if (cid!=null){
            int num = epartnerService.updateByCid(status,cid);
            ecooperationService.updateByCid(status,cid);
            eccontactsService.updateByCid(status,cid);
            /*for (int i=0;i<=data.length-1;i++){
                 num =  epartnerService.updateByCid(status,data2[i]);
                ecooperationService.updateByCid(status,data2[i]);
                eccontactsService.updateByCid(status,data2[i]);
            }*/
            //根据前端传过来的cid作为参数修改公司的状态
            //同步更新合作关系和联系人状态
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
        return new ResultResponse(503,"partnerNo为空异常！");
    }

    /**
     * 合作伙伴信息、合作情况信息、公司联系人信息新增
     * 集成文件上传
     */
    @RequestMapping(value = "/addCompany", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResultResponse addCom(HttpServletRequest req,MultipartFile[] file)throws Exception {
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
        //通过用户工号来查询相应权限进行权限判断  执行此功能必须要有add权限

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
        if (file!=null) {
            StringBuffer buffer = new StringBuffer();
            for (MultipartFile multipartFile : file) {
                //重复文件名判断
                int count = fileInfoService.selectFileCountByFileName(multipartFile.getOriginalFilename(),no);
                if (count > 0) {
                    //如果文件存在则将之前新增的记录删除
                    epartnerService.deleteByPrimaryKey(no);
                    ecooperationService.deleteByPrimaryKey2(no);
                    eccontactsService.deleteByPrimaryKey3(no);
                    return new ResultResponse(501, "有文件已存在，请选择其他文件上传!");
                }
                buffer.append(multipartFile.getOriginalFilename());
                buffer.append(",");
                String all = buffer.substring(0, buffer.length() - 1);
                System.out.println("所有文件：" + all);
                String Suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
                System.out.println("文件后缀：" + Suffix);
                //根据原始文件名的后缀进行文件类型判断
                if (Suffix.equals(".xls") || Suffix.equals(".xlsx") || Suffix.equals(".xlsm") || Suffix.equals(".doc")
                        || Suffix.equals(".docx") || Suffix.equals(".pdf") || Suffix.equals(".ppt") || Suffix.equals(".pptx")
                        || Suffix.equals(".txt")) {
                    String realPath = req.getServletContext().getRealPath("/ecoUpload"+"/"+epartner.getPartnerName());//此方法用于获取上传路径
                    //本地路径测试文件上传
                    String Path = "D:/66/testUpload/ecoUpload/"+epartner.getPartnerName();
                    System.out.println("本地实际路径：" + Path);
                    //服务器路径测试文件上传
                    String Path2 = "/tmp/ecoUpload/"+epartner.getPartnerName();
                    System.out.println("服务器实际路径：" + Path2);
                    File folder = new File(Path2);//此处打包上去之前需要置换路径
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
                    String URL = "D:/66/testUpload/ecoUpload/"+epartner.getPartnerName()+"/" + multipartFile.getOriginalFilename();
                    System.out.println("本地存储URL:"+URL);
                    //服务器路径测试文件上传
                    String URL2 = "/tmp/ecoUpload/"+epartner.getPartnerName()+"/" + multipartFile.getOriginalFilename();
                    System.out.println("服务器存储URL:"+URL2);
                    //进行文件上传记录的存储
                    FileInfo fileInfo = new FileInfo();
                    fileInfo.setFileName(multipartFile.getOriginalFilename());
                    //本地存储
                    //fileInfo.setFilePath(URL);
                    //服务器存储   打包上去前需要置换
                    fileInfo.setFilePath(URL2);
                    fileInfo.setRelDocId(no);
                    fileInfo.setUpdatedTime(new Date());
                    fileInfo.setLastUpdateUser(userCode);
                    fileInfo.setKeyword(multipartFile.getOriginalFilename());
                    int num = fileInfoService.insertSelective(fileInfo);
                    //查询出当前成功文件的编号
                    int number = epartnerService.selectID();
                    System.out.println("刚刚增加成功的记录的编号为：" + number);
                } else {
                    //如果文件存在则将之前新增的记录删除
                    epartnerService.deleteByPrimaryKey(no);
                    ecooperationService.deleteByPrimaryKey2(no);
                    eccontactsService.deleteByPrimaryKey3(no);
                    return new ResultResponse(505, "上传文件格式不符合需求");
                }
            }
        }
        if (arr[0] == 1 && arr[1] == 1 && arr[2] == 1) {
            resultResponse = new ResultResponse(0, "提示信息：新增成功！");
            return resultResponse;
        }
        resultResponse = new ResultResponse(501, "提示信息：新增失败！");
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
        int identity =  userInfoService.selectIdentityByUserCode(userCode);
        epartner.setCreaterIdentity(identity);
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
     * 集成文件上传和删除(当文件数量等于或多于当前文件数量时调用)
     */
    @RequestMapping(value = "/updateCompany", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResultResponse modifyCom(MultipartFile[] file, HttpServletRequest req)throws IOException{
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
        System.out.println("修改者："+userName);
        System.out.println("获取的工号："+userCode);
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

            //当文件等于或多于原文件数量时
            if(file!=null){
                for (MultipartFile multipartFile : file) {
                    String name = multipartFile.getOriginalFilename();
                    System.out.println("原始文件名：" + name);
                    //如果文件被删除则查询出被删除的文件名修改其状态
                    //根据当前文件名查询文件编号
                    //根据公司合作伙伴编号和文件名查询附件是否未修改
                    /*int number = fileInfoService.judgeIfFileChanged(cid, name);
                    if (number > 0) {
                        //说明文件未修改 默认跳过修改返回修改成功
                        arr[3] = 1;
                    }else{*/
                        //说明有多的文件需要上传
                        //重复文件名判断
                        int count = fileInfoService.selectFileCountByFileName(multipartFile.getOriginalFilename(),cid);
                        //如果文件为删除状态则还可以上传
                    List<Integer> count2=  fileInfoService.selectFileStatusByFileName(multipartFile.getOriginalFilename(),cid);
                        for (int i=0;i<=count2.size()-1;i++){
                            if (count > 0 && count2.get(i)==0) {
                                return new ResultResponse(501, "文件已存在，请选择其他文件上传!");
                            }
                        }
                        String Suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
                        System.out.println("文件后缀：" + Suffix);
                        //根据原始文件名的后缀进行文件类型判断
                        if (Suffix.equals(".xls") || Suffix.equals(".xlsx") || Suffix.equals(".xlsm") || Suffix.equals(".doc")
                                || Suffix.equals(".docx") || Suffix.equals(".pdf") || Suffix.equals(".ppt") || Suffix.equals(".pptx")
                                || Suffix.equals(".txt")) {
                            String realPath = req.getServletContext().getRealPath("/ecoUpload"+"/"+epartner.getPartnerName());//此方法用于获取上传路径
                            //本地路径测试文件上传
                            String Path = "D:/66/testUpload/ecoUpload/"+epartner.getPartnerName();
                            System.out.println("本地实际路径：" + Path);
                            //服务器路径测试文件上传
                            String Path2 = "/tmp/ecoUpload/"+epartner.getPartnerName();
                            System.out.println("服务器实际路径：" + Path2);
                            File folder = new File(Path2);
                            if (!folder.exists()) {
                                folder.mkdirs();
                            }//无报错则上传成功
                            //获取上传者
                            multipartFile.transferTo(new File(folder, multipartFile.getOriginalFilename()));
                            String url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/ecoUpload" + "/" + multipartFile.getOriginalFilename();
                            System.out.println(url);//真实存储的url
                           /* String newUrl = req.getServletContext().getRealPath("/ecoUpload")+"/"+epartner.getPartnerName() + "/" + multipartFile.getOriginalFilename();
                            System.out.println("真实URL：" + newUrl);*/
                            //本地路径测试文件上传
                            String URL = "D:/66/testUpload/ecoUpload/"+epartner.getPartnerName()+"/" + multipartFile.getOriginalFilename();
                            System.out.println("本地存储URL:"+URL);
                            //服务器路径测试文件上传
                            String URL2 = "/tmp/ecoUpload/"+epartner.getPartnerName()+"/" + multipartFile.getOriginalFilename();
                            System.out.println("服务器存储URL:"+URL2);
                            //进行文件上传记录的存储
                            FileInfo fileInfo = new FileInfo();
                            fileInfo.setFileName(multipartFile.getOriginalFilename());
                            //fileInfo.setFilePath(URL);
                            //服务器存储   打包上去前需要置换
                            fileInfo.setFilePath(URL2);
                            fileInfo.setRelDocId(cid);
                            fileInfo.setUpdatedTime(new Date());
                            fileInfo.setLastUpdateUser(userCode);
                            fileInfo.setKeyword(multipartFile.getOriginalFilename());
                            fileInfoService.insertSelective(fileInfo);
                            //查询出当前成功文件的编号
                            int count3 = epartnerService.selectID();
                            System.out.println("刚刚增加成功的记录的编号为：" + count3);
                        } else {
                            return new ResultResponse(506, "上传文件格式不符合需求");
                        }
                    //}
                }
            }

            if (arr[0]==1 && arr[1]==1 && arr[2]==1){
                resultResponse = new ResultResponse(0,"提示信息：修改成功！");
                return resultResponse;
            }
        }
        resultResponse = new ResultResponse(507,"提示信息：未查到对应公司合作伙伴编号！");
        return resultResponse;
    }

    /**
     * 合作伙伴信息、合作情况信息、公司联系人信息修改
     * 集成删除文件(当文件数量少于当前文件数量时调用)
     * 接收的参数是文件名
     */
    @RequestMapping(value = "/updateCompany2", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResultResponse modifyCom2(@RequestBody CompanyPackage filePackage, HttpServletRequest req){
        ResultResponse resultResponse = null;
        //通过接受三个对象来进行修改 包含公司的partnerNo
        Object[] fileName = filePackage.getFileName();
        Epartner epartner = filePackage.getEpartner();
        if (epartner==null){
            resultResponse = new ResultResponse(502,"提示信息：公司合作关系信息为空异常！");
            return resultResponse;
        }
        Eccontacts eccontacts = filePackage.getEccontacts();
        if (eccontacts==null){
            resultResponse = new ResultResponse(503,"提示信息：公司联系人信息为空异常！");
            return resultResponse;
        }
        Ecooperation ecooperation = filePackage.getEcooperation();
        if (ecooperation==null){
            resultResponse = new ResultResponse(502,"提示信息：公司合作关系信息为空异常！");
            return resultResponse;
        }
        UserInfo userInfo = filePackage.getUserInfo();
        Integer cid = epartner.getPartnerNo();
        System.out.println("接收到的partnerNo:"+cid);
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

            //当文件少于原文件数量时
            if(fileName!=null){
                //对应的文件编号
                List<Integer> list2 = fileInfoService.selectFileNoByNo(cid);
                for (Object file : fileName) {
                    //如果文件被删除则查询出被删除的文件名修改其状态
                    //根据当前文件名查询文件编号
                    List<Integer> num2 = fileInfoService.selectFileIdByFileName(file.toString());
                    for (int i=0;i<=num2.size()-1;i++){
                        if (list2.contains(num2.get(i))){
                            System.out.println("该文件被包含，文件名为："+file.toString());
                            list2.remove(num2.get(i));
                        }
                    }
                }
                System.out.println("LIST2:"+list2.toString());
                //根据剩余的编号修改状态
                for (int i=0;i<=list2.size()-1;i++){
                    //看看是否剩下的编号都被修改了
                   fileInfoService.updateFileStatusByFileNo(list2.get(i));
                }
            }else{
                //此处进行文件的删除:如果没有收到文件证明文件已被删除
                epartnerService.deleteFileByParNo(cid);
            }
            if (arr[0]==1 && arr[1]==1 && arr[2]==1){
                resultResponse = new ResultResponse(0,"提示信息：修改成功！");
                return resultResponse;
            }
        }
        resultResponse = new ResultResponse(507,"提示信息：未查到对应公司合作伙伴编号！");
        return resultResponse;
    }

    /**
     * 管理员删除公司（软删除）
     */
    @RequestMapping(value = "/del", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResultResponse delCom(@RequestBody HidePackage hidePackage){
        //通过接受partnerNo进行删除（隐藏） 需要对其合作伙伴以及联系人同时删除（隐藏）
        //需要同时修改三张表的状态
        ResultResponse resultResponse = null;
        //获取公司对应的合作伙伴编号partnerNo
        Object[] data = hidePackage.getData();
        for (int i =0;i<=data.length-1;i++){
            System.out.println("公司数组："+data[i]);
        }
        int[] data2 = new int[data.length];
        for (int i =0;i<=data.length-1;i++){
            data2[i]=(int)data[i];
        }
        int num = 0;
        int count = 0;
        int count2 = 0;
        for (int i=0;i<=data2.length-1;i++){
            num =  epartnerService.updateStatusByCid(data2[i]);
        }
            if (num>0){
                //如果成功修改了公司状态则同步修改联系人和合作关系
                for (int i=0;i<=data2.length-1;i++) {
                    count = ecooperationService.updateStatusByCid(data2[i]);
                    count2 = eccontactsService.updateStatusByCid(data2[i]);
                }
                if (count>0 && count2>0){
                    resultResponse = new ResultResponse(0,"提示信息：删除成功！");
                    return resultResponse;
                }
            }
            resultResponse = new ResultResponse(501,"提示信息：删除失败！");
            return resultResponse;
    }
}
