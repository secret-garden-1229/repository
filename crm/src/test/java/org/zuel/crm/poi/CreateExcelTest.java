package org.zuel.crm.poi;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CreateExcelTest {
    public static void main(String[] args) {
        //new CreateExcelTest().test1();
        System.out.println(new CreateExcelTest().getClass().getClassLoader().getResource("applicationContext.xml"));
        System.out.println(new CreateExcelTest().getClass().getClassLoader().getResource("ActivityMapper.xml"));
        System.out.println(new CreateExcelTest().getClass().getClassLoader().getResource("image/home.png"));
        System.out.println(new CreateExcelTest().getClass().getClassLoader().getResource("/"));

    }

    public void test1(){
        //
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("test");
        //放到多少行，从0开始
        HSSFRow row = sheet.createRow(20);
        //放到多少列，从0开始
        HSSFCell cell = row.createCell(20);
        cell.setCellValue("test-1");
        cell = row.createCell(3);
        cell.setCellValue("test-2");
        int i = 1,j=1;
        for (i=0;i<10;i++){
            for (j=0;j<9-i;j++){
                row = sheet.createRow(i);
                cell = row.createCell(j);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(i);
                stringBuilder.append("*");
                stringBuilder.append(j);
                stringBuilder.append("=");
                stringBuilder.append(i*j);
                cell.setCellValue(stringBuilder.toString());
            }
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("E:\\Study_relation\\CRM_SSM\\workspace\\testpoi.xls");
            workbook.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (workbook != null){
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("ok!");
    }
}
