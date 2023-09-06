package com.zz.common.common.core.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.zz.common.common.parser.EnumsSerializer;

import java.util.HashMap;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@JsonDeserialize(using = EnumsSerializer.JacksonDeserializer.class)
public interface BaseEnumsInterface {

    String getValue();

    String getName();

    default void extraParameter(HashMap<String, String> map) {
    }

}
