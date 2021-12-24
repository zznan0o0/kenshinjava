package org.kenshin.shopproduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ShopProductApplication2 {
    public static void main(String[] args){
        SpringApplication.run(ShopProductApplication2.class, args);
    }
}
