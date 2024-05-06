package com.zz.test_7003;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;



@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class Test7003Application {

    public static void main(String[] args) {
        SpringApplication.run(Test7003Application.class, args);
    }

}
