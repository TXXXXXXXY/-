package com.xinhua.service;

import com.xinhua.entity.PageResult;
import com.xinhua.entity.QueryPageBean;
import com.xinhua.pojo.Order;

public interface UploadReportService {
    //分页
    public PageResult pageQuery(QueryPageBean queryPageBean);
    public Order findById(Integer id);
    void editReport(Order order);

}
