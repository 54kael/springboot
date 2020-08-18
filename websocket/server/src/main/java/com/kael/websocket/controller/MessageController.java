package com.kael.websocket.controller;

import com.kael.websocket.entity.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


/**
 * @Author kael
 * @Description
 * @Date 2020/8/17 0017
 */
@Controller
public class MessageController {
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Message greeting(Message message) {
        System.out.println("===>:"+message);
        return message;
    }
}
