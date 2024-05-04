package com.zz.test.module.test.service;

import com.zz.test.module.test.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    }


}
