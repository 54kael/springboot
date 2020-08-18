package com.kael.websocket.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.StringStack;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.GenericMessage;

import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @Author kael
 * @Description
 * @Date 2020/8/18 0018
 */
public class ChatChin implements ChannelInterceptor{

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {

        String token = null;
        Message<?> msg=null;
        MessageHeaders header = message.getHeaders();
        System.out.println(header);
        SimpMessageType type =header.get("simpMessageType",SimpMessageType.class);
        if (type==SimpMessageType.CONNECT_ACK) {
            Message<?> m = header.get("simpConnectMessage",Message.class);
            if (m!=null) {
                MessageHeaders headers = m.getHeaders();
                Map<String,Object> map = (Map<String, Object>) headers.get("nativeHeaders");
                List<String> list = (List<String>) map.get("token");
                token=list.get(0);

                return message;
            }

        }
        if (type==SimpMessageType.MESSAGE) {
            String s = new String((byte[])message.getPayload(), StandardCharsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            try {
            com.kael.websocket.entity.Message m = objectMapper.readValue(s,com.kael.websocket.entity.Message.class);


                String ss = objectMapper.writeValueAsString(m);
                msg = new GenericMessage<>(ss.getBytes(StandardCharsets.UTF_8),header);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return msg;
    }

    @Override
    public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
        System.out.println(message);
    }

    @Override
    public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, Exception ex) {
        System.out.println(message);
    }

    @Override
    public boolean preReceive(MessageChannel channel) {
        return false;
    }

    @Override
    public Message<?> postReceive(Message<?> message, MessageChannel channel) {
        return null;
    }

    @Override
    public void afterReceiveCompletion(Message<?> message, MessageChannel channel, Exception ex) {

    }
}
