package org.kenshin.shoporder.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.kenshin.shoporder.entity.Order;
import org.kenshin.shoporder.entity.Product;
import org.kenshin.shoporder.service.ProductService;
import org.kenshin.shoporder.utils.SentinelExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProductService productService;


    @GetMapping("/getBaidu")
    public String getBaidu(){
        RestTemplate restTemplate = new RestTemplate();
        String str = restTemplate.getForObject("https://www.baidu.com/", String.class);
        return str;
    }

    @GetMapping(value = "/getOrder")
    public Order getOrder(){
        RestTemplate restTemplate = new RestTemplate();
        ServiceInstance serviceInstance = discoveryClient.getInstances ("shop-product").get(0);
        String url = serviceInstance.getHost() + ":" + serviceInstance.getPort();
        Product product = restTemplate.getForObject("http://" + url + "/getProduct/1", Product.class);
        Order order = new Order();
        order.orderId = url;
        order.productName = product.name;
        order.productPrice = product.price;
        order.productPid = product.pid;
        return order;
    }

    //自己实现负载均衡
    @GetMapping(value = "/getOrderLoad")
    public Order getOrderLoad(){
        RestTemplate restTemplate = new RestTemplate();
        List<ServiceInstance> instances = discoveryClient.getInstances ("shop-product");
        int index = new Random().nextInt (instances.size());
        ServiceInstance serviceInstance = instances.get(index);
        String url = serviceInstance.getHost() + ":" + serviceInstance.getPort();
        Product product = restTemplate.getForObject("http://" + url + "/getProduct/2", Product.class);
        Order order = new Order();
        order.orderId = url;
        order.productName = product.name;
        order.productPrice = product.price;
        order.productPid = product.pid;
        return order;
    }

    //ribbon实现负载均衡
    @GetMapping(value = "/getOrderRibbon")
    public Order getOrderRibbon(){
        String url = "shop-product";
        Product product = this.restTemplate.getForObject(
                "http://" + url + "/getProduct/3", Product.class);

        Order order = new Order();
        order.orderId = url;
        order.productName = product.name;
        order.productPrice = product.price;
        order.productPid = product.pid;
        return order;
    }

    //通过fegin调用商品微服务
    @GetMapping(value = "/getOrderFegin")
    public Order getOrderFegin(){
        Product product = productService.findByPid(4);
        Order order = new Order();
        order.orderId = "getOrderFegin"+1;
        order.productName = product.name;
        order.productPrice = product.price;
        order.productPid = product.pid;
        return order;
    }


    //线程阻塞导致其他接口访问需要等待线程空闲
    @GetMapping(value = "/getOrderSleep")
    public Order getOrderSleep() {
        try{
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Product product = productService.findByPid(5);
        Order order = new Order();
        order.orderId = "getOrderFegin"+1;
        order.productName = product.name;
        order.productPrice = product.price;
        order.productPid = product.pid;
        return order;
    }

    @GetMapping("/message")
    public String message(){
        return "helloworld";
    }


    //限流
    @GetMapping(value = "/getOrderSentinel")
    @SentinelResource(value = "getOrderSentinel", blockHandler = "exceptionHandler"
            , blockHandlerClass = { SentinelExceptionUtil.class }
            , fallback = "fallbackHandler")
    public Order getOrderSentinel(){
        Product product = productService.findByPid(6);
        Order order = new Order();
        order.orderId = "getOrderFegin"+1;
        order.productName = product.name;
        order.productPrice = product.price;
        order.productPid = product.pid;
        return order;
    }

    // Fallback 函数，函数签名与原函数一致.
    public static Order fallbackHandler() {
        Order order = new Order();
        order.orderId = "fallbackHandler";
        return order;

    }

}
