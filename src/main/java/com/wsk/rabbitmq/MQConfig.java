package com.wsk.rabbitmq;

import com.wsk.tool.Constant;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by jiangyunxiong on 2018/5/29.
 *
 * 配置bean
 */
public class MQConfig {
    static {
        System.out.println("配置成功");
    }
    public static final String SECKILL_QUEUE = "seckill.queue";
    public static final String SECKill_EXCHANGE="secKillExchange";


    /**
     * Direct模式 交换机Exchange
     * 发送者先发送到交换机上，然后交换机作为路由再将信息发到队列，
     * */
//    @Bean
//    public Queue queue() {
//        return new Queue(QUEUE, true);
//    }
    @Bean
    public Queue secKillQueue(){
        return new Queue(SECKILL_QUEUE,true);
    }

    @Bean
    public DirectExchange secKillExchange(){
        return new DirectExchange(SECKill_EXCHANGE);
    }
    //绑定
    @Bean
    public Binding directBinding(){
        return BindingBuilder.bind(secKillQueue()).to(secKillExchange()).with(Constant.SECKILLKEY);
    }

}
