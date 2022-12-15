package org.kenshin.gatewaypredicates;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Predicate;

@SpringBootApplication
public class GatewayPredicatesApplication {
    public static void main(String[] args){
        SpringApplication.run(GatewayPredicatesApplication.class, args);
    }

    /**
     * 用于readBody断言，可配置到yml
     * @return
     */
    @Bean
    public Predicate bodyPredicate(){
        return new Predicate() {
            @Override
            public boolean test(Object o) {
                return true;
            }
        };
    }

    @Bean
    public Predicate bodyFalsePredicate(){
        return new Predicate() {
            @Override
            public boolean test(Object o) {
                return false;
            }
        };
    }
}