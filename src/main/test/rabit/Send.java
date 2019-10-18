package rabit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.wsk.tool.Constant;
import org.junit.Test;

public class Send {
    private final static String QUEUE_NAME = "q_test_01";
    @Test
    public void send() throws Exception {
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        // 从连接中创建通道
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("IExchange","direct",true);
        // 声明（创建）队列
        //channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        // 消息内容
        String message = "Hello World!";
        channel.basicPublish("IExchange", Constant.SECKILLKEY, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
        //关闭通道和连接
        channel.close();
        connection.close();
    }
}
