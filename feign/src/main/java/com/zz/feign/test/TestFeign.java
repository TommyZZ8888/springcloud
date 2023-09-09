package com.zz.feign.test;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Describtion: TestService
 * @Author: 张卫刚
 * @Date: 2023/9/9 9:43
 */
@FeignClient(value = "test")
public interface TestService {

    @RequestMapping(value = "/test/login", method = RequestMethod.GET)
     String login();
}
