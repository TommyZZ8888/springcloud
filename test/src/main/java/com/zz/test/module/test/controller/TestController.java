package com.zz.test.module.test.controller;


import com.zz.feign.test7003.Test7003Service;
import com.zz.test.module.test.domain.dto.UserDTO;
import com.zz.test.module.test.holder.LoginUserHolder;
import com.zz.test.module.test.mapper.TestMapper;
import com.zz.test.module.test.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping

@RefreshScope
@Api("test")
public class TestController {

    @Autowired
    private TestMapper testMapper;

    @Autowired
    private TestService testService;

    @Autowired
    private LoginUserHolder loginUserHolder;

    @Autowired
    private Test7003Service test7003Service;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
    @GetMapping("/currentUser")
    public UserDTO currentUser() {
        return loginUserHolder.getCurrentUser();
    }

    @GetMapping("/feignTest7003")
    public String feignTest7003() {
        return test7003Service.get();
    }


    //引入@RefreshScope注解，值从配置文件获取
//    @Value("${user.name}")
    private String name;
//    @Value("${user.age}")
    private String age;
//    @Value(value = "${user.sex}")
    private String sex;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation("测试接口")
    public String login() {
        String msg = " I am " + name + " , I am " + age + " years old!,sex: "+sex;
        System.out.println(msg);
        return msg;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update() {
       testService.update();
       return "ok";
    }
}
