package com.zz.feign.test7003;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Service;

/**
 * @Describtion: TestService
 * @Author: 张卫刚
 * @Date: 2023/9/9 9:48
 */
@Service("feignTest7003Service")
@EnableFeignClients
public class FeignTest7003Service {

    @Autowired
    private Test7003Feign test7003Feign;

    public String get(){
        String login = test7003Feign.login();
        System.out.println(login);
       return login;
    }
}
