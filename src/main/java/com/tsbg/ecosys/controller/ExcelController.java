package com.tsbg.ecosys.controller;

import com.tsbg.ecosys.model.Eccontacts;
import com.tsbg.ecosys.model.Epartner;
import com.tsbg.ecosys.model.bag.MixPackage;
import com.tsbg.ecosys.service.EpartnerService;
import com.tsbg.ecosys.service.MixPackageService;
import com.tsbg.ecosys.util.ResultUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 条件搜索后导出合作伙伴信息到excel
 */
@RestController
public class ExcelController {

    @Autowired
    private EpartnerService epartnerService;
    @Autowired
    private MixPackageService mixPackageService;

    @RequestMapping(value = "/excel", method = { RequestMethod.GET, RequestMethod.POST })
    public ResultUtils excel(HttpServletRequest req, HttpServletResponse response) throws Exception {
        String userCode = req.getHeader("userCode");
        System.out.println("Header:"+userCode);
        if(userCode == null || userCode.equals("")){
            return new ResultUtils(501,"非常规方式进入管理员后台！请登录后再试");
        }
        //获取五个搜索条件，没有则为空
        String partnerName = req.getParameter("partnerName");
        String partnerProduct = req.getParameter("partnerProduct");
        String partnerRegion = req.getParameter("partnerRegion");
        String partnerIndustry = req.getParameter("partnerIndustry");
        String name = req.getParameter("name");
        Epartner epartner = new Epartner();
        Eccontacts eccontacts = new Eccontacts();
        if (partnerName!=null && !partnerName.equals("")){
            epartner.setPartnerName(partnerName);
        }
        if (partnerProduct!=null && !partnerProduct.equals("")){
            epartner.setPartnerProduct(partnerProduct);
        }
        if (partnerRegion!=null && !partnerRegion.equals("")){
            epartner.setPartnerRegion(partnerRegion);
        }
        if (partnerIndustry!=null && !partnerIndustry.equals("")){
            epartner.setPartnerIndustry(partnerIndustry);
        }
        if (name!=null && !name.equals("")){
            eccontacts.setName(name);
        }
        //查出公司所有相关信息
        List<MixPackage> mixPackages = mixPackageService.selectAll(epartner,eccontacts);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Workbook wb = new XSSFWorkbook();
        Font titleFont = wb.createFont();
        titleFont.setBold(true);
        titleFont.setColor(IndexedColors.BLACK.index);
        //添加列名 与数据库的备注一致
        List<String> titles = new ArrayList<>();
        List<String> columnlist = epartnerService.selectColumnName();
        List<String> columnlist2 = epartnerService.selectColumnName2();
        List<String> columnlist3 = epartnerService.selectColumnName3();
        for (int i=0;i<=columnlist.size()-1;i++){
            titles.add(columnlist.get(i));
        }
        for (int i=0;i<=columnlist2.size()-1;i++){
            titles.add(columnlist2.get(i));
        }
        for (int i=0;i<=columnlist3.size()-1;i++){
            titles.add(columnlist3.get(i));
        }
        //设置sheet名称，并创建新的sheet对象
        String sheetName = "公司合作夥伴信息一覽";
        Sheet companySheet = wb.createSheet(sheetName);
        //获取表头行
        Row titleRow = companySheet.createRow(0);
        //创建单元格，设置style居中，字体，单元格大小，背景色等
        CellStyle style = wb.createCellStyle();
        style.setFont(titleFont);
        style.setFillPattern(CellStyle.FINE_DOTS);
        style.setFillBackgroundColor(IndexedColors.GREY_25_PERCENT.index);
        Cell cell = null;
        //把已经写好的标题行写入excel文件中
        for (int i = 0; i < titles.size(); i++) {
            cell = titleRow.createCell(i);
            cell.setCellValue(titles.get(i));
            cell.setCellStyle(style);
        }
        //把从数据库中取得的数据一一写入excel文件中
        Row row = null;
        for (int i = 0; i < mixPackages.size(); i++) {
            //创建list.size()行数据
            row = companySheet.createRow(i + 1);
            //把值一一写进单元格里
            row.createCell(0).setCellValue(mixPackages.get(i).getPartnerNo()== null ? "" : mixPackages.get(i).getPartnerNo().toString());
            row.createCell(1).setCellValue(mixPackages.get(i).getPartnerName()== null ? "" : mixPackages.get(i).getPartnerName());
            row.createCell(2).setCellValue(mixPackages.get(i).getPartnerDate()== null ? "" : mixPackages.get(i).getPartnerDate());
            row.createCell(3).setCellValue(mixPackages.get(i).getPartnerRegistcapital()== null ? "" : mixPackages.get(i).getPartnerRegistcapital());
            row.createCell(4).setCellValue(mixPackages.get(i).getPartnerList()== null ? "" : mixPackages.get(i).getPartnerList().toString());
            row.createCell(5).setCellValue(mixPackages.get(i).getPartnerScale()== null ? "" : mixPackages.get(i).getPartnerScale());
            row.createCell(6).setCellValue(mixPackages.get(i).getPartnerAddr()== null ? "" : mixPackages.get(i).getPartnerAddr());
            row.createCell(7).setCellValue(mixPackages.get(i).getPartnerWebddr()== null ? "" : mixPackages.get(i).getPartnerWebddr());
            row.createCell(8).setCellValue(mixPackages.get(i).getPartnerTech()== null ? "" : mixPackages.get(i).getPartnerTech());
            row.createCell(9).setCellValue(mixPackages.get(i).getPartnerProduct()== null ? "" : mixPackages.get(i).getPartnerProduct());
            row.createCell(10).setCellValue(mixPackages.get(i).getPartnerChannel()== null ? "" : mixPackages.get(i).getPartnerChannel());
            row.createCell(11).setCellValue(mixPackages.get(i).getPartnerTurnover()== null ? "" : mixPackages.get(i).getPartnerTurnover());
            row.createCell(12).setCellValue(mixPackages.get(i).getPartnerRegion()== null ? "" : mixPackages.get(i).getPartnerRegion());
            row.createCell(13).setCellValue(mixPackages.get(i).getPartnerIndustry()== null ? "" : mixPackages.get(i).getPartnerIndustry());
            row.createCell(14).setCellValue(mixPackages.get(i).getStatus()== null ? "" : mixPackages.get(i).getStatus().toString());
            row.createCell(15).setCellValue(mixPackages.get(i).getDelStatus()== null ? "" : mixPackages.get(i).getDelStatus().toString());
            row.createCell(16).setCellValue(mixPackages.get(i).getCreateTime()== null ? "" : sdf.format(mixPackages.get(i).getCreateTime()));
            row.createCell(17).setCellValue(mixPackages.get(i).getUpdateTime()== null ? "" : sdf.format(mixPackages.get(i).getUpdateTime()));
            row.createCell(18).setCellValue(mixPackages.get(i).getCreater()== null ? "" : mixPackages.get(i).getCreater());
            row.createCell(19).setCellValue(mixPackages.get(i).getCreaterIdentity()== null ? "" : mixPackages.get(i).getCreaterIdentity().toString());
            row.createCell(20).setCellValue(mixPackages.get(i).getUpdater()== null ? "" : mixPackages.get(i).getUpdater());
            row.createCell(21).setCellValue(mixPackages.get(i).getRemark()== null ? "" : mixPackages.get(i).getRemark());
            row.createCell(22).setCellValue(mixPackages.get(i).getProjId()== null ? "" : mixPackages.get(i).getProjId().toString());
            row.createCell(23).setCellValue(mixPackages.get(i).getCoopId()== null ? "" : mixPackages.get(i).getCoopId().toString());
            row.createCell(24).setCellValue(mixPackages.get(i).getPartnerNo2()== null ? "" : mixPackages.get(i).getPartnerNo2().toString());
            row.createCell(25).setCellValue(mixPackages.get(i).getPartnerName2()== null ? "" : mixPackages.get(i).getPartnerName2());
            row.createCell(26).setCellValue(mixPackages.get(i).getPartnerCallintime()== null ? "" : mixPackages.get(i).getPartnerCallintime());
            row.createCell(27).setCellValue(mixPackages.get(i).getPartnerBdOwner()== null ? "" : mixPackages.get(i).getPartnerBdOwner());
            row.createCell(28).setCellValue(mixPackages.get(i).getCoMode()== null ? "" : mixPackages.get(i).getCoMode());
            row.createCell(29).setCellValue(mixPackages.get(i).getPartnerCostage()== null ? "" : mixPackages.get(i).getPartnerCostage());
            row.createCell(30).setCellValue(mixPackages.get(i).getSignContract()== null ? "" : mixPackages.get(i).getSignContract().toString());
            row.createCell(31).setCellValue(mixPackages.get(i).getContractDate()== null ? "" : mixPackages.get(i).getContractDate());
            row.createCell(32).setCellValue(mixPackages.get(i).getEntrust()== null ? "" : mixPackages.get(i).getEntrust().toString());
            row.createCell(33).setCellValue(mixPackages.get(i).getEntrustName()== null ? "" : mixPackages.get(i).getEntrustName());
            row.createCell(34).setCellValue(mixPackages.get(i).getPartnerAwarding()== null ? "" : mixPackages.get(i).getPartnerAwarding().toString());
            row.createCell(35).setCellValue(mixPackages.get(i).getProjectName()== null ? "" : mixPackages.get(i).getProjectName());
            row.createCell(36).setCellValue(mixPackages.get(i).getCoType()== null ? "" : mixPackages.get(i).getCoType());
            row.createCell(37).setCellValue(mixPackages.get(i).getCoProgress()== null ? "" : mixPackages.get(i).getCoProgress());
            row.createCell(38).setCellValue(mixPackages.get(i).getFiiCodepartment()== null ? "" : mixPackages.get(i).getFiiCodepartment());
            row.createCell(39).setCellValue(mixPackages.get(i).getCreateTime2()== null ? "" : sdf.format(mixPackages.get(i).getCreateTime2()));
            row.createCell(40).setCellValue(mixPackages.get(i).getUpdateTime2()== null ? "" : sdf.format(mixPackages.get(i).getUpdateTime2()));
            row.createCell(41).setCellValue(mixPackages.get(i).getCreater2()== null ? "" : mixPackages.get(i).getCreater2());
            row.createCell(42).setCellValue(mixPackages.get(i).getUpdater2()== null ? "" : mixPackages.get(i).getUpdater2());
            row.createCell(43).setCellValue(mixPackages.get(i).getStatus2()== null ? "" : mixPackages.get(i).getStatus2().toString());
            row.createCell(44).setCellValue(mixPackages.get(i).getDelStatus2()== null ? "" : mixPackages.get(i).getDelStatus2().toString());
            row.createCell(45).setCellValue(mixPackages.get(i).getRemark2()== null ? "" : mixPackages.get(i).getRemark2());
            row.createCell(46).setCellValue(mixPackages.get(i).getContactId()== null ? "" : mixPackages.get(i).getContactId().toString());
            row.createCell(47).setCellValue(mixPackages.get(i).getPartnerNo3()== null ? "" : mixPackages.get(i).getPartnerNo3().toString());
            row.createCell(48).setCellValue(mixPackages.get(i).getPartnerName3()== null ? "" : mixPackages.get(i).getPartnerName3());
            row.createCell(49).setCellValue(mixPackages.get(i).getName()== null ? "" : mixPackages.get(i).getName());
            row.createCell(50).setCellValue(mixPackages.get(i).getTitle()== null ? "" : mixPackages.get(i).getTitle());
            row.createCell(51).setCellValue(mixPackages.get(i).getGender()== null ? "" : mixPackages.get(i).getGender().toString());
            row.createCell(52).setCellValue(mixPackages.get(i).getPhoneNumber()== null ? "" : mixPackages.get(i).getPhoneNumber());
            row.createCell(53).setCellValue(mixPackages.get(i).getEmailAddress()== null ? "" : mixPackages.get(i).getEmailAddress());
            row.createCell(54).setCellValue(mixPackages.get(i).getCreateTime3()== null ? "" : sdf.format(mixPackages.get(i).getCreateTime3()));
            row.createCell(55).setCellValue(mixPackages.get(i).getUpdateTime3()== null ? "" : sdf.format(mixPackages.get(i).getUpdateTime3()));
            row.createCell(56).setCellValue(mixPackages.get(i).getCreater3()== null ? "" : mixPackages.get(i).getCreater3());
            row.createCell(57).setCellValue(mixPackages.get(i).getUpdater3()== null ? "" : mixPackages.get(i).getUpdater3());
            row.createCell(58).setCellValue(mixPackages.get(i).getStatus3()== null ? "" : mixPackages.get(i).getStatus3().toString());
            row.createCell(59).setCellValue(mixPackages.get(i).getDelStatus3()== null ? "" : mixPackages.get(i).getDelStatus3().toString());
            row.createCell(60).setCellValue(mixPackages.get(i).getRemark3()== null ? "" : mixPackages.get(i).getRemark3());
        }
        for (int i = 0; i < mixPackages.size(); i++) {
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
        //获取配置文件中保存对应excel文件的路径，本地也可以直接写成F：excel/stuInfoExcel路径
        //String folderPath = ResourceBundle.getBundle("systemconfig").getString("downloadFolder") + File.separator + "stuInfoExcel";
        //String folderPath = "D:\\66" + File.separator +"excel"+ File.separator + "stuInfoExcel";
       /* //创建上传文件目录
        File folder = new File(folderPath);
        //如果文件夹不存在创建对应的文件夹
        if (!folder.exists()) {
            folder.mkdirs();
        }
        //设置文件名
        String fileName = sdf1.format(new Date()) + sheetName + ".xlsx";
        String savePath = folderPath + File.separator + fileName;
        // System.out.println(savePath);
        OutputStream fileOut = new FileOutputStream(savePath);
        wb.write(fileOut);
        fileOut.close();
        //返回文件保存全路径
        return savePath;*/
    }
}
