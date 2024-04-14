package com.zz.test.module.test.service;

import com.zz.test.module.test.domain.entity.TestEntity;
import com.zz.test.module.test.domain.enums.TestEnums;
import com.zz.test.module.test.mapper.TestMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Describtion: TestService
 * @Author: 张卫刚
 * @Date: 2023/9/26 20:19
 */
@Service
public class TestService {

    @Autowired
    private TestMapper testMapper;


    public void update() {
        List<TestEntity> testEntities = testMapper.find(null);
        testEntities.forEach(item -> item.setSex(TestEnums.FEMALE));
        testMapper.updateBatch(testEntities);
    }


}
