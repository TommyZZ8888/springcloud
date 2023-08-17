package com.vren.common.common.core.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName:SwaggerController
 * @Description:
 * @Author: vren
 * @Date: 2022/6/16 13:13
 */
@Slf4j
@RestController
@RequestMapping("/doc")
public class SwaggerController extends BaseController {

    @GetMapping("/${spring.application.name}/swagger.json")
    public void swaggerJson(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher("/v3/api-docs").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
