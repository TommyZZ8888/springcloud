package com.vren.common.common.parser;

import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.reader.ObjectReader;
import com.alibaba.fastjson2.writer.ObjectWriter;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @ClassName:Jackson2MoneySerializer
 * @Description:
 * @Author: vren
 * @Date: 2023/4/22 19:08
 */
public class MoneySerializer {

    public static class JacksonSerializer extends JsonSerializer<Money> {
        @Override
        public void serialize(Money money, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeString(money.getAmount().toPlainString());
        }

        @Override
        public Class<Money> handledType() {
            return Money.class;
        }
    }

    public static class JacksonDeserializer extends JsonDeserializer<Money> {
        @Override
        public Money deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            String text = jsonParser.getText();
            Money money = Money.of(CurrencyUnit.of("CNY"), 0);
            if (StringUtils.isNotBlank(text) && NumberUtils.isNumber(text)) {
                money = Money.of(CurrencyUnit.of("CNY"), Double.parseDouble(text));
            }
            return money;
        }
    }

    public static class FastjsonSerializer implements ObjectWriter<Money> {
        @Override
        public void write(JSONWriter jsonWriter, Object o, Object o1, Type type, long l) {
            if (o == null) {
                jsonWriter.writeNull();
                return;
            }
            Money money = (Money) o;
            jsonWriter.writeString(money.getAmount().toPlainString());
        }
    }

    public static class FastjsonDeserializer implements ObjectReader<Money> {
        @Override
        public Money readObject(JSONReader jsonReader, Type type, Object o, long l) {
            String text = jsonReader.readString();
            Money money = Money.of(CurrencyUnit.of("CNY"), 0);
            if (StringUtils.isNotBlank(text) && NumberUtils.isNumber(text)) {
                money = Money.of(CurrencyUnit.of("CNY"), Double.parseDouble(text));
            }
            return money;
        }
    }
}
