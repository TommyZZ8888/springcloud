package com.zz.feign.test7003.fallback;

import com.zz.feign.test7003.Test7003Feign;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Service;

/**
 * @Describtion: TestService
 * @Author: 张卫刚
 * @Date: 2023/9/9 9:48
 */
@Service("test7003ServiceFallback")
@EnableFeignClients
public class Test7003ServiceFallback implements Test7003Feign {

    @Override
    public String login() {
        return "test7003Fallback";
    }
}
