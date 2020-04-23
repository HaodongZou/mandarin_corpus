package cn.zouhd.mandarin_corpus.service.impl;

import cn.zouhd.mandarin_corpus.entities.Yunshu;
import cn.zouhd.mandarin_corpus.exception.MyExcetpion;
import cn.zouhd.mandarin_corpus.repositories.YunshuRepo;
import cn.zouhd.mandarin_corpus.service.ExcelService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelServiceImpl implements ExcelService {

    @Autowired
    YunshuRepo yunshuRepo;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean excelBatchImport(String filename, MultipartFile multipartFile) throws Exception {

        List<Yunshu> yunshuList = new ArrayList<>();
//        判断上传的文件是.xls还是.xlsx文件
        boolean isExcel2003 = false;
        if (filename.matches("^.+\\.(?i)(xls)$")){
            isExcel2003 = true;
        }
        else if (!filename.matches("^.+\\.(?i)(xlsx)$")){
            logger.warn("提交的文件：" + filename + " 不是一个Excel格式的文件");
            throw new MyExcetpion("上传的不是.xls或.xlsx文件");
        }


        InputStream excelFile = multipartFile.getInputStream();
        Workbook wb = null;
        if (isExcel2003){
            wb = new HSSFWorkbook(excelFile);
        }else {
            wb = new XSSFWorkbook(excelFile);
        }
        //判断是否为空表
        Sheet sheet = wb.getSheetAt(0);

        if (sheet == null){
            return false;
        }

        Yunshu yunshu;

        Field[] fields = Yunshu.class.getDeclaredFields();

        //获取实体类中每个属性的名称，并将首字母大写
        String[] attributeName = new String[fields.length];
        String name;
        for (int i = 0; i < fields.length; i++) {
            name = fields[i].getName();
            attributeName[i] = name.substring(0, 1).toUpperCase() + name.substring(1);
        }

        //TODO:添加自定义起始行功能
        //这里起始行为4.根据“元明清汉语韵书宕江摄入声数据库 1.xlsx”
        for (int r = 4; r < sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);

            if (row == null || row.getCell(0).getNumericCellValue() == 0){
                continue;
            }

            yunshu = new Yunshu();
            yunshu.setId((int) row.getCell(0).getNumericCellValue());
            //TODO: 修改 attributeName.length 为 row.getLastCellNum()
            for (int c = 1; c < attributeName.length ; c++) {
                try {
                    Method setMethod = Yunshu.class.getMethod("set" + attributeName[c], String.class);
                    setMethod.invoke(yunshu, row.getCell(c).getStringCellValue());
                } catch (NoSuchMethodException | SecurityException e) {
                    e.printStackTrace();

                    logger.error("上传文件出现异常，事务已回滚");
                }

            }

            yunshuList.add(yunshu);
            
        }


        for (Yunshu yunshuRecord : yunshuList) {
            yunshuRepo.save(yunshuRecord);
        }

        //返回true表示导入操作成功
        return true;
    }
}
