package com.zz.test.module.test.service;

import com.zz.common.common.utils.BeanUtils;
import com.zz.common.common.utils.StringUtils;
import com.zz.test.module.test.domain.dto.TestDTO;
import com.zz.test.module.test.domain.entity.TestEntity;
import com.zz.test.module.test.mapper.TestMapper;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.ArrayList;

/**
 * @Describtion: MockitoService
 * @Author: 张卫刚
 * @Date: 2023/9/15 7:38
 */
@Service
public class MockitoService {

    @Autowired
    private TestMapper testMapper;


    public void updateById(String id) {
        TestEntity testEntity = testMapper.selectById(id);
        if (Comm)
        testEntity.setOptUserId("aaa");
        TestDTO testDTO = BeanUtils.copy(testEntity, TestDTO.class);
        int update = testMapper.update(testDTO);
    }


    public int add(int a, int b) {
        return a + b;
    }
}
