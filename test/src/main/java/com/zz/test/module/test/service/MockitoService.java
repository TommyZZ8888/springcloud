package com.zz.test.module.test.service;

import com.zz.common.common.utils.StringUtils;
import com.zz.test.module.test.domain.entity.TestEntity;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
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



    public void getByNameAndPhone(String name,String phone){

        if (StringUtils.isBlank(name)){
            throw new ValidationException("name is blank");
        }

        if (StringUtils.isBlank(phone)){
            throw new ValidationException("phone is blank");
        }

    }
}
