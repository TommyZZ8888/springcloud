package com.zz.auth.service;

import com.zz.auth.dao.UserMapper;
import com.zz.common.module.identity.user.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Describtion: UserDetailService
 * @Author: 张卫刚
 * @Date: 2024/4/15 21:13
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectByUserName(username);
        if (user != null) {
            //返回oauth2的用户
            return new org.springframework.security.core.userdetails.User(
                    user.getUserName(),
                    user.getPassWord(),
                    AuthorityUtils.createAuthorityList(user.getRole())
            );
        } else {
            throw new UsernameNotFoundException("用户不存在");
        }
    }

}
