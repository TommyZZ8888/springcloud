package com.zz.common.module.system.login;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.zz.common.common.exception.ErrorException;
import com.zz.common.module.identity.user.domain.entity.UserInfoEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class LoginServer {

    public static final String AUTH_HEADER_KEY = "Authorization";

    public static final String TOKEN_PREFIX = "Bearer ";

    public UserInfoEntity checkToken(String token) {
        DecodedJWT decode = JWT.decode(token);
        String id = decode.getClaim("id").asString();
        Date expiresAt = decode.getExpiresAt();
        boolean expiration = expiresAt.before(new Date());
        if (expiration) {
            throw new ErrorException("登录已过期");
        }
        //调试用，打印出来
        UserInfoEntity userInfo = new UserInfoEntity();
        userInfo.setUserId(id);
        return userInfo;
    }
}
