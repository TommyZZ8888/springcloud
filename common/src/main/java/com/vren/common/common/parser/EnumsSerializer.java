package com.vren.common.common.parser;

import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.reader.ObjectReader;
import com.alibaba.fastjson2.writer.ObjectWriter;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.vren.common.common.core.enums.BaseEnumsInterface;
import com.vren.common.common.utils.EnumUtils;

import java.io.IOException;
import java.lang.reflect.Type;

public class EnumsSerializer {

    public static class JacksonDeserializer extends JsonDeserializer<BaseEnumsInterface> implements ContextualDeserializer {
        private Class<BaseEnumsInterface> type;

        public JacksonDeserializer() {

        }

        public JacksonDeserializer(Class<BaseEnumsInterface> type) {
            this.type = type;
        }

        @Override
        public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
            return new JacksonDeserializer((Class<BaseEnumsInterface>) beanProperty.getType().getRawClass());
        }

        @Override
        public BaseEnumsInterface deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            return EnumUtils.getEnumByValue(type, jsonParser.getValueAsString());
        }
    }


    public static class FastjsonSerializer implements ObjectWriter {
        @Override
        public void write(JSONWriter jsonWriter, Object o, Object o1, Type type, long l) {
            if (o == null) {
                jsonWriter.writeNull();
                return;
            }
            BaseEnumsInterface enums = (BaseEnumsInterface) o;
            jsonWriter.writeString(enums.getValue());
        }
    }

    public static class FastjsonDeserializer implements ObjectReader {

        @Override
        public BaseEnumsInterface readObject(JSONReader jsonReader, Type type, Object o, long l) {
            String text = jsonReader.readString();
            Class<BaseEnumsInterface> rawType = (Class<BaseEnumsInterface>) type;
            return EnumUtils.getEnumByValue(rawType, text);
        }
    }


}
