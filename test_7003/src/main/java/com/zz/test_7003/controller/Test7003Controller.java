package com.zz.test_7003.controller;

import com.zz.common.common.annotation.NoNeedLogin;
import com.zz.common.common.annotation.OperateLog;
import com.zz.common.common.core.controller.BaseController;
import com.zz.feign.test.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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


    @Autowired
    private TestService testService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login() {

        return "test7003";
    }


    @RequestMapping(value = "/loginFeignTest",method = RequestMethod.POST)
    @NoNeedLogin
    public String loginFeign() {
        String msg = testService.get();
        System.out.println(msg);
        return msg;
    }
}
