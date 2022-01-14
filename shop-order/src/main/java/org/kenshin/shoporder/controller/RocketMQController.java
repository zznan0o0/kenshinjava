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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RocketMQController {

    public String sendMQ() throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("myproducter-group");
        producer.setNamesrvAddr("192.168.10.83:9876");

        producer.start();
        //构建消息对象,主题、标签
        Message message = new Message("myTopic", "myTag", ("Test RocketMQ Message--------------------------").getBytes());
        //发送消息，参数二是超时时间
        SendResult result = producer.send(message, 10000);
        System.out.println(result);
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

}
