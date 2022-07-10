package com.xinhua.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xinhua.constant.MessageConstant;
import com.xinhua.constant.RedisConstant;
import com.xinhua.entity.PageResult;
import com.xinhua.entity.QueryPageBean;
import com.xinhua.entity.Result;
import com.xinhua.pojo.CheckItem;
import com.xinhua.pojo.Order;
import com.xinhua.service.UploadReportService;
import com.xinhua.utils.QiniuUtils1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/uploadReport")
public class UploadReportController {

    @Reference
    private UploadReportService uploadReportService;
    @Autowired
    private JedisPool jedisPool;
    //分页查询
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = uploadReportService.pageQuery(queryPageBean);
        return pageResult;
    }
    @RequestMapping("/findByOrderId")
    public Result findByOrderId(Integer id){
        try {
            Order order=new Order();
            order = uploadReportService.findById(id);
            return new Result(true,"success",order);
        }catch (Exception e){
            return new Result(false,"false");
        }
    }
    @RequestMapping("/edit")
    public Result reportEdit(@RequestBody Order order){
        try {
            uploadReportService.editReport(order);
        }catch (Exception e){
            return new Result(false,"操作失败");
        }
        return new Result(true,"上传成功");
    }
    @RequestMapping("/upload")
    public Result reportUpload(@RequestParam("imgFile")MultipartFile imgFile){
        try{
            //获取原始文件名
            String originalFilename = imgFile.getOriginalFilename();
            int lastIndexOf = originalFilename.lastIndexOf(".");
            //获取文件后缀
            String suffix = originalFilename.substring(lastIndexOf - 1);
            //使用UUID随机产生文件名称，防止同名文件覆盖
            String fileName = UUID.randomUUID().toString() + suffix;
            QiniuUtils1.upload2Qiniu(imgFile.getBytes(),fileName);
            //图片上传成功
            Result result = new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS);
            result.setData(fileName);
            //将上传图片名称存入Redis，基于Redis的Set集合存储
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES,fileName);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            //图片上传失败
            return new Result(false,MessageConstant.PIC_UPLOAD_FAIL);
        }
    }
}
