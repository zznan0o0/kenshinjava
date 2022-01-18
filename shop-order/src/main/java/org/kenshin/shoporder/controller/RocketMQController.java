package org.kenshin.shoporder.controller;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.kenshin.shoporder.entity.Order;
import org.kenshin.shoporder.entity.Product;
import org.kenshin.shoporder.service.MQProducerService;
import org.kenshin.shoporder.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RocketMQController {
    @Qualifier("org.kenshin.shoporder.service.ProductService")
    @Autowired
    ProductService productService;

    @Autowired
    MQProducerService mqProducerService;

    public String sendMQ() throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("myproducter-group");
        producer.setNamesrvAddr("192.168.10.83:9876");

        producer.start();
        //构建消息对象,主题、标签
        Message message = new Message("myTopic", "myTag", ("Test RocketMQ Message--------------------------").getBytes());
        //发送消息，参数二是超时时间
        SendResult result = producer.send(message, 10000);
        System.out.println(result);
//        producer.sendMessageInTransaction("", null);
        //关闭生产者
        producer.shutdown();
        return "success";
    }



    //得在调用后谁几秒不然会直接结束进程收不到消息
    public String recMQ() throws MQClientException {
        //创建消费者指定组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("myconsumer-group");

        consumer.setNamesrvAddr("192.168.10.83:9876");
        //订阅主题、标签
        consumer.subscribe("myTopic", "*");
        //设置一个回调函数，并在函数中编写接受消息后的逻辑
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                //处理消费逻辑
                System.out.println("开始处理接受到的消息逻辑");
                System.out.println(msgs);
                System.out.println("结束处理接受到的消息逻辑");

                //返回成功状态
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        //启动消费者
        consumer.start();
        System.out.println("消费者启动了");
        return "success";
    }

    @GetMapping("/order/prod/{pid}")
    public Order order(@PathVariable("pid") Integer pid) {
        System.out.println("接收到下单请求，查询商品信息");
        Product product = this.productService.findByPid(pid);

        try {
            Thread.sleep(2000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Order order = new Order();
        order.orderId = "1";
        order.productPid = product.pid;
        order.productPrice = product.price;
        order.productName = product.name;
        System.out.println("订单创建成功");
        try{
            this.mqProducerService.send(order);
            Thread.sleep(100);
//            this.sendMQ("product-name", "order-topic");
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        return order;
    }
}
