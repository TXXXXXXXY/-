package com.xinhua.test;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.*;


public class QiNiutest {
    @Test
    public void test1(){

        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
//...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
//...生成上传凭证，然后准备上传
        String accessKey = "JCiCYCBdHa9XeeGY5H-kjKvi_M2nblaERCmXpF-3";
        String secretKey = "-8kTZHP0BSoKDqa_OU_-z7CsxJ0f-qPwoOXd6-FJ";
        String bucket = "xinhuahealth-space-1";
//如果是Windows情况下，格式是 D:\\qiniu\\test.png
        String localFilePath = "D:\\__easyHelper__\\day04\\资源\\图片资源\\03a36073-a140-4942-9b9b-712cecb144901.jpg";
//默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
    }
    @Test
    public void test2(){
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        //...生成上传凭证，然后准备上传
        String accessKey = "JCiCYCBdHa9XeeGY5H-kjKvi_M2nblaERCmXpF-3";
        String secretKey = "-8kTZHP0BSoKDqa_OU_-z7CsxJ0f-qPwoOXd6-FJ";
        String bucket = "xinhuahealth-space-1";
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, "ee7dcf84-8a3a-4ab9-b981-9c5d272fd58d3.jpg");
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }

    }
    @Test
    public  void test3() throws Exception{
        //创建工作簿
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File("D:\\1.xlsx")));
//获取工作表，既可以根据工作表的顺序获取，也可以根据工作表的名称获取
        XSSFSheet sheet = workbook.getSheetAt(0);
        int lastRowNum = sheet.getLastRowNum();
        for(int i=0;i<=lastRowNum;i++){
            //根据行号获取行对象
            XSSFRow row = sheet.getRow(i);
            short lastCellNum = row.getLastCellNum();
            for(short j=0;j<lastCellNum;j++){
                String value = row.getCell(j).getStringCellValue();
                System.out.print(value+" ");
            }
            System.out.println();
        }
        workbook.close();
    }

    @Test
    public void test4() throws IOException {
        ///在内存中创建一个Excel文件
        XSSFWorkbook workbook = new XSSFWorkbook();
        //创建工作表，指定工作表名称
        XSSFSheet sheet = workbook.createSheet("test");

        //创建行，0表示第一行
        XSSFRow row = sheet.createRow(0);
        //创建单元格，0表示第一个单元格
        row.createCell(0).setCellValue("编号");
        row.createCell(1).setCellValue("名称");
        row.createCell(2).setCellValue("年龄");

        XSSFRow row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue("1");
        row1.createCell(1).setCellValue("小明");
        row1.createCell(2).setCellValue("10");

        XSSFRow row2 = sheet.createRow(2);
        row2.createCell(0).setCellValue("2");
        row2.createCell(1).setCellValue("小王");
        row2.createCell(2).setCellValue("20");

        //通过输出流将workbook对象下载到磁盘
        FileOutputStream out = new FileOutputStream("D:\\xxx.xlsx");
        workbook.write(out);
        out.flush();
        out.close();
        workbook.close();


    }
    @Test
    public void test5(){
        String string = "asdn23ur124g2prg?asdf=12&sdf=23";
        String[] a=string.split("[?]");
        System.out.println("a.length "+a.length);
        String[] b=a[1].split("&");
        System.out.println("b.length "+b.length);
        for(int i=0;i<b.length;i++){
            String[] c=b[i].split("=");
            System.out.println(c[0]);
        }


    }


}
