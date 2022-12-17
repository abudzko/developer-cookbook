package com.budzko.cookbook.springboot.kafka.msg;

import com.budzko.cookbook.springboot.kafka.utils.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

public class MsgUtils {


    public static Msg create(String data) {
        Msg msg = new Msg();
        msg.setData(data);
        return msg;
    }

    public static String toStr(Msg msg) throws JsonProcessingException {
        return JsonUtils.toStr(msg);
    }
}
