package com.kael.websocket.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author kael
 * @Description
 * @Date 2020/8/17 0017
 */
@Configuration
public class RabbitmqConfig {
    @Bean
    public Queue queue(){
        return new Queue("hello");
    }
}
