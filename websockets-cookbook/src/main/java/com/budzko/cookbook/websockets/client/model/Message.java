package com.budzko.cookbook.websockets.client.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Message {
    private String senderId;
    private ChannelType channelType;
    private String channelId;
    private String data;

    public static Message generateRandomMessage() {
        Message message = new Message();
        message.setData(UUID.randomUUID().toString());
        message.setSenderId(UUID.randomUUID().toString());
        message.setChannelId(UUID.randomUUID().toString());
        message.setChannelType(ChannelType.CHAT);
        return message;
    }
}
