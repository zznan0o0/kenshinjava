package org.kenshin.shoporder.controller;

import org.kenshin.shoporder.entity.Order;
import org.kenshin.shoporder.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@RestController
public class OrderController {
    @Autowired
    private DiscoveryClient discoveryClient;



    @GetMapping(value = "/getOrder")
    public Order getOrder(){
        RestTemplate restTemplate = new RestTemplate();

        ServiceInstance serviceInstance = discoveryClient.getInstances ("shop-product").get(0);
        String url = serviceInstance.getHost() + ":" + serviceInstance.getPort();
        Product product = restTemplate.getForObject("http://" + url + "/getProduct", Product.class);
        Order order = new Order();
        order.orderId = url;
        order.productName = product.name;
        order.productPrice = product.price;
        return order;
    }

    @GetMapping(value = "/getOrderLoad")
    public Order getOrderLoad(){
        RestTemplate restTemplate = new RestTemplate();
        List<ServiceInstance> instances = discoveryClient.getInstances ("shop-product");
        int index = new Random().nextInt (instances.size());
        ServiceInstance serviceInstance = instances.get(index);
        String url = serviceInstance.getHost() + ":" + serviceInstance.getPort();
        Product product = restTemplate.getForObject("http://" + url + "/getProduct", Product.class);
        Order order = new Order();
        order.orderId = url;
        order.productName = product.name;
        order.productPrice = product.price;
        return order;
    }
}
