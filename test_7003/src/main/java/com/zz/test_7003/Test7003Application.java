package com.zz.test_7003;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;



@SpringBootApplication
@EnableFeignClients(basePackages ={"com.zz.common.module", "com.zz.feign"})
public class Test7003Application {

    public static void main(String[] args) {
        SpringApplication.run(Test7003Application.class, args);
    }

}
