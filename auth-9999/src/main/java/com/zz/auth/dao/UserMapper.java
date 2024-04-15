package com.zz.auth.dao;

import com.zz.common.module.identity.user.domain.entity.User;
import org.apache.catalina.realm.X509UsernameRetriever;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * @Describtion: UserMapper
 * @Author: 张卫刚
 * @Date: 2024/4/15 20:52
 */
@Mapper
public interface UserMapper {

    User selectByUserName(String username);
}
