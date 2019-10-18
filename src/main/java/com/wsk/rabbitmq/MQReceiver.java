package com.wsk.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * Created by jiangyunxiong on 2018/5/29.
 */
@Service
public class MQReceiver {

    private static Logger log = LoggerFactory.getLogger(MQReceiver.class);

    static {
        System.out.println("依赖成功");
    }
    @RabbitListener(queues=MQConfig.SECKILL_QUEUE)
    public void receive(String message){
        log.info("receive message:"+message);

    }
}
