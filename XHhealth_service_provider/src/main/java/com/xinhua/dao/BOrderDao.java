package com.xinhua.dao;

import com.github.pagehelper.Page;
import com.xinhua.pojo.Order;

public interface BOrderDao {
    public Page<Order> selectByCondition(String queryString);
    public void setStatusY(Integer id);
    public void SetStatusN(Integer id);
}
