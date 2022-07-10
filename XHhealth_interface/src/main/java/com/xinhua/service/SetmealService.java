package com.xinhua.service;

import com.xinhua.entity.PageResult;
import com.xinhua.entity.QueryPageBean;
import com.xinhua.pojo.CheckGroup;
import com.xinhua.pojo.Setmeal;

import java.util.List;
import java.util.Map;


/**
 * 体检套餐服务接口
 */
public interface SetmealService {
    public void add(Setmeal setmeal, Integer[] checkgroupIds);
    public PageResult pageQuery(QueryPageBean queryPageBean);
    public List<Setmeal> findAll();
    public Setmeal findById(int id);
    public List<Map<String,Object>> findSetmealCount();
}