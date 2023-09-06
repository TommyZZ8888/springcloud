package com.zz.gateway.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Describtion: GatewayController
 * @Author: 张卫刚
 * @Date: 2023/9/6 21:45
 */
@RestController
@RequestMapping("/config")
@RefreshScope
public class GatewayController {

   @Value("${useLocalCache}")
    private boolean useLocalCache;

    @RequestMapping("/get")
    public boolean get() {
        return useLocalCache;
    }
}
