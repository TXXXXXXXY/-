package com.xinhua.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.aliyuncs.exceptions.ClientException;
import com.xinhua.constant.MessageConstant;
import com.xinhua.constant.RedisConstant;
import com.xinhua.constant.RedisMessageConstant;
import com.xinhua.entity.Result;
import com.xinhua.pojo.Member;
import com.xinhua.pojo.Order;
import com.xinhua.pojo.Setmeal;
import com.xinhua.service.OrderService;
import com.xinhua.utils.SMSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 体检预约
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Reference
    private OrderService orderService;
    @Autowired
    private JedisPool jedisPool;

    /**
     * 体检预约
     * @param map
     * @return
     */
    @RequestMapping("/submit")
    public Result submitOrder(@RequestBody Map map){
        String telephone = (String) map.get("telephone");
        //从Redis中获取缓存的验证码，key为手机号+RedisConstant.SENDTYPE_ORDER
        String codeInRedis = jedisPool.getResource().get(
                telephone + RedisMessageConstant.SENDTYPE_ORDER);
        String validateCode = (String) map.get("validateCode");
        //校验手机验证码
        if(codeInRedis == null || !codeInRedis.equals(validateCode)){
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }
        Result result =null;
        //调用体检预约服务
        try{
            map.put("orderType", Order.ORDERTYPE_WEIXIN);
            result = orderService.order(map);
        }catch (Exception e){
            e.printStackTrace();
            //预约失败
            return result;
        }
        if(result.isFlag()){
            //预约成功，发送短信通知
            String orderDate = (String) map.get("orderDate");
           /* try {
                SMSUtils.sendShortMessage(SMSUtils.ORDER_NOTICE,telephone,orderDate);
            } catch (ClientException e) {
                e.printStackTrace();
            }*/
        }
        return result;
    }
    /**
     * 根据id查询预约信息，包括套餐信息和会员信息
     * @param id
     * @return
     */
    @RequestMapping("/findById")
    public Result findById(Integer id){
        try{
            Map map = orderService.findById(id);
            //查询预约信息成功
            return new Result(true,MessageConstant.QUERY_ORDER_SUCCESS,map);
        }catch (Exception e){
            e.printStackTrace();
            //查询预约信息失败
            return new Result(false,MessageConstant.QUERY_ORDER_FAIL);
        }
    }
    @RequestMapping("/getOrder")
    public Result getOrder(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        String phone =null;
        List<Order> orders = new ArrayList<>();
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("login_member_telephone"));
            {
                phone = cookie.getValue();
            }
        }
        try {
            if(null!=phone){
                orders=orderService.getOrders(phone);
                return new Result(true,"success",orders);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "false");
        }
        return new Result(false, "false");
    }
}