package org.kenshin.shopproduct.controller;

import org.kenshin.shopproduct.entity.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @GetMapping(value = "/getProduct/{pid}")
    public Product getProduct(@PathVariable Integer pid){
        Product product = new Product();
        product.name = "苹果";
        product.price = 2;
        product.pid = pid;
        return product;
    }
}
