package com.vren.common.common.utils;


import com.vren.common.module.identity.user.domain.entity.UserInfoEntity;

public class ThreadLocalUser {

    private ThreadLocalUser() {
    }


    private static final ThreadLocal<UserInfoEntity> USER_INFO_DAO_THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 清除用户信息
     */
    public static void clear() {
        USER_INFO_DAO_THREAD_LOCAL.remove();
    }

    /**
     * 存储用户信息
     */
    public static void set(UserInfoEntity userInfoEntity) {
        USER_INFO_DAO_THREAD_LOCAL.set(userInfoEntity);
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public static UserInfoEntity get() {
        return USER_INFO_DAO_THREAD_LOCAL.get();
    }

}
