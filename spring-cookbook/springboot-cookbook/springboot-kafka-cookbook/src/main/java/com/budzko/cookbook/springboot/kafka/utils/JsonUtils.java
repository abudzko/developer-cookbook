package com.budzko.cookbook.springboot.kafka.utils;

import com.budzko.cookbook.springboot.kafka.msg.Msg;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String toStr(Msg msg) throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsString(msg);
    }
}
