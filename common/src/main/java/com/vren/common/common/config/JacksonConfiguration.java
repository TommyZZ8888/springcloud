package com.vren.common.common.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.key.LocalDateTimeKeyDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.vren.common.common.parser.MoneySerializer;
import org.joda.money.Money;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimeZone;

@Configuration
public class JacksonConfiguration {

    @Bean
    protected ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        //关闭日期序列化为时间戳的功能
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        //关闭序列化的时候没有为属性找到getter方法,报错
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        //关闭反序列化的时候，没有找到属性的setter报错
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        //序列化的时候序列对象的所有属性
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        //反序列化的时候如果多了其他属性,不抛出异常
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //如果是空对象的时候,不抛异常
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        //时区 格式设置
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        objectMapper.setTimeZone(TimeZone.getTimeZone(ZoneId.of("Asia/Shanghai")));

        SimpleModule simpleModule = new SimpleModule();
        //json值序列化
        simpleModule.addSerializer(LocalDateTime.class, LocalDateTimeSerializer.INSTANCE);
        //json值反序列化
        simpleModule.addDeserializer(LocalDateTime.class, LocalDateTimeDeserializer.INSTANCE);
        //json键序列化
        simpleModule.addKeySerializer(LocalDateTime.class, LocalDateTimeSerializer.INSTANCE);
        //json键反序列化
        simpleModule.addKeyDeserializer(LocalDateTime.class, LocalDateTimeKeyDeserializer.INSTANCE);

        simpleModule.addDeserializer(Money.class, new MoneySerializer.JacksonDeserializer());
        simpleModule.addSerializer(new MoneySerializer.JacksonSerializer());

        objectMapper.registerModule(simpleModule);
        return objectMapper;
    }
}