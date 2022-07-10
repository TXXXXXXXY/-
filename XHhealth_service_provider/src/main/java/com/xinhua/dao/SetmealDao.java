package com.xinhua.dao;

import com.github.pagehelper.Page;
import com.xinhua.pojo.Setmeal;

import java.util.List;
import java.util.Map;

public interface SetmealDao {
    public void add(Setmeal setmeal);
    public void setSetmealAndCheckGroup(Map<String, Integer> map);
    Page<Setmeal> findByCondition(String queryString);
    public List<Setmeal> findAll();
    public Setmeal findById(int id);
    public List<Map<String,Object>> findSetmealCount();
}