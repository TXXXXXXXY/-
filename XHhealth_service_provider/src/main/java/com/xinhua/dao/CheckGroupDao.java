package com.xinhua.dao;
import com.github.pagehelper.Page;
import com.xinhua.pojo.CheckGroup;
import java.util.List;
import java.util.Map;
/**
 * 持久层Dao接口
 */
public interface CheckGroupDao {
    void add(CheckGroup checkGroup);
    void setCheckGroupAndCheckItem(Map map);
    public Page<CheckGroup> selectByCondition(String queryString);
    CheckGroup findById(Integer id);
    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);
    void deleteAssociation(Integer id);
    void edit(CheckGroup checkGroup);
    List<CheckGroup> findAll();
}