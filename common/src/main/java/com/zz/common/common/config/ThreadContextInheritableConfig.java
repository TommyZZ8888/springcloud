//package com.zz.common.common.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.RequestContextFilter;
//import org.springframework.web.servlet.DispatcherServlet;
//
//
//import javax.annotation.PostConstruct;
//
///**
// * @ClassName:ThreadContextInheritableConfig
// * @Author: com.vren
// * @Date: 2022/11/22 9:25
// */
//@Component
//public class ThreadContextInheritableConfig {
//
//    @Autowired
//    private RequestContextTestFilter requestContextFilter;
//
//
//    @Autowired
//    private DispatcherTestServlet dispatcherServlet;
//
//    @PostConstruct
//    public void init() {
//        requestContextFilter.setThreadContextInheritable(true);
//        dispatcherServlet.setThreadContextInheritable(true);
//    }
//}
