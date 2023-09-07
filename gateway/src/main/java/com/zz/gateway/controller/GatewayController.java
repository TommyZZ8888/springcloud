package com.zz.gateway.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Describtion: GatewayController
 * @Author: 张卫刚
 * @Date: 2023/9/6 21:45
 */
@RestController
@RequestMapping("/config")
@RefreshScope
public class GatewayController {

    //引入@RefreshScope注解，值从配置文件获取
    @Value("${user.name}")
    private String name;
    @Value("${user.age}")
    private String age;

    //登录
    @RequestMapping("/login")
    public String login(){

        String msg =" I am " + name + " , I am " + age + " years old!";
        System.out.println(msg);
        return msg;}
}
