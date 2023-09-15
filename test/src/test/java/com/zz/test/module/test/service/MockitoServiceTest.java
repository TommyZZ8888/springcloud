package com.zz.test.module.test.service;

import com.zz.test.module.test.mapper.TestMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.context.annotation.Primary;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description MockitoServiceTest
 * @Author 张卫刚
 * @Date Created on 2023/9/15
 */
class MockitoServiceTest {


    @Mock
    MockitoService mockitoService;

    @Mock
    TestMapper testMapper;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void twoNumberAdd() {
        Random random = Mockito.mock(Random.class);
        Mockito.when(random.nextInt()).thenReturn(100);
        Assertions.assertEquals(100, random.nextInt());

        int a = 100;
        int b = 200;
        int add = mockitoService.add(a, b);
        Assertions.assertEquals(300,add);
    }

    @Test
    void updateById() {
        String
    }
}