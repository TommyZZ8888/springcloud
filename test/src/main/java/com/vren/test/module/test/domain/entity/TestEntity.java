package com.vren.test.module.test.domain.entity;


import com.vren.common.common.annotation.EnableUserInfo;
import com.vren.common.common.annotation.UserInfo;
import com.vren.common.module.identity.user.domain.entity.UserInfoEntity;
import com.vren.test.module.test.domain.enums.TestEnums;
import lombok.Data;
import org.joda.money.Money;

import java.util.Date;
import java.util.List;

@Data
@EnableUserInfo
public class TestEntity {

    private String testId;

    private String test2Id;

    private Date updateTime;

    private Date createTime;

    private String userId;

    private String optUserId;

    @UserInfo
    private UserInfoEntity userInfo;

    @UserInfo(field = "optUserId")
    private UserInfoEntity optUserInfo;

    private Test2Entity test2;

    private List<Test3Entity> test3;


    private Money money;

    private TestEnums sex;
}
