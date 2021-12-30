package org.kenshin.shoporder.service;


import org.kenshin.shoporder.entity.Product;
import org.kenshin.shoporder.service.impl.ProductServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "shop-product", fallback = ProductServiceFallBack.class)//声明调用的提供者的name
public interface ProductService {
    //指定调用提供者的哪个方法
    //@FeignClient+@GetMapping 就是一个完整的请求路径 http://shop-product/getProduct/{pid}
    @GetMapping(value = "/getProduct/{pid}")
    Product findByPid (@PathVariable("pid") Integer pid);
}
