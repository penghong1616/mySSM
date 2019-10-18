package com.wsk.rabbitmq;
import com.wsk.tool.Constant;
import com.wsk.tool.JUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jiangyunxiong on 2018/5/29.
 */
@Service
public class MQSender {

    private static Logger log = LoggerFactory.getLogger(MQSender.class);

    @Autowired
	RabbitTemplate rabbitTemplate;

//    public void send(Object message){
//        String msg = RedisService.beanToString(message);
//        log.info("send message:"+msg);
//        amqpTemplate.convertAndSend(MQConfig.QUEUE, message);
//    }

    	public void sendSecKill(Object message) {
		String msg = JUtil.objToString(message);
		log.info("send topic message:"+msg);
		rabbitTemplate.convertAndSend(MQConfig.SECKill_EXCHANGE, Constant.SECKILLKEY, msg+"1");
	}
}
