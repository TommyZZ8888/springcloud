package com.zz.test7004;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients(basePackages ={"com.zz.common.module", "com.zz.feign"})
@SpringBootApplication
public class Test7004Application {

    public static void main(String[] args) {
        SpringApplication.run(Test7004Application.class, args);
    }

}
