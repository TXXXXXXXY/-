package com.xinhua.controller;

import com.aliyuncs.exceptions.ClientException;
import com.xinhua.constant.MessageConstant;
import com.xinhua.constant.RedisConstant;
import com.xinhua.constant.RedisMessageConstant;
import com.xinhua.entity.Result;
import com.xinhua.utils.SMSUtils;
import com.xinhua.utils.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import java.util.Random;

/**
 * 短信验证码
 */
@RestController
@RequestMapping("/validateCode")
public class ValidateCodeController {
    @Autowired
    private JedisPool jedisPool;

    //体检预约时发送手机验证码
    @RequestMapping("/send4Order")
    public Result send4Order(String telephone){
        Integer code = ValidateCodeUtils.generateValidateCode(4);//生成4位数字验证码
        /*try {
            //发送短信
            SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE,telephone,code.toString());
        } catch (ClientException e) {
            e.printStackTrace();
            //验证码发送失败
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }*/
        System.out.println("发送的手机验证码为：" + code);
        //将生成的验证码缓存到redis
        jedisPool.getResource().setex(
                telephone + RedisMessageConstant.SENDTYPE_ORDER,5 * 60,code.toString());
        //验证码发送成功
        return new Result(true,MessageConstant.SEND_VALIDATECODE_SUCCESS);
    }

    //手机快速登录时发送手机验证码
    @RequestMapping("/send4Login")
    public Result send4Login(String telephone){
        Integer code = ValidateCodeUtils.generateValidateCode(6);//生成6位数字验证码
        /*try {
            //发送短信
            *//*SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE,telephone,code.toString());*//*
        } catch (ClientException e) {
            e.printStackTrace();
            //验证码发送失败
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }*/
        System.out.println("发送的手机验证码为：" + code);
        //将生成的验证码缓存到redis
        jedisPool.getResource().setex(telephone+RedisMessageConstant.SENDTYPE_LOGIN,
                5 * 60,
                code.toString());
        //验证码发送成功
        return new Result(true,MessageConstant.SEND_VALIDATECODE_SUCCESS);
    }
}