import lombok.Data;
import org.apache.rocketmq.client.exception.MQClientException;
import org.junit.Test;
import org.kenshin.shoporder.controller.RocketMQController;
import org.springframework.beans.BeanUtils;

import java.util.function.Supplier;

public class MQTest {
    @Data
    private class A{
        private String a1;
        private Integer a2;
        private Boolean a3;
    }

    @Data
    private class B{
        private String a1;
        private Integer a2;
        private Boolean b1;
    }


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

    @Test
    public void A2B(){
        A a = new A();
        a.setA1("1");
        a.setA2(2);
        a.setA3(false);

        B b = new B();
        BeanUtils.copyProperties(a, b);
        b.setB1(true);
        System.out.println(b);
        b.setA1("3");
        System.out.println(a);
        System.out.println(b);

//        System.out.println(A::new);
        Supplier AN = A::new;
        A a1 = (A) AN.get();
        System.out.println(a1);
    }




}
