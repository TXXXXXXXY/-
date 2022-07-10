package com.xinhua.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xinhua.constant.MessageConstant;
import com.xinhua.entity.PageResult;
import com.xinhua.entity.QueryPageBean;
import com.xinhua.entity.Result;
import com.xinhua.service.BOrderService;
import com.xinhua.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class BOrderController {

    @Reference
    private BOrderService bOrderService;
    //分页查询
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = bOrderService.pageQuery(queryPageBean);
        return pageResult;
    }
    @RequestMapping("/changeOrderStatusYes")
    public Result changeOrderStatusY(Integer id){
        int t=1;
        try {
            bOrderService.changeOrderStatus(id,t);
        }catch (Exception e){
            return new Result(false, "修改失败");
        }
        return new Result(true,"修改成功");
    }
    @RequestMapping("/changeNo")
    public Result changeOrderStatusN(Integer id){
        int t=0;
        try {
            bOrderService.changeOrderStatus(id,t);
        }catch (Exception e){
            return new Result(false, "修改失败");
        }
        return new Result(true,"修改成功");
    }
}
