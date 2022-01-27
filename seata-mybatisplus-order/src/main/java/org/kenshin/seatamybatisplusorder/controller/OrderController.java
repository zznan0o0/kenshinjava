package org.kenshin.seatamybatisplusorder.controller;

import io.seata.spring.annotation.GlobalTransactional;
import org.kenshin.seatamybatisplusorder.domain.TbOrder;
import org.kenshin.seatamybatisplusorder.service.TbOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    TbOrderService tbOrderService;

    @GetMapping("/add")
    @GlobalTransactional
    public String add(){
        TbOrder tbOrder = new TbOrder();
        Date date = new Date();
        long time = date.getTime();
        tbOrder.setOrder(String.valueOf(time));
        tbOrder.setUserId(1);
        tbOrder.setMoney(1000);
        this.tbOrderService.save(tbOrder);

        RestTemplate restTemplate = new RestTemplate();
        String result =  restTemplate.getForObject("http://127.0.0.1:7005/user/deduct?id=1&money=1000", String.class);
        return result;
//        return "{\"ok\":true}";
    }
}
