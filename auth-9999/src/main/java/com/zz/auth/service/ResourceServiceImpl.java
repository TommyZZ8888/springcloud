package com.zz.auth.service;


import cn.hutool.core.collection.CollUtil;
import com.zz.auth.constants.RedisConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Describtion: 资源与角色匹配关系管理业务类
 * @Author: 张卫刚
 * @Date: 2024/5/3 13:41
 */
@Service
public class ResourceServiceImpl {

    private Map<String, List<String>> resourceRolesMap;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @PostConstruct
    public void initData() {
        resourceRolesMap = new TreeMap<>();
        resourceRolesMap.put("/test/hello", CollUtil.toList("ADMIN"));
        resourceRolesMap.put("/test/user/currentUser", CollUtil.toList("ADMIN", "TEST"));
        resourceRolesMap.put("/test/feignTest7003", CollUtil.toList("ADMIN", "TEST"));
        resourceRolesMap.put("/test7003/login", CollUtil.toList("ADMIN", "TEST"));
        redisTemplate.opsForHash().putAll(RedisConstant.RESOURCE_ROLES_MAP, resourceRolesMap);
    }
}