package com.zz.common.common.parser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.zz.common.common.annotation.EnableUserInfo;
import com.zz.common.common.annotation.UserInfo;
import com.zz.common.common.core.domain.UserInfoEntity;
import com.zz.common.common.utils.ApplicationContextUtils;
//import com.zz.feign.test.FeignTestService;


import java.lang.reflect.Field;

public class UserInfoSerializer {

    public static class JacksonSerializer extends JsonSerializer<Object> {

//        private FeignTestService userService;
//
//        public JacksonSerializer() {
//            userService = ApplicationContextUtils.getBean(FeignTestService.class);
//        }

        @Override
        public void serialize(Object object, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            Class<?> objectClass = object.getClass();
            EnableUserInfo annotation = objectClass.getAnnotation(EnableUserInfo.class);
            if (annotation == null) {
                return;
            }
            try {
                Field[] declaredFields = objectClass.getDeclaredFields();
                jsonGenerator.writeStartObject();
                for (Field declaredField : declaredFields) {
                    String propertyName = declaredField.getName();
                    UserInfo userInfoAnnotation = declaredField.getAnnotation(UserInfo.class);
                    if (userInfoAnnotation == null || !declaredField.getType().isAssignableFrom(UserInfoEntity.class)) {
                        declaredField.setAccessible(true);
                        jsonGenerator.writeObjectField(propertyName, declaredField.get(object));
                        continue;
                    }
                    String fieldName = userInfoAnnotation.field();
                    Field field = objectClass.getDeclaredField(fieldName);
                    field.setAccessible(true);
                    String fieldValue = (String) field.get(object);
                    if (fieldValue == null || "".equals(fieldValue)) {
                        continue;
                    }
//                    UserInfoEntity userInfoEntity = userService.getUserInfoByIDCache(fieldValue);
//                    jsonGenerator.writeObjectField(propertyName, userInfoEntity);
                }
                jsonGenerator.writeEndObject();
            } catch (Exception ignored) {
            }
        }
    }
}
