package com.vren.test.module.test.domain.enums;

import com.vren.common.common.core.enums.BaseEnumsInterface;

import java.util.HashMap;

public enum TestEnums implements BaseEnumsInterface {
    MALE("1", "男性"),
    FEMALE("2", "女性");

    private String value;

    private String name;

    TestEnums(String value, String name) {
        this.value = value;
        this.name = name;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void extraParameter(HashMap<String, String> map) {
        map.put("test", this.toString());
    }


}