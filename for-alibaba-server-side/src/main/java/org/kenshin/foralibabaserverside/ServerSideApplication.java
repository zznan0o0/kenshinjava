package org.kenshin.foralibabaserverside;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServerSideApplication {
    public static void main(String[] args){
        SpringApplication.run(ServerSideApplication.class, args);
    }
}
