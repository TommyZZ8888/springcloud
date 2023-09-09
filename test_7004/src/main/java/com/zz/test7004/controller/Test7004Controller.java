package com.zz.test7004.controller;

import com.zz.common.common.annotation.NoNeedLogin;
import com.zz.common.common.annotation.OperateLog;
import com.zz.feign.test.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@OperateLog
@NoNeedLogin
@RefreshScope
public class Test7004Controller {


    @Autowired
    private TestService testService;

    @Value("${user.name}")
    private String name;
    @Value("${user.age}")
    private String age;

    @RequestMapping("/login")
    public String login() {
        String msg = " I am " + name + " , I am " + age + " years old!";
        System.out.println(msg);
        return msg;
    }


    @RequestMapping(value = "/loginFeignTest")
    public String loginFeign() {
        String msg = testService.get();
        System.out.println(msg);
        return msg;
    }
}
