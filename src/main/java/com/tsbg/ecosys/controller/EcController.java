package com.tsbg.ecosys.controller;

import com.tsbg.ecosys.dto.Comment;
import com.tsbg.ecosys.model.bag.DerivediPackage;
import com.tsbg.ecosys.service.QfeedbackService;
import com.tsbg.ecosys.service.QuestionService;
import com.tsbg.ecosys.util.ResultUtils;
import com.tsbg.ecosys.dto.EcTotalDto;
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
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private QfeedbackService qfeedbackService;
    @Autowired
    private QuestionService questionService;

    @ApiOperation(value = "查询合作伙伴信息", notes = "查询合作伙伴信息")
    @RequestMapping(value = "/total", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResultUtils getEcooperationList(@RequestBody Epartner epartner) {
        //获取公司合作伙伴编号
        Integer cid = epartner.getPartnerNo();
        //根据合作伙伴编号同步查询合作情况与联系人
        if (cid != null) {
            List<EcTotalDto> ecTotalDtoList = ecooperationService.getEcooperationList(cid);
            return new ResultUtils(0, "根据PartnerNo查询", ecTotalDtoList);
        }
        return new ResultUtils(500, "未收到PartnerNo");
    }

    /*@ApiOperation(value = "按联系人查询", notes = "按联系人查询")
    @RequestMapping(value = "/contacts", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResultUtils getEcooperationListll(@RequestBody Epartner epartner) {
        //获取公司合作伙伴编号
        Integer cid = epartner.getPartnerNo();
        //根据合作伙伴编号同步查询合作情况与联系人
        if (cid != null) {
            List<EcTotalDto> ecTotalDtoList = ecooperationService.getEcooperationList(cid);
            return new ResultUtils(0, "根据PartnerNo查询", ecTotalDtoList);
        }
        return new ResultUtils(500, "未收到PartnerNo");
    }
*/

    /**
     * 查询公司文件
     */
    @RequestMapping(value = "/filedetail", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResultUtils getFileDetail(@RequestBody Epartner epartner) {
        Integer partnerNo = epartner.getPartnerNo();
        if (partnerNo != null) {
            //查询当前公司的文件详情
            List<String> filelist = epartnerService.selectFileByParNo(partnerNo);
            List<String> filelist2 = epartnerService.selectFileByParNo2(partnerNo);
            if (filelist != null) {
                return new ResultUtils(0, "查询公司文件(公司隐藏情况下文件也隐藏)", filelist, filelist2);
            }
            return new ResultUtils(500, "未查到相关文件");
        }
        return new ResultUtils(501, "未收到PartnerNo");
    }

    //根据查询条件导出Excel
    @RequestMapping(value = "/totalo", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    //@NeedLogin
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
        List<Epartner> epartner1 = ecooperationService.getEcooperationListall(epartner);
        export(response, epartner1);
    }

    //全部导出Excel（只导出status=0 and del_status=0）
    //@NeedLogin
    @RequestMapping(value = "/exportall", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void exportAll(HttpServletResponse response) throws IOException {
        EcTotal_Excel epartnerList = ecooperationService.getEpartnerList();
        exportexcel(response, epartnerList);
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
        row.createCell(38).setCellValue("与Fii合作模式");//为第五个单元格设值
        row.createCell(39).setCellValue("合作階段");//为第六个单元格设值
        row.createCell(40).setCellValue("是否簽署合約：1是/0否");//为第七个单元格设值
        row.createCell(41).setCellValue("合約起始日");//为第八个单元格设值
        row.createCell(42).setCellValue("是否有委託簽署關係:1是/0否");//为第九个单元格设值
        row.createCell(43).setCellValue("合約委託方");//为第十个单元格设值
        row.createCell(44).setCellValue("是否有授牌:1是/0否");//为第十一个单元格设值
        row.createCell(45).setCellValue("合作項目名稱");//为第十二个单元格设值
        row.createCell(46).setCellValue("合作業務類型");//为第十三个单元格设值
        row.createCell(47).setCellValue("合作項目進度");//为第十四个单元格设值
        row.createCell(48).setCellValue("Fii合作部門");//为第十五个单元格设值
        row.createCell(49).setCellValue("創建時間");//为第十五个单元格设值
        row.createCell(50).setCellValue("修改時間");//为第十五个单元格设值
        row.createCell(51).setCellValue("創建人");//为第十五个单元格设值
        row.createCell(52).setCellValue("修改人");//为第十五个单元格设值
     /*   row.createCell(56).setCellValue("隐藏状态：0未隐藏/1隐藏");//为第十五个单元格设值
        row.createCell(57).setCellValue("删除状态：0未删除/1删除");//为第十五个单元格设值*/
        row.createCell(53).setCellValue("備註");//为第十五个单元格设值

        int totalRow = 2;
        for (int i = 0; eccontactss.size() > 0 && i < eccontactss.size(); i++) {
            row = sheet.createRow(i + 2);
            Eccontacts eccontacts = eccontactss.get(i);
            if (eccontacts != null) {
                row.createCell(0).setCellValue(eccontacts.getContactId() == null ? "" : eccontacts.getContactId().toString());
                row.createCell(1).setCellValue(eccontacts.getPartnerNo() == null ? "" : eccontacts.getPartnerNo().toString());
                row.createCell(2).setCellValue(eccontacts.getPartnerName() == null ? "" : eccontacts.getPartnerName().toString());
                row.createCell(3).setCellValue(eccontacts.getName() == null ? "" : eccontacts.getName().toString());
                row.createCell(4).setCellValue(eccontacts.getTitle() == null ? "" : eccontacts.getTitle().toString());
                row.createCell(5).setCellValue(eccontacts.getGender() == null ? "" : eccontacts.getGender().toString());
                row.createCell(6).setCellValue(eccontacts.getPhoneNumber() == null ? "" : eccontacts.getPhoneNumber().toString());
                row.createCell(7).setCellValue(eccontacts.getEmailAddress() == null ? "" : eccontacts.getEmailAddress().toString());
                row.createCell(8).setCellValue(eccontacts.getCreateTime() == null ? "" : ExcelTimeUtils.getTimehhString(eccontacts.getCreateTime()));
                row.createCell(9).setCellValue(eccontacts.getUpdateTime() == null ? "" : ExcelTimeUtils.getTimehhString(eccontacts.getUpdateTime()));
                row.createCell(10).setCellValue(eccontacts.getCreater() == null ? "" : eccontacts.getCreater().toString());
                row.createCell(11).setCellValue(eccontacts.getUpdater() == null ? "" : eccontacts.getUpdater().toString());
              /*  row.createCell(12).setCellValue(eccontacts.getStatus()== null ? "":eccontacts.getStatus().toString());
                row.createCell(13).setCellValue(eccontacts.getDelStatus()== null ? "":eccontacts.getDelStatus().toString());
                ExcelTimeUtils.getTimeString(
                */
                row.createCell(12).setCellValue(eccontacts.getRemark() == null ? "" : eccontacts.getRemark().toString());
                totalRow++;
            }
        }

        for (int i = 0; epartners.size() > 0 && i < epartners.size(); i++) {
            if (i < totalRow - 2) {
                row = sheet.getRow(i + 2);
            } else {
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
        for (int i = 0; ecooperations.size() > 0 && i < ecooperations.size(); i++) {
            if (i < (totalRow - 2)) {
                row = sheet.getRow(i + 2);
            } else {
                row = sheet.createRow(totalRow++);
            }
            Ecooperation ecooperation = ecooperations.get(i);
            if (ecooperation != null) {
                row.createCell(33).setCellValue(ecooperation.getCoopId() == null ? "" : ecooperation.getCoopId().toString());
                row.createCell(34).setCellValue(ecooperation.getPartnerNo() == null ? "" : ecooperation.getPartnerNo().toString());
                row.createCell(35).setCellValue(ecooperation.getPartnerName() == null ? "" : ecooperation.getPartnerName().toString());
                // row.createCell(36).setCellValue(ecooperation.getPartnerCallintime() == null ? "" : ExcelTimeUtils.getTimehhString(ecooperation.getPartnerCallintime()));
                row.createCell(36).setCellValue(ecooperation.getPartnerCallintime() == null ? "" : ecooperation.getPartnerCallintime());
                row.createCell(37).setCellValue(ecooperation.getPartnerBdOwner() == null ? "" : ecooperation.getPartnerBdOwner().toString());
                row.createCell(38).setCellValue(ecooperation.getCoMode() == null ? "" : ecooperation.getCoMode().toString());
                row.createCell(39).setCellValue(ecooperation.getPartnerCostage() == null ? "" : ecooperation.getPartnerCostage().toString());
                row.createCell(40).setCellValue(ecooperation.getSignContract() == null ? "" : ecooperation.getSignContract().toString());
                row.createCell(41).setCellValue(ecooperation.getContractDate() == null ? "" : ecooperation.getContractDate().toString());
                row.createCell(42).setCellValue(ecooperation.getEntrust() == null ? "" : ecooperation.getEntrust().toString());
                row.createCell(43).setCellValue(ecooperation.getEntrustName() == null ? "" : ecooperation.getEntrustName().toString());
                row.createCell(44).setCellValue(ecooperation.getPartnerAwarding() == null ? "" : ecooperation.getPartnerAwarding().toString());
                row.createCell(45).setCellValue(ecooperation.getProjectName() == null ? "" : ecooperation.getProjectName().toString());
                row.createCell(46).setCellValue(ecooperation.getCoType() == null ? "" : ecooperation.getCoType().toString());
                row.createCell(47).setCellValue(ecooperation.getCoProgress() == null ? "" : ecooperation.getCoProgress().toString());
                row.createCell(48).setCellValue(ecooperation.getFiiCodepartment() == null ? "" : ecooperation.getFiiCodepartment().toString());
                row.createCell(49).setCellValue(ecooperation.getCreateTime() == null ? "" : ExcelTimeUtils.getTimehhString(ecooperation.getCreateTime()));
                row.createCell(50).setCellValue(ecooperation.getUpdateTime() == null ? "" : ExcelTimeUtils.getTimehhString(ecooperation.getUpdateTime()));
                row.createCell(51).setCellValue(ecooperation.getCreater() == null ? "" : ecooperation.getCreater().toString());
                row.createCell(52).setCellValue(ecooperation.getUpdater() == null ? "" : ecooperation.getUpdater().toString());
              /*  row.createCell(56).setCellValue(ecooperation.getStatus() == null ? "" : ecooperation.getStatus().toString());
                row.createCell(57).setCellValue(ecooperation.getDelStatus() == null ? "" : ecooperation.getDelStatus().toString());*/
                row.createCell(53).setCellValue(ecooperation.getRemark() == null ? "" : ecooperation.getRemark().toString());
            }
        }
        sheet.setDefaultRowHeight((short) (30 * 30));
        //列宽自适应
        for (int i = 0; i <= 53; i++) {
            sheet.autoSizeColumn(i);
        }
        response.setHeader("Content-Type", "application/vnd.ms-excel;charset=utf-8");
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        OutputStream os = response.getOutputStream();
        String fileName = "Excel.xls";
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
        wb.write(os);
        os.flush();
        os.close();
    }

    //根据查询条件导出Excel
    public void export(HttpServletResponse response, List<Epartner> epartnerList) throws IOException {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("获取excel测试表格");
        HSSFRow row = null;
        row = sheet.createRow(0);//创建第一个单元格
        row.setHeight((short) (40 * 20));
        row.createCell(0).setCellValue("合作情況信息表");//为第一行单元格设值
		/*
		* 第一个table_name 表名字
		* 第二个table_name 数据库名称
		* */
        //epartner表头
        row = sheet.createRow(1);
        row.setHeight((short) (22.50 * 20));//设置行高
        row.createCell(0).setCellValue("合作夥伴編號");//为第一个单元格设值
        row.createCell(1).setCellValue("合作夥伴公司名稱");//为第二个单元格设值
        row.createCell(2).setCellValue("公司註冊時間");//为第三个单元格设值
        row.createCell(3).setCellValue("公司註冊資本");//为第四个单元格设值
        row.createCell(4).setCellValue("是否為上市公司：1是/0否");//为第五个单元格设值
        row.createCell(5).setCellValue("公司規模/分佈");//为第六个单元格设值
        row.createCell(6).setCellValue("公司地址");//为第七个单元格设值
        row.createCell(7).setCellValue("公司網址");//为第八个单元格设值
        row.createCell(8).setCellValue("公司核心技術");//为第九个单元格设值
        row.createCell(9).setCellValue("主營產品/業務/服務");//为第十个单元格设值
        row.createCell(10).setCellValue("業務渠道");//为第十一个单元格设值
        row.createCell(11).setCellValue("營業額");//为第十二个单元格设值
        row.createCell(12).setCellValue("業務主要區域");//为第十三个单元格设值
        row.createCell(13).setCellValue("行業");//为第十四个单元格设值
       /* row.createCell(29).setCellValue("隱藏狀態：0未隱藏/1隱藏");//为第十五个单元格设值
        row.createCell(30).setCellValue("删除状态：0未删除/1删除");//为第十五个单元格设值*/
        row.createCell(14).setCellValue("創建時間");//为第十五个单元格设值
        row.createCell(15).setCellValue("修改時間");//为第十五个单元格设值
        row.createCell(16).setCellValue("創建人");//为第十五个单元格设值
        row.createCell(17).setCellValue("修改人");//为第十五个单元格设值
        row.createCell(18).setCellValue("備註");//为第十五个单元格设值
        row.createCell(19).setCellValue("項目ID");//为第十五个单元格设值
        int totalRow = 2;
        if (epartnerList != null && epartnerList.size() > 0) {
            for (int i = 0; i < epartnerList.size(); i++) {
                row = sheet.createRow(i + 2);
                Epartner epartner = epartnerList.get(i);
                row.createCell(0).setCellValue(epartner.getPartnerNo() == null ? "" : epartner.getPartnerNo().toString());
                row.createCell(1).setCellValue(epartner.getPartnerName() == null ? "" : epartner.getPartnerName().toString());
                //row.createCell(15).setCellValue(epartner.getPartnerDate() == null ? "" : ExcelTimeUtils.getTimehhString(epartner.getPartnerDate()));
                row.createCell(2).setCellValue(epartner.getPartnerDate() == null ? "" : epartner.getPartnerDate());
                row.createCell(3).setCellValue(epartner.getPartnerRegistcapital() == null ? "" : epartner.getPartnerRegistcapital().toString());
                row.createCell(4).setCellValue(epartner.getPartnerList() == null ? "" : epartner.getPartnerList().toString());
                row.createCell(5).setCellValue(epartner.getPartnerScale() == null ? "" : epartner.getPartnerScale().toString());
                row.createCell(6).setCellValue(epartner.getPartnerAddr() == null ? "" : epartner.getPartnerAddr().toString());
                row.createCell(7).setCellValue(epartner.getPartnerWebddr() == null ? "" : epartner.getPartnerWebddr().toString());
                row.createCell(8).setCellValue(epartner.getPartnerTech() == null ? "" : epartner.getPartnerTech());
                row.createCell(9).setCellValue(epartner.getPartnerProduct() == null ? "" : epartner.getPartnerProduct());
                row.createCell(10).setCellValue(epartner.getPartnerChannel() == null ? "" : epartner.getPartnerChannel().toString());
                row.createCell(11).setCellValue(epartner.getPartnerTurnover() == null ? "" : epartner.getPartnerTurnover().toString());
                row.createCell(12).setCellValue(epartner.getPartnerRegion() == null ? "" : epartner.getPartnerRegion().toString());
                row.createCell(13).setCellValue(epartner.getPartnerIndustry() == null ? "" : epartner.getPartnerIndustry().toString());
               /* row.createCell(29).setCellValue(epartner.getStatus() == null ? "" : epartner.getStatus().toString());
                row.createCell(30).setCellValue(epartner.getDelStatus() == null ? "" : epartner.getDelStatus().toString());*/
                row.createCell(14).setCellValue(epartner.getCreateTime() == null ? "" : ExcelTimeUtils.getTimehhString(epartner.getCreateTime()));
                row.createCell(15).setCellValue(epartner.getUpdateTime() == null ? "" : ExcelTimeUtils.getTimehhString(epartner.getUpdateTime()));
                row.createCell(16).setCellValue(epartner.getCreater() == null ? "" : epartner.getCreater().toString());
                row.createCell(17).setCellValue(epartner.getUpdater() == null ? "" : epartner.getUpdater().toString());
                row.createCell(18).setCellValue(epartner.getRemark() == null ? "" : epartner.getRemark().toString());
                row.createCell(19).setCellValue(epartner.getProjId() == null ? "" : epartner.getProjId().toString());
                totalRow++;
            }
        }
        sheet.setDefaultRowHeight((short) (30 * 30));
        //列宽自适应 (有多少列就遍历多少列)
        for (int i = 0; i <= 19; i++) {
            sheet.autoSizeColumn(i);
        }
        response.setHeader("Content-Type", "application/vnd.ms-excel;charset=utf-8");
        OutputStream os = response.getOutputStream();
        String fileName = "Excel.xls";
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
        wb.write(os);
        os.flush();
        os.close();
    }

    //导出系统名称、反馈人、处理人信息
    @RequestMapping(value = "/excelAll", method = { RequestMethod.GET, RequestMethod.POST })
    public ResultUtils exportAll(HttpServletRequest req, HttpServletResponse response) throws Exception {
        List<DerivediPackage> derivediPackages = questionService.selectquestion();
        System.out.println(derivediPackages);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Workbook wb = new XSSFWorkbook();
        Font titleFont = wb.createFont();
        titleFont.setBold(true);
        titleFont.setColor(IndexedColors.BLACK.index);
        //查询数据库中的备注
        List<String> titles = new ArrayList<>();
        List<Comment> selectColumnNamea = qfeedbackService.selectColumnNamea();
        List<Comment> selectColumnNameb = qfeedbackService.selectColumnNameb();
        List<Comment> selectColumnNamec = qfeedbackService.selectColumnNamec();
        List<Comment> selectColumnNamed = qfeedbackService.selectColumnNamed();
        List<Comment> selectColumnNamee = qfeedbackService.selectColumnNamee();
        for (int i = 0; i <= selectColumnNamea.size() - 1; i++) {
            if("proj_id".equals(selectColumnNamea.get(i).getColumn_name())){
                titles.add(selectColumnNamea.get(i).getColumn_comment());
            }
            if("pro_name".equals(selectColumnNamea.get(i).getColumn_name())){
                titles.add(selectColumnNamea.get(i).getColumn_comment());
            }
        }
        for (int i = 0; i <= selectColumnNameb.size() - 1; i++) {
            if("type_id".equals(selectColumnNameb.get(i).getColumn_name())){
                titles.add(selectColumnNameb.get(i).getColumn_comment());
            }
            if("type_name".equals(selectColumnNameb.get(i).getColumn_name())){
                titles.add(selectColumnNameb.get(i).getColumn_comment());
            }
        }
        for (int i = 0; i <= selectColumnNamec.size() - 1; i++) {
            if("file_name".equals(selectColumnNamec.get(i).getColumn_name())){
                titles.add(selectColumnNamec.get(i).getColumn_comment());
                break;
            }
        }
        for (int i = 0; i <= selectColumnNamed.size() - 1; i++) {
            if("user_code".equals(selectColumnNamed.get(i).getColumn_name())){
                titles.add(selectColumnNamed.get(i).getColumn_comment());
            }
            if("user_name".equals(selectColumnNamed.get(i).getColumn_name())){
                titles.add(selectColumnNamed.get(i).getColumn_comment());
            }
            if("user_ext".equals(selectColumnNamed.get(i).getColumn_name())){
                titles.add(selectColumnNamed.get(i).getColumn_comment());
            }
            if("user_email_address".equals(selectColumnNamed.get(i).getColumn_name())){
                titles.add(selectColumnNamed.get(i).getColumn_comment());
            }
        }
        for (int i = 0; i <= selectColumnNamee.size() - 1; i++) {
            if("handle_code".equals(selectColumnNamee.get(i).getColumn_name())){
                titles.add(selectColumnNamee.get(i).getColumn_comment());
            }
            if("handle_name".equals(selectColumnNamee.get(i).getColumn_name())){
                titles.add(selectColumnNamee.get(i).getColumn_comment());
            }
            if("start_date".equals(selectColumnNamee.get(i).getColumn_name())){
                titles.add(selectColumnNamee.get(i).getColumn_comment());
            }
            if("handle_time".equals(selectColumnNamee.get(i).getColumn_name())){
                titles.add(selectColumnNamee.get(i).getColumn_comment());
            }
            if("is_complete".equals(selectColumnNamee.get(i).getColumn_name())){
                titles.add(selectColumnNamee.get(i).getColumn_comment());
            }
        }
        for (int i = 0; i <= selectColumnNamec.size() - 1; i++) {
            if("file_name".equals(selectColumnNamec.get(i).getColumn_name())){
                titles.add(selectColumnNamec.get(i).getColumn_comment());
                break;
            }
        }
        for (int i = 0; i <= selectColumnNamee.size() - 1; i++) {
            if("remark".equals(selectColumnNamee.get(i).getColumn_name())){
                titles.add(selectColumnNamee.get(i).getColumn_comment());
                break;
            }
        }
        String sheetName = "问题反馈信息处理文档";
        Sheet companySheet = wb.createSheet(sheetName);
        Row titleRow = companySheet.createRow(0);
        CellStyle style = wb.createCellStyle();
        style.setFont(titleFont);
        style.setFillPattern(CellStyle.FINE_DOTS);
        style.setFillBackgroundColor(IndexedColors.GREY_25_PERCENT.index);
        Cell cell = null;
        for (int i = 0; i < titles.size(); i++) {
            cell = titleRow.createCell(i);
            cell.setCellStyle(style);
        }
        //填写表头
        for(int i=0;i<titles.size();i++){
            titleRow.createCell(i).setCellValue(titles.get(i).toString());//为第一个单元格设值
        }
        Row row = null;
        for (int i = 0; i < derivediPackages.size(); i++) {
            row = companySheet.createRow(i + 1);
            row.createCell(0).setCellValue(derivediPackages.get(i).getProjIda() == null ? "" : derivediPackages.get(i).getProjIda().toString());
            row.createCell(1).setCellValue(derivediPackages.get(i).getProNamea() == null ? "" :derivediPackages.get(i).getProNamea().toString());
            row.createCell(2).setCellValue(derivediPackages.get(i).getTypeNameb() == null ? "" : derivediPackages.get(i).getTypeNameb().toString());
            row.createCell(3).setCellValue(derivediPackages.get(i).getStatusb() == null ? "" : derivediPackages.get(i).getStatusb().toString());
            row.createCell(4).setCellValue(derivediPackages.get(i).getFileNamec() == null ? "" : derivediPackages.get(i).getFileNamec().toString());
            row.createCell(5).setCellValue(derivediPackages.get(i).getUserCoded() == null ? "" :derivediPackages.get(i).getUserCoded().toString());
            row.createCell(6).setCellValue(derivediPackages.get(i).getUserNamed() == null ? "" : derivediPackages.get(i).getUserNamed().toString());
            row.createCell(7).setCellValue(derivediPackages.get(i).getUserExtd() == null ? "" : derivediPackages.get(i).getUserExtd().toString());
            row.createCell(8).setCellValue(derivediPackages.get(i).getUserEmailAddressd() == null ? "" : derivediPackages.get(i).getUserEmailAddressd().toString());
            row.createCell(9).setCellValue(derivediPackages.get(i).getHandleCodee() == null ? "" : derivediPackages.get(i).getHandleCodee().toString());
            row.createCell(10).setCellValue(derivediPackages.get(i).getHandleNamee()== null ? "" : derivediPackages.get(i).getHandleNamee().toString());
            row.createCell(11).setCellValue(derivediPackages.get(i).getStartDatee() == null ? "" : ExcelTimeUtils.getTimehhString(derivediPackages.get(i).getStartDatee()));
            row.createCell(12).setCellValue(derivediPackages.get(i).getHandleTimee() == null ? "" : ExcelTimeUtils.getTimehhString(derivediPackages.get(i).getHandleTimee()));
            row.createCell(13).setCellValue(derivediPackages.get(i).getIsCompletee() == null ? "" : derivediPackages.get(i).getIsCompletee().toString());
            row.createCell(14).setCellValue(derivediPackages.get(i).getFileNamec() == null ? "" : derivediPackages.get(i).getFileNamec().toString());
            row.createCell(15).setCellValue(derivediPackages.get(i).getRemarke() == null ? "" : derivediPackages.get(i).getRemarke().toString());
        }
        for (int i = 0; i < derivediPackages.size(); i++) {
            companySheet.autoSizeColumn(i, true);
        }
        response.setHeader("Content-Type", "application/vnd.ms-excel;charset=utf-8");
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        OutputStream os = response.getOutputStream();
        String fileName = sdf.format(new Date()) + sheetName + ".xlsx";
        response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "utf-8"));
        wb.write(os);
        os.flush();
        os.close();
        return null;
    }
}