package com.zz.test.module.test;

import com.zz.common.common.annotation.NoNeedLogin;
import com.zz.common.common.annotation.OperateLog;
import com.zz.common.common.core.controller.BaseController;
import com.zz.common.common.core.domain.PageResult;
import com.zz.common.common.core.domain.ResponseResult;
import com.zz.feign.test.TestService;
import com.zz.test.module.test.domain.dto.TestDTO;
import com.zz.test.module.test.domain.entity.TestEntity;
import com.zz.test.module.test.mapper.TestMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
@OperateLog
@NoNeedLogin
@RefreshScope
@Api("test")
public class TestController extends BaseController {

    @Autowired
    private TestMapper testMapper;

    @GetMapping("/test")
    public ResponseResult<PageResult<TestEntity>> test(@RequestBody TestDTO dto) {
        startPage();
        List<TestEntity> testEntities = testMapper.find(null);
        return getDataTable(testEntities);
    }

    @RequestMapping(value = "/select", method = RequestMethod.POST)
    public ResponseResult<PageResult<TestEntity>> select(@RequestBody TestDTO dto) {
        startPage();
        List<TestEntity> testEntities = testMapper.select(dto);
        return getDataTable(testEntities);
    }

    @PostMapping("/test2")
    public ResponseResult<Boolean> test2(@RequestBody TestEntity testEntity) {
        testMapper.insert(testEntity);
        return ResponseResult.success("操作成功");
    }

    @GetMapping("/test1")
    public ResponseResult<List<TestEntity>> test1() {
        TestEntity testEntity = new TestEntity();
        testEntity.setMoney(Money.of(CurrencyUnit.of("CNY"), 100));
        ArrayList<TestEntity> userInfoEntities = new ArrayList<>();
        userInfoEntities.add(testEntity);
        return ResponseResult.success("成功1", userInfoEntities);
    }


    @PostMapping("/returnTest")
    public List<TestEntity> returnTest() {
        TestEntity testEntity = new TestEntity();
        testEntity.setMoney(Money.of(CurrencyUnit.of("CNY"), 100));
        ArrayList<TestEntity> userInfoEntities = new ArrayList<>();
        userInfoEntities.add(testEntity);
        return userInfoEntities;
    }

    //引入@RefreshScope注解，值从配置文件获取
    @Value("${user.name}")
    private String name;
    @Value("${user.age}")
    private String age;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @NoNeedLogin
    @ApiOperation("测试接口")
    public String login() {
        String msg = " I am " + name + " , I am " + age + " years old!";
        System.out.println(msg);
        return msg;
    }
}
