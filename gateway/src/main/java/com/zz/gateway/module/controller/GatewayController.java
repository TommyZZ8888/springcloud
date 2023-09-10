package com.zz.gateway.module.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Describtion: GatewayController
 * @Author: 张卫刚
 * @Date: 2023/9/6 21:45
 */
@RestController
@RequestMapping("/gateway")
@RefreshScope
public class GatewayController {

    @Value("${user.name}")
    private String name;
    @Value("${user.age}")
    private String age;

    @PostMapping(value = "/test")
    public String test() {
        return "ok";
    }


    @RequestMapping(value = "/login")
    public String login() {
        String msg = " I am " + name + " , I am " + age + " years old!";
        System.out.println(msg);
        return msg;
    }

}
