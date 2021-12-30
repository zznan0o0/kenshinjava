package org.kenshin.shoporder.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.kenshin.shoporder.entity.Product;
import org.kenshin.shoporder.service.ProductService;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProductServiceFallBack implements ProductService {
    @Override
    public Product findByPid(Integer pid) {
        Product product = new Product();
        product.pid = -1;
        return product;
    }
}
