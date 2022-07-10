package com.xinhua.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xinhua.entity.PageResult;
import com.xinhua.entity.QueryPageBean;
import com.xinhua.entity.Result;
import com.xinhua.pojo.CheckItem;
import com.xinhua.pojo.Order;

import java.util.List;
import java.util.Map;
/**
 * 体检预约服务接口*/


public interface OrderService {
    //体检预约
    public Result order(Map map) throws Exception;
    //根据id查询预约信息，包括体检人信息、套餐信息
    public Map findById(Integer id) throws Exception;
    // 返回体检报告信息
    public List<Order> getOrders(String phone);
}
