package com.zz.feign.test;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Describtion: TestFeign
 * @Author: 张卫刚
 * @Date: 2023/9/9 9:43
 */
@FeignClient(value = "127.0.0.1:7002")
public interface TestFeign {

    @RequestMapping(value = "/test/login")
    String login();
}
