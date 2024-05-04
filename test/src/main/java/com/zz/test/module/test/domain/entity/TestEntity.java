package com.zz.test.module.test.domain.entity;



import lombok.Data;
import org.joda.money.Money;

import java.util.Date;
import java.util.List;

@Data
public class TestEntity {

    private String testId;

    private String test2Id;

    private Date updateTime;

    private Date createTime;

    private String userId;

    private String optUserId;

}
