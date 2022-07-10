package com.xinhua.dao;

import com.xinhua.pojo.OrderSetting;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface OrderSettingDao {
    public void add(OrderSetting orderSetting);
    public void editNumberByOrderDate(OrderSetting orderSetting);
    public long findCountByOrderDate(Date orderDate);
    public List<OrderSetting> getOrderSettingByMonth(Map date);
    //根据预约日期查询预约设置信息
    public OrderSetting findByOrderDate(Date orderDate);
    public void editReservationsByOrderDate(OrderSetting orderSetting);

}
