package com.kael.websocket.handler;

import com.kael.websocket.model.StompPrincipal;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

/**
 * @Author kael
 * @Description 自定义握手处理器
 * @Date 2020/8/19 0019
 */
public class CustomHandshakeHandler extends DefaultHandshakeHandler {
    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        ServletServerHttpRequest req = (ServletServerHttpRequest) request;
        String token = req.getServletRequest().getParameter("token");
        System.out.println(token+":连接上来了");
        return new StompPrincipal(token);
    }
}
