package org.kenshin.shopproduct.controller;

import org.kenshin.shopproduct.entity.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @GetMapping(value = "/getProduct")
    public Product getProduct(){
        Product product = new Product();
        product.name = "香蕉";
        product.price = 10;
        return product;
    }
}
