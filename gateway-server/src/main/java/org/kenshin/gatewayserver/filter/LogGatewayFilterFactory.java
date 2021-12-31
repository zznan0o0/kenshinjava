package org.kenshin.gatewayserver.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

//聚玻日志过滤器
@Component
public class LogGatewayFilterFactory extends AbstractGatewayFilterFactory<LogGatewayFilterFactory.Config> {
    public LogGatewayFilterFactory(){
        //给父级传入配置类型
        super(LogGatewayFilterFactory.Config.class);
    }
    //读取配置文件中的参数赋值给配置类
    @Override
    public List<String> shortcutFieldOrder(){
        return Arrays.asList("consoleLog","cacheLog");
    }

    //过滤逻辑
    @Override
    public GatewayFilter apply(LogGatewayFilterFactory.Config config){
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                if (config.isCacheLog()){
                    System.out.println("cachelog已经开启");
                }

                if(config.isConsoleLog()){
                    System.out.println("consoleLog已经开启");
                }

                return chain.filter(exchange);
            }
        };
    }

    public static class Config{
        private boolean consoleLog;
        private boolean cacheLog;


        public boolean isConsoleLog() {
            return consoleLog;
        }

        public void setConsoleLog(boolean consoleLog) {
            this.consoleLog = consoleLog;
        }

        public boolean isCacheLog() {
            return cacheLog;
        }

        public void setCacheLog(boolean cacheLog) {
            this.cacheLog = cacheLog;
        }
    }
}
