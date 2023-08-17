package com.vren.common.common.utils;

import com.vren.common.common.core.enums.BaseEnumsInterface;

import java.util.Arrays;
import java.util.Objects;

/**
 * @ClassName:EnumUtil
 * @Description:
 * @Author: com.vren
 * @Date: 2022/5/30 13:19
 */
public class EnumUtils {

    public static String getName(Class<? extends BaseEnumsInterface> clazz, String code) {
        if (code == null) {
            return null;
        }
        return Arrays.stream(clazz.getEnumConstants())
                .filter(e -> Objects.equals(e.getValue(), code))
                .findFirst().map(BaseEnumsInterface::getName).orElse(null);
    }

    public static String getValue(Class<? extends BaseEnumsInterface> clazz, String name) {
        if (name == null) {
            return null;
        }
        return Arrays.stream(clazz.getEnumConstants())
                .filter(e -> Objects.equals(e.getName(), name))
                .findFirst().map(BaseEnumsInterface::getValue).orElse(null);
    }

    public static <T extends BaseEnumsInterface> T getEnumByValue(Class<T> clazz, String code) {
        if (code == null) {
            return null;
        }
        return Arrays.stream(clazz.getEnumConstants())
                .filter(e -> Objects.equals(e.getValue(), code))
                .findFirst().orElse(null);
    }
}
