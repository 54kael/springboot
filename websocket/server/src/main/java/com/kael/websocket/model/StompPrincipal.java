package com.kael.websocket.model;

import java.security.Principal;

/**
 * @Author kael
 * @Description 自定义身份管理器
 * @Date 2020/8/19 0019
 */
public class StompPrincipal implements Principal {
    private final String name;

    public StompPrincipal(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

}
