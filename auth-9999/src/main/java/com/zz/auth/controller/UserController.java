package com.zz.auth.controller;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.KeyPair;
import java.security.Principal;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

/**
 * @Describtion: UserController
 * @Author: 张卫刚
 * @Date: 2024/4/15 21:19
 */
@RestController
@RequestMapping("/user")
public class UserController {


    /**
     * 获取授权的用户信息
     * @param principal 当前用户
     * @return 授权信息
     */
     @GetMapping("/current/get")
    public Principal currentUser(Principal principal){
         return principal;
     }
}
