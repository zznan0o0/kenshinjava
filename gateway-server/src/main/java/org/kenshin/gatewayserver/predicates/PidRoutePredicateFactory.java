package org.kenshin.gatewayserver.predicates;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

// 自定义路由断言工厂
// 名字必须是配置项名(Pid=1,100)+RoutePredicateFactory
// 必须继承AbstractRoutePredicateFactory<配置类>
@Component
public class PidRoutePredicateFactory extends AbstractRoutePredicateFactory<PidRoutePredicateFactory.Config> {


    public PidRoutePredicateFactory() {
        super(PidRoutePredicateFactory.Config.class);
    }

    // 将配置文件中的值按返回集合的顺序，赋值给配置类
    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(new String[]{"minPid", "maxPid"});
    }

    @Override
    public Predicate<ServerWebExchange> apply(Consumer<Config> consumer) {
        return super.apply(consumer);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        // 创建网关断言对象
        return new Predicate<ServerWebExchange>() {
            // 检查
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                // TODO 获取请求参数pid，判断是否满足[1, 100),post是getBody
                MultiValueMap<String, String> queryParams = serverWebExchange.getRequest().getQueryParams();
                String pid = queryParams.getFirst("pid");
                if (!StringUtils.isEmpty(pid) && pid.matches("[0-9]+")) {
                    int iPid = Integer.parseInt(pid);
                    if (iPid >= config.minPid && iPid < config.maxPid) {
                        return true;
                    }
                }
                return false;
            }
        };
    }


    // 配置类，属性用于接收配置文件中的值
    @Validated
    public static class Config {
        private int minPid;
        private int maxPid;

        public int getMinPid() {
            return minPid;
        }

        public void setMinPid(int minPid) {
            this.minPid = minPid;
        }

        public int getMaxPid() {
            return maxPid;
        }

        public void setMaxPid(int maxPid) {
            this.maxPid = maxPid;
        }
    }
}