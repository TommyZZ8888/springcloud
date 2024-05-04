package com.zz.test.module.test.holder;


import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONObject;
import com.zz.test.module.test.domain.dto.UserDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Describtion: LoginUserHolder
 * @Author: 张卫刚
 * @Date: 2024/5/2 20:22
 */
@Component
public class LoginUserHolder {

    public UserDTO getCurrentUser(){
        //从Header中获取用户信息
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String userStr = request.getHeader("user");
        JSONObject userJsonObject = new JSONObject(userStr);
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(userJsonObject.getStr("user_name"));
        userDTO.setId(Convert.toLong(userJsonObject.get("id")));
        String authorities =(String) userJsonObject.get("authorities");
        userDTO.setRoles(Collections.singletonList(authorities));
        return userDTO;
    }
}
