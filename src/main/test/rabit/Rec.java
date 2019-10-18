package rabit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import com.wsk.pojo.SecKillCar;
import com.wsk.tool.Constant;
import com.wsk.tool.JUtil;
import org.junit.Test;

public class Rec {
    private final static String QUEUE_NAME = "secKillQue";
    @Test
    public void  rec() throws Exception {

        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        // 从连接中创建通道
        Channel channel = connection.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        channel.queueBind(QUEUE_NAME,"secKillExchanger", "secKill");
        // 定义队列的消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);

        // 监听队列
        channel.basicConsume(QUEUE_NAME, true, consumer);

        // 获取消息
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(message);
            SecKillCar secKillCar= JUtil.strToObject(message,SecKillCar.class);
            System.out.println(secKillCar);
        }
    }
}
