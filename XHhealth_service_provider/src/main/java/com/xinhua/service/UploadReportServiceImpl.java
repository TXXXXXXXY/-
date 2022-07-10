package com.xinhua.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xinhua.dao.UploadReportDao;
import com.xinhua.entity.PageResult;
import com.xinhua.entity.QueryPageBean;
import com.xinhua.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = UploadReportService.class)
public class UploadReportServiceImpl implements UploadReportService{
    @Autowired
    private UploadReportDao uploadReportDao;

    //分页
    public PageResult pageQuery(QueryPageBean queryPageBean) {

        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();

        PageHelper.startPage(currentPage,pageSize);
        Page<Order> page = uploadReportDao.selectByCondition(queryString);
        long total = page.getTotal();
        List<Order> rows = page.getResult();
        return new PageResult(total,rows);
    }


    public Order findById(Integer id) {
        return uploadReportDao.findById(id);
    }


    public void editReport(Order order) {
        uploadReportDao.edit(order);
    }
}
