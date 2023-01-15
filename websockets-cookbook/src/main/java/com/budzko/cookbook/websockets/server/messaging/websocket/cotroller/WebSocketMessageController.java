package com.budzko.cookbook.websockets.server.messaging.websocket.cotroller;

import com.budzko.cookbook.websockets.client.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.UUID;

@Controller
@Slf4j
@MessageMapping("/app")
public class WebSocketMessageController {

    @MessageMapping("/message")
    @SendTo("/topic/pong")
    public Message handleMessage(Message message, Principal principal) throws Exception {
        log.info(message.toString());
        return createPongMsg();
    }

    private Message createPongMsg() {
        Message message = new Message();
        message.setSenderId(UUID.randomUUID().toString());
        message.setData("PONG");
        return message;
    }
}
