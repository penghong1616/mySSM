package com.wsk.rabbitmq;

import com.wsk.pojo.GoodsCar;
import com.wsk.pojo.SecKillCar;
import com.wsk.pojo.SecKillGood;
import com.wsk.service.GoodsCarService;
import com.wsk.service.SecKillGoodService;
import com.wsk.tool.JUtil;
import com.wsk.tool.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class MQListener {
    @Autowired
    private GoodsCarService goodsCarService;
    @Autowired
    private JedisUtil jedisUtil;
    @Autowired
    private SecKillGoodService secKillGoodService;
    public void listen(String msg) {
        System.out.println("msg"+msg);
        SecKillCar secKillCar= JUtil.strToObject(msg,SecKillCar.class);
        System.out.println("秒杀队列开始处理消息： " + msg);
        System.out.println("收到对象："+secKillCar.toString());
        int userId= secKillCar.getUId();
        int secId=secKillCar.getSecId();
        int sId=secKillGoodService.selectGoodById(secId);
        GoodsCar goodsCar=new GoodsCar();
        goodsCar.setUid(userId);
        goodsCar.setSid(sId);
        goodsCar.setModified(new Date());
        goodsCar.setDisplay(1);
        goodsCar.setQuantity(1);
        goodsCarService.insert(goodsCar);
        System.out.println("秒杀商品添加成功");
    }
}
