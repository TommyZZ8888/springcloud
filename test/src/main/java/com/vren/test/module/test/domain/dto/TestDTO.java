package com.vren.test.module.test.domain.dto;

import com.vren.common.common.core.domain.PageParam;
import com.vren.test.module.test.domain.enums.TestEnums;
import lombok.Data;
import org.joda.money.Money;

import java.util.Date;

@Data
public class TestDTO extends PageParam {

    private TestEnums sex;

    private String testId;

    private Money money;

    private Date date;
}
