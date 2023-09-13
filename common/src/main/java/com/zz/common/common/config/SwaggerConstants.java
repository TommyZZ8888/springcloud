package com.zz.common.common.config;

import java.util.Arrays;
import java.util.List;

/**
 * @Describtion: SwaggerConstants
 * @Author: 张卫刚
 * @Date: 2023/9/13 18:17
 */
public interface SwaggerConstants {
    /**
     * 默认校验KEY
     */
    String ACCESS_TOKEN_KEY = "Authorization";

    /**
     * 默认的排除路径，排除Spring Boot默认的错误处理路径和端点
     */
    List<String> DEFAULT_EXCLUDE_PATH = Arrays.asList("/error", "/actuator/**");

    /**
     * 默认根路径
     */
    String DEFAULT_BASE_PATH = "/**";

    /**
     * 默认包
     */
    String BASE_PACKAGE = "com.zz";
}