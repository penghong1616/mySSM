package com.wsk.tool;

import com.alibaba.fastjson.JSON;
import com.wsk.pojo.SecKillCar;
import com.wsk.pojo.SecKillGood;
import com.wsk.service.SecKillGoodService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
public class SecKillHandler implements MessageListener {
    @Autowired
    private SecKillGoodService secKillGoodService;
    static {
        System.out.println("依赖成功");
    }
    @Override
    public void onMessage(Message message) {
        System.out.println("message:"+message.toString());
    }
}
