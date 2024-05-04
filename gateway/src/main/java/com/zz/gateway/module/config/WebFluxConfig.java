//package com.zz.gateway.module.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.codec.ServerCodecConfigurer;
//import org.springframework.http.codec.json.Jackson2JsonDecoder;
//import org.springframework.http.codec.json.Jackson2JsonEncoder;
//import org.springframework.web.reactive.config.WebFluxConfigurer;
//
///**
// * @Describtion: WebFluxConfig
// * @Author: 张卫刚
// * @Date: 2023/9/9 17:41
// */
//@Configuration
//public class WebFluxConfig implements WebFluxConfigurer {
//
//    @Override
//    public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
//        configurer.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder());
//        configurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder());
//    }
//
//    @Bean
//    public ServerCodecConfigurer serverCodecConfigurer() {
//        return ServerCodecConfigurer.create();
//    }
//}
