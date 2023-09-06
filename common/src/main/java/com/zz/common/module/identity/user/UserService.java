package com.zz.common.module.identity.user;

import com.zz.common.common.core.domain.ResponseResult;
import com.zz.common.common.core.redis.RedisCache;
import com.zz.common.common.core.redis.RedisConstant;
import com.zz.common.module.identity.user.domain.dto.GetUserInfoDTO;
import com.zz.common.module.identity.user.domain.entity.UserInfoEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserFeign userFeign;

    @Resource
    private RedisCache redisCache;


    public UserInfoEntity getUserInfoByIDCache(String keyId) {
        String redisKey = RedisConstant.ALL_USER_LIST;
        Boolean exist = redisCache.hasKey(redisKey);
        if (!exist) {
            synchronized (this) {
                exist = redisCache.hasKey(redisKey);
                if (!exist) {
                    ResponseResult<List<UserInfoEntity>> responseResult = userFeign.getUserBindSelect();
                    Map<String, UserInfoEntity> collect = responseResult.getData().stream().collect(Collectors.toMap(UserInfoEntity::getUserId, Function.identity()));
                    //更新缓存
                    redisCache.deleteObject(redisKey);
                    redisCache.setCacheMap(redisKey, collect);
                    redisCache.expire(redisKey, 2 * 3600);
                }
            }
        }
        return redisCache.getCacheMapValue(redisKey, keyId);
    }

    /**
     * 获取单条的人员信息
     */
    public UserInfoEntity getUserInfoByID(String keyId) {
        GetUserInfoDTO form = new GetUserInfoDTO();
        form.setKeyId(keyId);
        ResponseResult<UserInfoEntity> responseResult = userFeign.getUserInfoDetail(form);
        return responseResult.getData();
    }

}
