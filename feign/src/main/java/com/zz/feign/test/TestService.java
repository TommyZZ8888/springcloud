package com.zz.feign.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Describtion: TestService
 * @Author: 张卫刚
 * @Date: 2023/9/9 9:48
 */
@Service
public class TestService {

    @Autowired
    private TestFeign testFeign;

    public String get(){
        String login = testFeign.login();
        System.out.println(login);
       return login;
    }
}
