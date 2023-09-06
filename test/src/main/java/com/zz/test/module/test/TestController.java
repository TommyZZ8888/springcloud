package com.zz.test.module.test;

import com.zz.common.common.annotation.NoNeedLogin;
import com.zz.common.common.annotation.OperateLog;
import com.zz.common.common.core.controller.BaseController;
import com.zz.common.common.core.domain.PageResult;
import com.zz.common.common.core.domain.ResponseResult;
import com.zz.test.module.test.domain.dto.TestDTO;
import com.zz.test.module.test.domain.entity.TestEntity;
import com.zz.test.module.test.mapper.TestMapper;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
@OperateLog
@NoNeedLogin
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

}
