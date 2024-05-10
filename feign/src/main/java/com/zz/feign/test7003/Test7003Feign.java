package com.zz.feign.test7003;

import com.zz.feign.test7003.fallback.Test7003ServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Describtion: TestFeign
 * @Author: 张卫刚
 * @Date: 2023/9/9 9:43
 */
@FeignClient(value = "test7003", url = "127.0.0.1:7003",fallback = Test7003ServiceFallback.class)
public interface Test7003Feign {

    @RequestMapping(value = "/test7003/login", method = RequestMethod.POST)
    String login();
}
