package com.xinhua.service;
import com.xinhua.entity.PageResult;
import com.xinhua.entity.QueryPageBean;
import com.xinhua.pojo.CheckGroup;
import java.util.List;
/**
 * 检查组服务接口
 */
public interface CheckGroupService {
    void add(CheckGroup checkGroup,Integer[] checkitemIds);
    public PageResult pageQuery(QueryPageBean queryPageBean);
    CheckGroup findById(Integer id);
    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);
    public void edit(CheckGroup checkGroup,Integer[] checkitemIds);
    List<CheckGroup> findAll();
}