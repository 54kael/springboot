package com.kael.websocket.controller;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author kael
 * @Description
 * @Date 2020/8/17 0017
 */
@Component
@RabbitListener(queues = "hello")
public class Receiver {
    @RabbitHandler
    public void process(String message){
        System.out.println("Receiver:"+message);
    }
}
