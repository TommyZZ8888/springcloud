package com.zz.test.module.test.domain.dto;


import lombok.Data;
import org.joda.money.Money;

import java.util.Date;

@Data
public class TestDTO  {



    private String testId;

    private Money money;

    private Date date;
}
