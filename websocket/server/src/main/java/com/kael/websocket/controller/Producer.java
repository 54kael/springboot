package com.kael.websocket.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author kael
 * @Description
 * @Date 2020/8/17 0017
 */
@RestController
public class Producer {
    @Autowired
    AmqpTemplate amqpTemplate;

    @GetMapping("/send")
    public void sendMessage() {
        String message = "测试消息队列";
        amqpTemplate.convertAndSend("hello",message);
    }
}
