package com.zz.common.common.core.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class OrderItem {
    private String column;
    private boolean asc = true;

    public String getIsAsc() {
        if (asc) {
            return "ASC";
        }
        return "DESC";
    }
}
