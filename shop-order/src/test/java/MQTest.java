import org.apache.rocketmq.client.exception.MQClientException;
import org.junit.Test;
import org.kenshin.shoporder.controller.RocketMQController;

public class MQTest {
    @Test
    public void send() throws Exception {
        RocketMQController rocketMQController = new RocketMQController();
        System.out.println(rocketMQController.sendMQ());;
    }

    @Test
    public void rec() throws Exception {
        RocketMQController rocketMQController = new RocketMQController();
        System.out.println(rocketMQController.recMQ());
//        System.out.println(rocketMQController.sendMQ());;

        Thread.sleep(5000);
        System.out.println("睡醒了");
    }
}
