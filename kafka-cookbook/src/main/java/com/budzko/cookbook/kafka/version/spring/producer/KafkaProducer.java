package com.budzko.cookbook.kafka.version.spring.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {
    private static final long messageSendInterval = 10000L;
    private final KafkaTemplate<String, String> eventKafkaTemplate;
    @Value("${spring.kafka.topics.log-event}")
    private String eventTopic;
    @Value("${source}")
    private String source;

    private static String buildMessage() {
        return LocalDateTime.now() + " " + UUID.randomUUID();
    }

    public static void main(String[] args) {
        KafkaProducer proxyInstance = (KafkaProducer) Proxy.newProxyInstance(
                KafkaProducer.class.getClassLoader(),
                new Class<?>[]{KafkaProducer.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if ("foo".equals(method.getName())) {
                            System.out.println("before");
                        }
                        return method.invoke(proxy, args);
                    }
                });
        proxyInstance.foo();
        Class

    }

    public void foo() {
        System.out.println("FOO");
    }

    @PostConstruct
    void init() {
        new Thread(() -> {
            try {
                while (true) {
                    String message = buildMessage();
                    sendMessage(message);
                    log.info("Message was send: {}", message);
                    Thread.sleep(messageSendInterval);
                }
            } catch (RuntimeException | InterruptedException e) {
                log.error(e.getMessage(), e);
            }
        }).start();
    }

    private void sendMessage(String message) {
//        eventKafkaTemplate.send(eventTopic, message);
        Message<String> msg = MessageBuilder
                .withPayload(message)
                .copyHeaders(
                        Map.of(
                                KafkaHeaders.TOPIC, eventTopic,
                                "source", source
                        ))
                .build();
        eventKafkaTemplate.send(msg);
    }
}
