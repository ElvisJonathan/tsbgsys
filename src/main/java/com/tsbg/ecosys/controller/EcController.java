package com.tsbg.ecosys.controller;

import com.tsbg.ecosys.common.NeedLogin;
import com.tsbg.ecosys.config.ResultResponse;
import com.tsbg.ecosys.dto.EcTotalDto;
import com.tsbg.ecosys.dto.EcTotalDtol;
import com.tsbg.ecosys.dto.EcTotal_Excel;
import com.tsbg.ecosys.model.Eccontacts;
import com.tsbg.ecosys.model.Ecooperation;
import com.tsbg.ecosys.model.Epartner;
import com.tsbg.ecosys.service.EpartnerService;
import com.tsbg.ecosys.serviceImpl.EcooperationService;
import com.tsbg.ecosys.util.ExcelTimeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 合作伙伴
 */
@RestController
@RequestMapping("/tsbg/ec")
@Api(value = "EcController", tags = "EcController")
public class EcController {
    @Autowired
    private EcooperationService ecooperationService;
    @Autowired
    private EpartnerService epartnerService;

    @ApiOperation(value = "查询合作伙伴信息", notes = "查询合作伙伴信息")
    @RequestMapping(value = "/total", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResultResponse getEcooperationList(@RequestBody Epartner epartner) {
        //获取公司合作伙伴编号
        Integer cid = epartner.getPartnerNo();
        //根据合作伙伴编号同步查询合作情况与联系人
        if (cid != null) {
            List<EcTotalDto> ecTotalDtoList = ecooperationService.getEcooperationList(cid);
            return new ResultResponse(0, "根据PartnerNo查询", ecTotalDtoList);
        }
        return new ResultResponse(500, "未收到PartnerNo");
    }

    /**
     * 查询公司文件
     */
    @RequestMapping(value = "/filedetail", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResultResponse getFileDetail(@RequestBody Epartner epartner) {
        Integer partnerNo = epartner.getPartnerNo();
        if (partnerNo != null) {
            //查询当前公司的文件详情
            List<String> filelist = epartnerService.selectFileByParNo(partnerNo);
            List<String> filelist2 = epartnerService.selectFileByParNo2(partnerNo);
            if (filelist != null) {
                return new ResultResponse(0, "查询公司文件(公司隐藏情况下文件也隐藏)", filelist, filelist2);
            }
            return new ResultResponse(500, "未查到相关文件");
        }
        return new ResultResponse(501, "未收到PartnerNo");
    }

    //根据查询条件导出Excel(条件Epartner表查&partnerName=aaa&&partnerProduct=aaa&partnerRegion=aaa&partnerIndustry=aaa)and status=0 and del_status=0才能导出
    @RequestMapping(value = "/totalo", method = { RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    @NeedLogin
    public void getEcooperationAll(HttpServletRequest req, HttpServletResponse response) throws IOException {
      /* //从前端获取添加的四个条件进行查询*/
        String json = req.getParameter("partnerName");
        String json2 = req.getParameter("partnerProduct");
        String json3 = req.getParameter("partnerRegion");
        String json4 = req.getParameter("partnerIndustry");
        Epartner epartner = new Epartner();
        epartner.setPartnerName(json);
        epartner.setPartnerProduct(json2);
        epartner.setPartnerRegion(json3);
        epartner.setPartnerIndustry(json4);
        //设置服务器的编码
        response.setCharacterEncoding("utf-8");
        List<EcTotalDtol> ecTotalDtoList = ecooperationService.getEcooperationListall(epartner);
        export(response, ecTotalDtoList);
    }


    //全部导出Excel（只导出status=0 and del_status=0）
    @NeedLogin
    @RequestMapping(value = "/exportall", method = {RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public void exportAll(HttpServletResponse response) throws IOException {
        EcTotal_Excel epartnerList = ecooperationService.getEpartnerList();
        exportexcel(response,epartnerList);
    }
    //全部导出Excel
    public void exportexcel(HttpServletResponse response, EcTotal_Excel ecTotal_excel) throws IOException {
        List<Eccontacts> eccontactss = new ArrayList<>();
        List<Epartner> epartners = new ArrayList<>();
        List<Ecooperation> ecooperations = new ArrayList<>();
        eccontactss = ecTotal_excel.getEccontactsList();
        epartners = ecTotal_excel.getEpartnerList();
        ecooperations = ecTotal_excel.getEcooperationList();

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("获取excel测试表格");

        HSSFRow row = null;

        row = sheet.createRow(0);//创建第一个单元格
        row.setHeight((short) (40 * 20));
        row.createCell(0).setCellValue("公司聯繫人、合作情況、合作夥伴信息表");//为第一行单元格设值
		/*为标题设计空间
		* firstRow从第1行开始
		* lastRow从第0行结束
		*从第1个单元格开始
		* 从第3个单元格结束
        CellRangeAddress rowRegion = new CellRangeAddress(0, 0, 0, 2);
        sheet.addMergedRegion(rowRegion);
		* 动态获取数据库列 sql语句 select COLUMN_NAME from INFORMATION_SCHEMA.Columns where table_name='user' and table_schema='test'
		* 第一个table_name 表名字
		* 第二个table_name 数据库名称
		* */
        //eccontacts表头
        row = sheet.createRow(1);
        row.setHeight((short) (22.50 * 20));//设置行高
        row.createCell(0).setCellValue("公司聯繫人編號");//为第一个单元格设值
        row.createCell(1).setCellValue("合作夥伴編號");//为第二个单元格设值
        row.createCell(2).setCellValue("所屬公司名稱");//为第三个单元格设值
        row.createCell(3).setCellValue("聯繫人名字");//为第四个单元格设值
        row.createCell(4).setCellValue("職稱");//为第五个单元格设值
        row.createCell(5).setCellValue("性別:1男/0女");//为第六个单元格设值
        row.createCell(6).setCellValue("電話號碼");//为第七个单元格设值
        row.createCell(7).setCellValue("郵箱地址");//为第八个单元格设值
        row.createCell(8).setCellValue("創建時間");//为第九个单元格设值
        row.createCell(9).setCellValue("修改時間");//为第十个单元格设值
        row.createCell(10).setCellValue("創建人");//为第十一个单元格设值
        row.createCell(11).setCellValue("修改人");//为第十二个单元格设值
       /* row.createCell(12).setCellValue("隐藏状态：0未隐藏/1隐藏");//为第十三个单元格设值
        row.createCell(13).setCellValue("删除状态：0未删除/1删除");//为第十四个单元格设值*/
        row.createCell(12).setCellValue("備註");//为第十五个单元格设值

        //epartner表头
        row.createCell(13).setCellValue("合作夥伴編號");//为第一个单元格设值
        row.createCell(14).setCellValue("合作夥伴公司名稱");//为第二个单元格设值
        row.createCell(15).setCellValue("公司註冊時間");//为第三个单元格设值
        row.createCell(16).setCellValue("公司註冊資本");//为第四个单元格设值
        row.createCell(17).setCellValue("是否為上市公司：1是/0否");//为第五个单元格设值
        row.createCell(18).setCellValue("公司規模/分佈");//为第六个单元格设值
        row.createCell(19).setCellValue("公司地址");//为第七个单元格设值
        row.createCell(20).setCellValue("公司網址");//为第八个单元格设值
        row.createCell(21).setCellValue("公司核心技術");//为第九个单元格设值
        row.createCell(22).setCellValue("主營產品/業務/服務");//为第十个单元格设值
        row.createCell(23).setCellValue("業務渠道");//为第十一个单元格设值
        row.createCell(24).setCellValue("營業額");//为第十二个单元格设值
        row.createCell(25).setCellValue("業務主要區域");//为第十三个单元格设值
        row.createCell(26).setCellValue("行業");//为第十四个单元格设值
        /*row.createCell(29).setCellValue("隐藏状态：0未隐藏/1隐藏");//为第十五个单元格设值
        row.createCell(30).setCellValue("删除状态：0未删除/1删除");//为第十五个单元格设值*/
        row.createCell(27).setCellValue("創建時間");//为第十五个单元格设值
        row.createCell(28).setCellValue("修改時間");//为第十五个单元格设值
        row.createCell(29).setCellValue("創建人");//为第十五个单元格设值
        row.createCell(30).setCellValue("修改人");//为第十五个单元格设值
        row.createCell(31).setCellValue("備註");//为第十五个单元格设值
        row.createCell(32).setCellValue("項目ID");//为第十五个单元格设值

        //ecooperation表头
        row.createCell(33).setCellValue("合作情況編號");//为第一个单元格设值
        row.createCell(34).setCellValue("合作夥伴編號");//为第二个单元格设值
        row.createCell(35).setCellValue("合作夥伴公司名稱");//为第三个单元格设值
        row.createCell(36).setCellValue("Callin 時間");//为第四个单元格设值
        row.createCell(37).setCellValue("Callin BD Owner");//为第五个单元格设值
        row.createCell(38).setCellValue("合作階段");//为第六个单元格设值
        row.createCell(39).setCellValue("是否簽署合約：1是/0否");//为第七个单元格设值
        row.createCell(40).setCellValue("合約起始日");//为第八个单元格设值
        row.createCell(41).setCellValue("是否有委託簽署關係:1是/0否");//为第九个单元格设值
        row.createCell(42).setCellValue("合約委託方");//为第十个单元格设值
        row.createCell(43).setCellValue("是否有授牌:1是/0否");//为第十一个单元格设值
        row.createCell(44).setCellValue("合作項目名稱");//为第十二个单元格设值
        row.createCell(45).setCellValue("合作業務類型");//为第十三个单元格设值
        row.createCell(46).setCellValue("合作項目進度");//为第十四个单元格设值
        row.createCell(47).setCellValue("Fii合作部門");//为第十五个单元格设值
        row.createCell(48).setCellValue("創建時間");//为第十五个单元格设值
        row.createCell(49).setCellValue("修改時間");//为第十五个单元格设值
        row.createCell(50).setCellValue("創建人");//为第十五个单元格设值
        row.createCell(51).setCellValue("修改人");//为第十五个单元格设值
     /*   row.createCell(56).setCellValue("隐藏状态：0未隐藏/1隐藏");//为第十五个单元格设值
        row.createCell(57).setCellValue("删除状态：0未删除/1删除");//为第十五个单元格设值*/
        row.createCell(52).setCellValue("備註");//为第十五个单元格设值

        int totalRow = 2;
        for (int i = 0; eccontactss.size()>0 && i < eccontactss.size(); i++) {
            row = sheet.createRow(i + 2);
            Eccontacts eccontacts = eccontactss.get(i);
            if(eccontacts != null){
                row.createCell(0).setCellValue(eccontacts.getContactId() == null ? "":eccontacts.getContactId().toString());
                row.createCell(1).setCellValue(eccontacts.getPartnerNo()  == null ? "":eccontacts.getPartnerNo().toString());
                row.createCell(2).setCellValue(eccontacts.getPartnerName()  == null ? "":eccontacts.getPartnerName().toString());
                row.createCell(3).setCellValue(eccontacts.getName() == null ? "":eccontacts.getName().toString());
                row.createCell(4).setCellValue(eccontacts.getTitle()== null ? "":eccontacts.getTitle().toString());
                row.createCell(5).setCellValue(eccontacts.getGender()== null ? "":eccontacts.getGender().toString());
                row.createCell(6).setCellValue(eccontacts.getPhoneNumber() == null ? "":eccontacts.getPhoneNumber().toString());
                row.createCell(7).setCellValue(eccontacts.getEmailAddress() == null ? "":eccontacts.getEmailAddress().toString());
                row.createCell(8).setCellValue(eccontacts.getCreateTime() == null ? "": ExcelTimeUtils.getTimehhString(eccontacts.getCreateTime()));
                row.createCell(9).setCellValue(eccontacts.getUpdateTime()== null ? "": ExcelTimeUtils.getTimehhString(eccontacts.getUpdateTime()));
                row.createCell(10).setCellValue(eccontacts.getCreater()== null ? "":eccontacts.getCreater().toString());
                row.createCell(11).setCellValue(eccontacts.getUpdater()== null ? "":eccontacts.getUpdater().toString());
              /*  row.createCell(12).setCellValue(eccontacts.getStatus()== null ? "":eccontacts.getStatus().toString());
                row.createCell(13).setCellValue(eccontacts.getDelStatus()== null ? "":eccontacts.getDelStatus().toString());
                ExcelTimeUtils.getTimeString(
                */
                row.createCell(12).setCellValue(eccontacts.getRemark()== null ? "":eccontacts.getRemark().toString());
                totalRow++;
            }
        }

        for(int i = 0;epartners.size()>0 && i<epartners.size();i++){
            if(i< totalRow-2){
                row = sheet.getRow(i+2);

            }else {
                row = sheet.createRow(totalRow++);
            }
            Epartner epartner = epartners.get(i);
            if (epartner != null) {
                row.createCell(13).setCellValue(epartner.getPartnerNo() == null ? "" : epartner.getPartnerNo().toString());
                row.createCell(14).setCellValue(epartner.getPartnerName() == null ? "" : epartner.getPartnerName().toString());
                //row.createCell(15).setCellValue(epartner.getPartnerDate() == null ? "" : ExcelTimeUtils.getTimehhString(epartner.getPartnerDate()));
                row.createCell(15).setCellValue(epartner.getPartnerDate() == null ? "" : epartner.getPartnerDate());
                row.createCell(16).setCellValue(epartner.getPartnerRegistcapital() == null ? "" : epartner.getPartnerRegistcapital().toString());
                row.createCell(17).setCellValue(epartner.getPartnerList() == null ? "" : epartner.getPartnerList().toString());
                row.createCell(18).setCellValue(epartner.getPartnerScale() == null ? "" : epartner.getPartnerScale().toString());
                row.createCell(19).setCellValue(epartner.getPartnerAddr() == null ? "" : epartner.getPartnerAddr().toString());
                row.createCell(20).setCellValue(epartner.getPartnerWebddr() == null ? "" : epartner.getPartnerWebddr().toString());
                row.createCell(21).setCellValue(epartner.getPartnerTech() == null ? "" : epartner.getPartnerTech());
                row.createCell(22).setCellValue(epartner.getPartnerProduct() == null ? "" : epartner.getPartnerProduct());
                row.createCell(23).setCellValue(epartner.getPartnerChannel() == null ? "" : epartner.getPartnerChannel().toString());
                row.createCell(24).setCellValue(epartner.getPartnerTurnover() == null ? "" : epartner.getPartnerTurnover().toString());
                row.createCell(25).setCellValue(epartner.getPartnerRegion() == null ? "" : epartner.getPartnerRegion().toString());
                row.createCell(26).setCellValue(epartner.getPartnerIndustry() == null ? "" : epartner.getPartnerIndustry().toString());
              /*  row.createCell(29).setCellValue(epartner.getStatus() == null ? "" : epartner.getStatus().toString());
                row.createCell(30).setCellValue(epartner.getDelStatus() == null ? "" : epartner.getDelStatus().toString());*/
                row.createCell(27).setCellValue(epartner.getCreateTime() == null ? "" : ExcelTimeUtils.getTimehhString(epartner.getCreateTime()));
                row.createCell(28).setCellValue(epartner.getUpdateTime() == null ? "" : ExcelTimeUtils.getTimehhString(epartner.getUpdateTime()));
                row.createCell(29).setCellValue(epartner.getCreater() == null ? "" : epartner.getCreater().toString());
                row.createCell(30).setCellValue(epartner.getUpdater() == null ? "" : epartner.getUpdater().toString());
                row.createCell(31).setCellValue(epartner.getRemark() == null ? "" : epartner.getRemark().toString());
                row.createCell(32).setCellValue(epartner.getProjId() == null ? "" : epartner.getProjId().toString());

            }

        }
        for(int i = 0;ecooperations.size()>0 && i<ecooperations.size();i++){
            if(i < (totalRow-2) ){
                row = sheet.getRow(i+2);
            }else {
                row = sheet.createRow(totalRow++);
            }
            Ecooperation ecooperation = ecooperations.get(i);
            if(ecooperation != null) {
                row.createCell(33).setCellValue(ecooperation.getCoopId() == null ? "" : ecooperation.getCoopId().toString());
                row.createCell(34).setCellValue(ecooperation.getPartnerNo() == null ? "" : ecooperation.getPartnerNo().toString());
                row.createCell(35).setCellValue(ecooperation.getPartnerName() == null ? "" : ecooperation.getPartnerName().toString());
               // row.createCell(36).setCellValue(ecooperation.getPartnerCallintime() == null ? "" : ExcelTimeUtils.getTimehhString(ecooperation.getPartnerCallintime()));
                row.createCell(36).setCellValue(ecooperation.getPartnerCallintime() == null ? "" :ecooperation.getPartnerCallintime());
                row.createCell(37).setCellValue(ecooperation.getPartnerBdOwner() == null ? "" : ecooperation.getPartnerBdOwner().toString());
                row.createCell(38).setCellValue(ecooperation.getPartnerCostage() == null ? "" : ecooperation.getPartnerCostage().toString());
                row.createCell(39).setCellValue(ecooperation.getSignContract() == null ? "" : ecooperation.getSignContract().toString());
                row.createCell(40).setCellValue(ecooperation.getContractDate() == null ? "" : ecooperation.getContractDate().toString());
                row.createCell(41).setCellValue(ecooperation.getEntrust() == null ? "" : ecooperation.getEntrust().toString());
                row.createCell(42).setCellValue(ecooperation.getEntrustName() == null ? "" : ecooperation.getEntrustName().toString());
                row.createCell(43).setCellValue(ecooperation.getPartnerAwarding() == null ? "" : ecooperation.getPartnerAwarding().toString());
                row.createCell(44).setCellValue(ecooperation.getProjectName() == null ? "" : ecooperation.getProjectName().toString());
                row.createCell(45).setCellValue(ecooperation.getCoType() == null ? "" : ecooperation.getCoType().toString());
                row.createCell(46).setCellValue(ecooperation.getCoProgress() == null ? "" : ecooperation.getCoProgress().toString());
                row.createCell(47).setCellValue(ecooperation.getFiiCodepartment() == null ? "" : ecooperation.getFiiCodepartment().toString());
                row.createCell(48).setCellValue(ecooperation.getCreateTime() == null ? "" : ExcelTimeUtils.getTimehhString(ecooperation.getCreateTime()));
                row.createCell(49).setCellValue(ecooperation.getUpdateTime() == null ? "" :ExcelTimeUtils.getTimehhString(ecooperation.getUpdateTime()));
                row.createCell(50).setCellValue(ecooperation.getCreater() == null ? "" : ecooperation.getCreater().toString());
                row.createCell(51).setCellValue(ecooperation.getUpdater() == null ? "" : ecooperation.getUpdater().toString());
              /*  row.createCell(56).setCellValue(ecooperation.getStatus() == null ? "" : ecooperation.getStatus().toString());
                row.createCell(57).setCellValue(ecooperation.getDelStatus() == null ? "" : ecooperation.getDelStatus().toString());*/
                row.createCell(52).setCellValue(ecooperation.getRemark() == null ? "" : ecooperation.getRemark().toString());
            }
        }
        sheet.setDefaultRowHeight((short) (30 * 30));
        //列宽自适应
        for (int i = 0; i <= 52; i++) {
            sheet.autoSizeColumn(i);
        }
        response.setHeader("Content-Type", "application/vnd.ms-excel;charset=utf-8");
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        OutputStream os = response.getOutputStream();
        String fileName = "Excel.xls";
        response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "utf-8"));
        wb.write(os);
        os.flush();
        os.close();
    }

    //根据查询条件导出Excel
    public void export(HttpServletResponse response, List<EcTotalDtol> ecTotalDtoList ) throws IOException {
        List<Eccontacts> eccontactss = new ArrayList<>();
        List<Epartner> epartners = new ArrayList<>();
        List<Ecooperation> ecooperations = new ArrayList<>();
        ecTotalDtoList.forEach(item->{
            eccontactss.add(item.getEccontacts());
            epartners.add(item.getEpartner());
            ecooperations.add(item.getEcooperation());
        });
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("获取excel测试表格");

        HSSFRow row = null;

        row = sheet.createRow(0);//创建第一个单元格
        row.setHeight((short) (26.25 * 20));
        row.createCell(0).setCellValue("公司聯繫人、合作情況、合作夥伴信息表");//为第一行单元格设值

		/*为标题设计空间
		* firstRow从第1行开始
		* lastRow从第0行结束
		*
		*从第1个单元格开始
		* 从第3个单元格结束
		*/
        CellRangeAddress rowRegion = new CellRangeAddress(0, 0, 0, 2);
        sheet.addMergedRegion(rowRegion);

		/*
		* 动态获取数据库列 sql语句 select COLUMN_NAME from INFORMATION_SCHEMA.Columns where table_name='user' and table_schema='test'
		* 第一个table_name 表名字
		* 第二个table_name 数据库名称
		* */
        //eccontacts表头
        row = sheet.createRow(1);
        row.setHeight((short) (22.50 * 20));//设置行高
        row.createCell(0).setCellValue("公司聯繫人編號");//为第一个单元格设值
        row.createCell(1).setCellValue("合作夥伴編號");//为第二个单元格设值
        row.createCell(2).setCellValue("所屬公司名稱");//为第三个单元格设值
        row.createCell(3).setCellValue("聯繫人名字");//为第四个单元格设值
        row.createCell(4).setCellValue("職稱");//为第五个单元格设值
        row.createCell(5).setCellValue("性別:1男/0女");//为第六个单元格设值
        row.createCell(6).setCellValue("電話號碼");//为第七个单元格设值
        row.createCell(7).setCellValue("郵箱地址");//为第八个单元格设值
        row.createCell(8).setCellValue("創建時間");//为第九个单元格设值
        row.createCell(9).setCellValue("修改時間");//为第十个单元格设值
        row.createCell(10).setCellValue("創建人");//为第十一个单元格设值
        row.createCell(11).setCellValue("修改人");//为第十二个单元格设值
        /*row.createCell(12).setCellValue("隱藏狀態：0未隱藏/1隱藏");//为第十三个单元格设值
        row.createCell(13).setCellValue("隱藏狀態：0未隱藏/1隱藏");//为第十四个单元格设值*/
        row.createCell(12).setCellValue("備註");//为第十五个单元格设值

        //epartner表头
        row.createCell(13).setCellValue("合作夥伴編號");//为第一个单元格设值
        row.createCell(14).setCellValue("合作夥伴公司名稱");//为第二个单元格设值
        row.createCell(15).setCellValue("公司註冊時間");//为第三个单元格设值
        row.createCell(16).setCellValue("公司註冊資本");//为第四个单元格设值
        row.createCell(17).setCellValue("是否為上市公司：1是/0否");//为第五个单元格设值
        row.createCell(18).setCellValue("公司規模/分佈");//为第六个单元格设值
        row.createCell(19).setCellValue("公司地址");//为第七个单元格设值
        row.createCell(20).setCellValue("公司網址");//为第八个单元格设值
        row.createCell(21).setCellValue("公司核心技術");//为第九个单元格设值
        row.createCell(22).setCellValue("主營產品/業務/服務");//为第十个单元格设值
        row.createCell(23).setCellValue("業務渠道");//为第十一个单元格设值
        row.createCell(24).setCellValue("營業額");//为第十二个单元格设值
        row.createCell(25).setCellValue("業務主要區域");//为第十三个单元格设值
        row.createCell(26).setCellValue("行業");//为第十四个单元格设值
       /* row.createCell(29).setCellValue("隱藏狀態：0未隱藏/1隱藏");//为第十五个单元格设值
        row.createCell(30).setCellValue("删除状态：0未删除/1删除");//为第十五个单元格设值*/
        row.createCell(27).setCellValue("創建時間");//为第十五个单元格设值
        row.createCell(28).setCellValue("修改時間");//为第十五个单元格设值
        row.createCell(29).setCellValue("創建人");//为第十五个单元格设值
        row.createCell(30).setCellValue("修改人");//为第十五个单元格设值
        row.createCell(31).setCellValue("備註");//为第十五个单元格设值
        row.createCell(32).setCellValue("項目ID");//为第十五个单元格设值

        //ecooperation表头
        row.createCell(33).setCellValue("合作情况編號");//为第一个单元格设值
        row.createCell(34).setCellValue("合作夥伴編號");//为第二个单元格设值
        row.createCell(35).setCellValue("合作夥伴公司名稱");//为第三个单元格设值
        row.createCell(36).setCellValue("Callin 時間");//为第四个单元格设值
        row.createCell(37).setCellValue("Callin BD Owner");//为第五个单元格设值
        row.createCell(38).setCellValue("合作階段");//为第六个单元格设值
        row.createCell(39).setCellValue("是否簽署合約：1是/0否");//为第七个单元格设值
        row.createCell(40).setCellValue("合約起始日");//为第八个单元格设值
        row.createCell(41).setCellValue("是否有委託簽署關係:1是/0否");//为第九个单元格设值
        row.createCell(42).setCellValue("合約委託方");//为第十个单元格设值
        row.createCell(43).setCellValue("是否有授牌:1是/0否");//为第十一个单元格设值
        row.createCell(44).setCellValue("合作項目名稱");//为第十二个单元格设值
        row.createCell(45).setCellValue("合作業務類型");//为第十三个单元格设值
        row.createCell(46).setCellValue("合作項目進度");//为第十四个单元格设值
        row.createCell(47).setCellValue("Fii合作部門");//为第十五个单元格设值
        row.createCell(48).setCellValue("創建時間");//为第十五个单元格设值
        row.createCell(49).setCellValue("修改時間");//为第十五个单元格设值
        row.createCell(50).setCellValue("創建人");//为第十五个单元格设值
        row.createCell(51).setCellValue("修改人");//为第十五个单元格设值
       /* row.createCell(56).setCellValue("隐藏状态：0未隐藏/1隐藏");//为第十五个单元格设值
        row.createCell(57).setCellValue("删除状态：0未删除/1删除");//为第十五个单元格设值*/
        row.createCell(52).setCellValue("備註");//为第十五个单元格设值

        int totalRow = 2;
        for (int i = 0; eccontactss.size()>0 && i < eccontactss.size(); i++) {
            row = sheet.createRow(i + totalRow);
            Eccontacts eccontacts = eccontactss.get(i);
            if(eccontacts != null){
                row.createCell(0).setCellValue(eccontacts.getContactId() == null ? "":eccontacts.getContactId().toString());
                row.createCell(1).setCellValue(eccontacts.getPartnerNo()  == null ? "":eccontacts.getPartnerNo().toString());
                row.createCell(2).setCellValue(eccontacts.getPartnerName()  == null ? "":eccontacts.getPartnerName().toString());
                row.createCell(3).setCellValue(eccontacts.getName() == null ? "":eccontacts.getName().toString());
                row.createCell(4).setCellValue(eccontacts.getTitle()== null ? "":eccontacts.getTitle().toString());
                row.createCell(5).setCellValue(eccontacts.getGender()== null ? "":eccontacts.getGender().toString());
                row.createCell(6).setCellValue(eccontacts.getPhoneNumber() == null ? "":eccontacts.getPhoneNumber().toString());
                row.createCell(7).setCellValue(eccontacts.getEmailAddress() == null ? "":eccontacts.getEmailAddress().toString());
                row.createCell(8).setCellValue(eccontacts.getCreateTime() == null ? "": ExcelTimeUtils.getTimehhString(eccontacts.getCreateTime()));
                row.createCell(9).setCellValue(eccontacts.getUpdateTime()== null ? "": ExcelTimeUtils.getTimehhString(eccontacts.getUpdateTime()));
                row.createCell(10).setCellValue(eccontacts.getCreater()== null ? "":eccontacts.getCreater().toString());
                row.createCell(11).setCellValue(eccontacts.getUpdater()== null ? "":eccontacts.getUpdater().toString());
               /* row.createCell(12).setCellValue(eccontacts.getStatus()== null ? "":eccontacts.getStatus().toString());
                row.createCell(13).setCellValue(eccontacts.getDelStatus()== null ? "":eccontacts.getDelStatus().toString());*/
                row.createCell(12).setCellValue(eccontacts.getRemark()== null ? "":eccontacts.getRemark().toString());
                totalRow++;
            }
        }

        for(int i = 0;epartners.size()>0 && i<epartners.size();i++){
            if(i<=totalRow){
                row = sheet.getRow(i+2);
            }else {
                row = sheet.createRow(i + totalRow);
            }
            Epartner epartner = epartners.get(i);
            if (epartner != null) {
                row.createCell(13).setCellValue(epartner.getPartnerNo() == null ? "" : epartner.getPartnerNo().toString());
                row.createCell(14).setCellValue(epartner.getPartnerName() == null ? "" : epartner.getPartnerName().toString());
                //row.createCell(15).setCellValue(epartner.getPartnerDate() == null ? "" : ExcelTimeUtils.getTimehhString(epartner.getPartnerDate()));
                row.createCell(15).setCellValue(epartner.getPartnerDate() == null ? "" : epartner.getPartnerDate());
                row.createCell(16).setCellValue(epartner.getPartnerRegistcapital() == null ? "" : epartner.getPartnerRegistcapital().toString());
                row.createCell(17).setCellValue(epartner.getPartnerList() == null ? "" : epartner.getPartnerList().toString());
                row.createCell(18).setCellValue(epartner.getPartnerScale() == null ? "" : epartner.getPartnerScale().toString());
                row.createCell(19).setCellValue(epartner.getPartnerAddr() == null ? "" : epartner.getPartnerAddr().toString());
                row.createCell(20).setCellValue(epartner.getPartnerWebddr() == null ? "" : epartner.getPartnerWebddr().toString());
                row.createCell(21).setCellValue(epartner.getPartnerTech() == null ? "" : epartner.getPartnerTech());
                row.createCell(22).setCellValue(epartner.getPartnerProduct() == null ? "" : epartner.getPartnerProduct());
                row.createCell(23).setCellValue(epartner.getPartnerChannel() == null ? "" : epartner.getPartnerChannel().toString());
                row.createCell(24).setCellValue(epartner.getPartnerTurnover() == null ? "" : epartner.getPartnerTurnover().toString());
                row.createCell(25).setCellValue(epartner.getPartnerRegion() == null ? "" : epartner.getPartnerRegion().toString());
                row.createCell(26).setCellValue(epartner.getPartnerIndustry() == null ? "" : epartner.getPartnerIndustry().toString());
               /* row.createCell(29).setCellValue(epartner.getStatus() == null ? "" : epartner.getStatus().toString());
                row.createCell(30).setCellValue(epartner.getDelStatus() == null ? "" : epartner.getDelStatus().toString());*/
                row.createCell(27).setCellValue(epartner.getCreateTime() == null ? "" : ExcelTimeUtils.getTimehhString(epartner.getCreateTime()));
                row.createCell(28).setCellValue(epartner.getUpdateTime() == null ? "" :  ExcelTimeUtils.getTimehhString(epartner.getUpdateTime()));
                row.createCell(29).setCellValue(epartner.getCreater() == null ? "" : epartner.getCreater().toString());
                row.createCell(30).setCellValue(epartner.getUpdater() == null ? "" : epartner.getUpdater().toString());
                row.createCell(31).setCellValue(epartner.getRemark() == null ? "" : epartner.getRemark().toString());
                row.createCell(32).setCellValue(epartner.getProjId() == null ? "" : epartner.getProjId().toString());

            }

        }
        totalRow += epartners.size();
        for(int i = 0;ecooperations.size()>0 && i<ecooperations.size();i++){
            if(i<=totalRow){
                row = sheet.getRow(i+2);
            }else {
                row = sheet.createRow(i + totalRow);
            }
            Ecooperation ecooperation = ecooperations.get(i);
            if(ecooperation != null) {
                row.createCell(33).setCellValue(ecooperation.getCoopId() == null ? "" : ecooperation.getCoopId().toString());
                row.createCell(34).setCellValue(ecooperation.getPartnerNo() == null ? "" : ecooperation.getPartnerNo().toString());
                row.createCell(35).setCellValue(ecooperation.getPartnerName() == null ? "" : ecooperation.getPartnerName().toString());
                //row.createCell(36).setCellValue(ecooperation.getPartnerCallintime() == null ? "" : ExcelTimeUtils.getTimehhString(ecooperation.getPartnerCallintime()));
                row.createCell(36).setCellValue(ecooperation.getPartnerCallintime() == null ? "" :ecooperation.getPartnerCallintime());
                row.createCell(37).setCellValue(ecooperation.getPartnerBdOwner() == null ? "" : ecooperation.getPartnerBdOwner().toString());
                row.createCell(38).setCellValue(ecooperation.getPartnerCostage() == null ? "" : ecooperation.getPartnerCostage().toString());
                row.createCell(39).setCellValue(ecooperation.getSignContract() == null ? "" : ecooperation.getSignContract().toString());
                row.createCell(40).setCellValue(ecooperation.getContractDate() == null ? "" : ecooperation.getContractDate().toString());
                row.createCell(41).setCellValue(ecooperation.getEntrust() == null ? "" : ecooperation.getEntrust().toString());
                row.createCell(42).setCellValue(ecooperation.getEntrustName() == null ? "" : ecooperation.getEntrustName().toString());
                row.createCell(43).setCellValue(ecooperation.getPartnerAwarding() == null ? "" : ecooperation.getPartnerAwarding().toString());
                row.createCell(44).setCellValue(ecooperation.getProjectName() == null ? "" : ecooperation.getProjectName().toString());
                row.createCell(45).setCellValue(ecooperation.getCoType() == null ? "" : ecooperation.getCoType().toString());
                row.createCell(46).setCellValue(ecooperation.getCoProgress() == null ? "" : ecooperation.getCoProgress().toString());
                row.createCell(47).setCellValue(ecooperation.getFiiCodepartment() == null ? "" : ecooperation.getFiiCodepartment().toString());
                row.createCell(48).setCellValue(ecooperation.getCreateTime() == null ? "" : ExcelTimeUtils.getTimehhString(ecooperation.getCreateTime()));
                row.createCell(49).setCellValue(ecooperation.getUpdateTime() == null ? "" :  ExcelTimeUtils.getTimehhString(ecooperation.getUpdateTime()));
                row.createCell(50).setCellValue(ecooperation.getCreater() == null ? "" : ecooperation.getCreater().toString());
                row.createCell(51).setCellValue(ecooperation.getUpdater() == null ? "" : ecooperation.getUpdater().toString());
              /*  row.createCell(56).setCellValue(ecooperation.getStatus() == null ? "" : ecooperation.getStatus().toString());
                row.createCell(57).setCellValue(ecooperation.getDelStatus() == null ? "" : ecooperation.getDelStatus().toString());*/
                row.createCell(52).setCellValue(ecooperation.getRemark() == null ? "" : ecooperation.getRemark().toString());
            }
        }
        sheet.setDefaultRowHeight((short) (30 * 30));
        //列宽自适应
        for (int i = 0; i <= 52; i++) {
            sheet.autoSizeColumn(i);
        }
        response.setHeader("Content-Type", "application/vnd.ms-excel;charset=utf-8");
        OutputStream os = response.getOutputStream();
        String fileName = "Excel.xls";
        response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "utf-8"));
        wb.write(os);
        os.flush();
        os.close();
    }
}
