package com.budzko.cookbook.websockets.client.model;

public interface OnMessageListener<T> {
    void onMessage(T message);
}
