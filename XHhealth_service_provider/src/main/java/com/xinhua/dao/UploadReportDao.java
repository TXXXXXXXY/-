package com.xinhua.dao;

import com.github.pagehelper.Page;
import com.xinhua.pojo.Order;
import org.aspectj.weaver.ast.Or;

public interface UploadReportDao {
    public Page<Order> selectByCondition(String queryString);
    public Order findById(Integer id);
    public void edit(Order order);
}
