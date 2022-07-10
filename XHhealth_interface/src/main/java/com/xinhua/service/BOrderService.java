package com.xinhua.service;

import com.xinhua.entity.PageResult;
import com.xinhua.entity.QueryPageBean;

public interface BOrderService {
    //分页
    public PageResult pageQuery(QueryPageBean queryPageBean);
    //yes
    public void changeOrderStatus(Integer id,Integer t);
}
