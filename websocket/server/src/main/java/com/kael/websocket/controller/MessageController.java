package com.kael.websocket.controller;

import com.kael.websocket.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import java.security.Principal;


/**
 * @Author kael
 * @Description
 * @Date 2020/8/17 0017
 */
@Controller
public class MessageController {
    @Autowired
    SimpMessageSendingOperations messageTemplate;

    @MessageMapping("/group")
    public void groupChat(Message message, SimpMessageHeaderAccessor headerAccessor) {
        Principal user = headerAccessor.getUser();
        if (user==null) {
            System.out.println("没有用户登录，程序结束");
        } else {
            String username = user.getName();
            message.setFrom(username);
            System.out.println("群发:"+message);
            // 历史记录保存在redis,每次登录或刷新时从redis拉取聊天记录
            messageTemplate.convertAndSend("/topic/chatting",message);
        }
    }

    @MessageMapping("/single")
    public void singleChat(Message message,SimpMessageHeaderAccessor headerAccessor) {
        Principal user = headerAccessor.getUser();
        if (user==null) {
            System.out.println("没有用户登录，程序结束");
        } else {
            String username = user.getName();
            message.setFrom(username);
            System.out.println("单点:"+message);
            messageTemplate.convertAndSendToUser(message.getTo(),"/queue/chatting",message);
        }
    }
}
