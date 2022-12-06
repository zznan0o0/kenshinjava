package org.kenshin.testeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class TestEureka {
    public static void main(String[] args) {
        SpringApplication.run(TestEureka.class, args);
    }
}
