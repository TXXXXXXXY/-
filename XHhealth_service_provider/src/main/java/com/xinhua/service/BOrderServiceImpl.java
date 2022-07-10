package com.xinhua.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xinhua.dao.BOrderDao;
import com.xinhua.entity.PageResult;
import com.xinhua.entity.QueryPageBean;
import com.xinhua.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service(interfaceClass = BOrderService.class)
@Transactional
public class BOrderServiceImpl implements BOrderService{
    @Autowired
    private BOrderDao bOrderDao;
    @Override
    //分页
    public PageResult pageQuery(QueryPageBean queryPageBean) {

        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();

        PageHelper.startPage(currentPage,pageSize);
        Page<Order> page = bOrderDao.selectByCondition(queryString);
        long total = page.getTotal();
        List<Order> rows = page.getResult();
        return new PageResult(total,rows);
    }
    public void changeOrderStatus(Integer id,Integer t){
        if(t==1)
            bOrderDao.setStatusY(id);
        else if(t==0)
            bOrderDao.SetStatusN(id);
    }
}
