package org.kenshin.shoporder.controller;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RocketMQController {

    @GetMapping("sendMQ")
    public String sendMQ() throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("myproducter-group");
        producer.setNamesrvAddr("192.168.10.83:9876");

        producer.start();
        //构建消息对象
        Message message = new Message("myTopic", "myTag", ("Test RocketMQ Message").getBytes());
        return "success";
    }

}
