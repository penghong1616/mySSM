package com.wsk.tool;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqUtil {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    public void send(String key,Object object){
        rabbitTemplate.convertAndSend(key,object);
    }
}
