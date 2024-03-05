package com.budzko.cookbook.springboot.kafka.service;

import com.budzko.cookbook.springboot.kafka.config.KafkaConsumerConfig;
import com.budzko.cookbook.springboot.kafka.msg.MsgUtils;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class ProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String msg) {
        kafkaTemplate.send(KafkaConsumerConfig.TOPIC, msg);
    }

    @PostConstruct
    public void postConstruct() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (true) {
                    try {
                        sendMessage(MsgUtils.toStr(MsgUtils.create("Msg" + i++)));
                        Thread.sleep(2000L);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
//        t.start();
    }
}
