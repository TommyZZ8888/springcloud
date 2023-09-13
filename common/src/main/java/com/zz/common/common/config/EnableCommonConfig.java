package com.zz.common.common.config;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.oas.annotations.EnableOpenApi;

@Configuration
@ComponentScan("com.zz")
@MapperScan("com.zz.*.module.**.mapper")
@EnableAsync
@EnableDiscoveryClient
@Slf4j
@EnableOpenApi
public class EnableCommonConfig implements EnvironmentAware {
    @Override
    public void setEnvironment(Environment environment) {
        log.info("Init Common");
    }
}
