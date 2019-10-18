import com.alibaba.fastjson.JSON;
import com.wsk.pojo.SecKillCar;
import com.wsk.pojo.SecKillGood;
import com.wsk.rabbitmq.MQSender;
import com.wsk.tool.Constant;
import com.wsk.tool.JUtil;
import com.wsk.tool.RabbitMqUtil;
import com.wsk.tool.SecKillHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring*.xml"})
@WebAppConfiguration
public class RabbitMQTest {
    @Autowired
    private MQSender mqSender;
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Test
    public void sendMessage() {
        SecKillCar secKillCar = new SecKillCar();
        secKillCar.setUId(3);
        secKillCar.setSecId(1);
        secKillCar.setModified(new Date());
        System.out.println(JUtil.objToString(secKillCar));
        mqSender.sendSecKill(secKillCar);
    }
    @Test
    public void send(){
        SecKillCar secKillCar = new SecKillCar();
        secKillCar.setUId(3);
        secKillCar.setSecId(1);
        secKillCar.setModified(new Date());
        rabbitTemplate.convertAndSend("secKill", JUtil.objToString(secKillCar));
    }
}
