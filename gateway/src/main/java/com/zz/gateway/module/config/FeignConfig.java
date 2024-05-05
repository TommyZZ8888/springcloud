//package com.zz.gateway.module.config;
//
//import com.zz.common.common.utils.InheritableThreadLocalHeader;
//import feign.Logger;
//import feign.RequestInterceptor;
//import feign.RequestTemplate;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.HashMap;
//
//@Configuration
//public class FeignConfig implements RequestInterceptor {
//
//    @Bean
//    public Logger.Level feignLoggerLevel() {
//        return Logger.Level.FULL;
//    }
//
//    //转发header
//    @Override
//    public void apply(RequestTemplate requestTemplate) {
////        try {
////            HashMap<String, String> headers = InheritableThreadLocalHeader.get();
////            if (headers != null) {
////                for (String headerName : headers.keySet()) {
////                    requestTemplate.header(headerName, headers.get(headerName));
////                }
////            }
//////            if (requestTemplate.feignTarget().url().equals(String.format("http://%s", requestTemplate.feignTarget().name()))) {
//////                requestTemplate.target(SystemConfig.getGatewayUrl());
//////            }
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//    }
//
//
//}
