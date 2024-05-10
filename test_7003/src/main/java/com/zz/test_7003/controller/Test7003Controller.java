package com.zz.test_7003.controller;

import com.zz.common.common.annotation.NoNeedLogin;
import com.zz.common.common.annotation.OperateLog;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test7003")
@OperateLog
@NoNeedLogin
@RefreshScope
public class Test7003Controller  {



    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login() {
        return "test7003";
    }



}
