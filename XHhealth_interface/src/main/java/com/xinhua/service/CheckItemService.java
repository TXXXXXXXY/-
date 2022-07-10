package com.xinhua.service;

import com.xinhua.entity.PageResult;
import com.xinhua.entity.QueryPageBean;
import com.xinhua.pojo.CheckItem;
import java.util.List;
/**
 * 检查项服务接口
 */
public interface CheckItemService {
    public void add(CheckItem checkItem);
    public PageResult pageQuery(QueryPageBean queryPageBean);
    public void delete(Integer id);
    public void edit(CheckItem checkItem);
    public CheckItem findById(Integer id);
    public List<CheckItem> findAll();
}