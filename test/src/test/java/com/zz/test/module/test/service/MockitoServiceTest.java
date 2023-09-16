package com.zz.test.module.test.service;

import com.google.common.base.Verify;
import com.zz.common.common.utils.BeanUtils;
import com.zz.test.module.test.domain.dto.TestDTO;
import com.zz.test.module.test.domain.entity.TestEntity;
import com.zz.test.module.test.mapper.TestMapper;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.context.annotation.Primary;

import javax.validation.ValidationException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description MockitoServiceTest
 * @Author 张卫刚
 * @Date Created on 2023/9/15
 */
class MockitoServiceTest {


    //    @Mock
    @Spy
    @InjectMocks
    MockitoService mockitoService;
    AutoCloseable autoCloseable;

    @Mock
    private TestMapper testMapper;

    @BeforeEach
    void setup() {
        autoCloseable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void finallyClose() throws Exception {
        autoCloseable.close();
    }


    @Test
    void add() {
        Random random = Mockito.mock(Random.class);
        Mockito.when(random.nextInt()).thenReturn(100);
        Assertions.assertEquals(100, random.nextInt());

        int a = 100;
        int b = 200;
        Mockito.when(mockitoService.add(a, b)).thenCallRealMethod();
        int add = mockitoService.add(a, b);
        Assertions.assertEquals(300, add);
    }

    @Test
    void updateById() {
        String id = "138138138138";

        try {
            Mockito.when(testMapper.selectById(id)).thenReturn(null);
            mockitoService.updateById(id);

            Assertions.fail("there will fail");
        } catch (Exception e) {
            Assertions.assertTrue(e instanceof ValidationException);
        }
        TestEntity testEntity = new TestEntity();
        testEntity.setTestId("ssssssssss");
        Mockito.when(testMapper.selectById(id)).thenReturn(testEntity);

        String optUserId = "ooo";
        testEntity.setOptUserId(optUserId);
        MockedStatic<BeanUtils> mockedStatic = Mockito.mockStatic(BeanUtils.class);

        TestDTO testDTO = new TestDTO();
        mockedStatic.when(() -> BeanUtils.copy(testEntity, TestDTO.class)).thenReturn(testDTO);
        Mockito.when(testMapper.update(testDTO)).thenReturn(1);
        Assertions.assertEquals(1, mockitoService.updateById(id));

    }
}