//package com.zz.common.common.filter;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//
///**
// * @ClassName:UrlFilter
// * @Description:
// * @Author: com.vren
// * @Date: 2022/7/7 17:27
// */
//@Order(1)
//@Component
//@WebFilter(filterName = "urlFilter", urlPatterns = "/api/*")
//public class UrlFilter implements Filter {
//
//    @Value("${spring.application.name}")
//    private String applicationName;
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        RequestCacheWrapper requestCacheWrapper = new RequestCacheWrapper((HttpServletRequest) request);
//        String servletPath = requestCacheWrapper.getServletPath();
//        String prefix = "/api/" + applicationName;
//        if (StringUtils.isNotBlank(servletPath) && servletPath.startsWith(prefix)) {
//            String newServletPath = servletPath.substring(prefix.length());
//            requestCacheWrapper.getRequestDispatcher(newServletPath).forward(requestCacheWrapper, response);
//        } else {
//            chain.doFilter(requestCacheWrapper, response);
//        }
//    }
//
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
//    }
//
//    @Override
//    public void destroy() {
//        Filter.super.destroy();
//    }
//}
