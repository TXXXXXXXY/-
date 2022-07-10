package com.xinhua.service;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xinhua.dao.CheckItemDao;
import com.xinhua.entity.PageResult;
import com.xinhua.entity.QueryPageBean;
import com.xinhua.pojo.CheckItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 检查项服务
 */
@Service(interfaceClass = CheckItemService.class)
@Transactional
public class CheckItemServiceImpl implements CheckItemService {
    @Autowired
    private CheckItemDao checkItemDao;
    //新增
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }
    //分页
    public PageResult pageQuery(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();

        PageHelper.startPage(currentPage,pageSize);
        Page<CheckItem> page = checkItemDao.selectByCondition(queryString);
        long total = page.getTotal();
        List<CheckItem> rows = page.getResult();
        return new PageResult(total,rows);
    }
    //删除
    public void delete(Integer id) throws RuntimeException{
        //查询当前检查项是否和检查组关联
        long count = checkItemDao.findCountByCheckItemId(id);
        if(count > 0){
            //当前检查项被引用，不能删除
            throw new RuntimeException("当前检查项被引用，不能删除");
        }
        checkItemDao.deleteById(id);
    }
    //编辑
    public void edit(CheckItem checkItem) {
        checkItemDao.edit(checkItem);
    }
    public CheckItem findById(Integer id) {
        return checkItemDao.findById(id);
    }
    public List<CheckItem> findAll() {
        return checkItemDao.findAll();
    }
}