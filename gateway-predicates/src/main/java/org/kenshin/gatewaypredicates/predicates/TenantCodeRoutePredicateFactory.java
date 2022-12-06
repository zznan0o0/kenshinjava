package org.kenshin.gatewaypredicates.predicates;

import io.jsonwebtoken.Claims;
import org.kenshin.gatewaypredicates.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.BetweenRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class TenantCodeRoutePredicateFactory extends AbstractRoutePredicateFactory<TenantCodeRoutePredicateFactory.Config> {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public TenantCodeRoutePredicateFactory() {
        super(TenantCodeRoutePredicateFactory.Config.class);
    }


//    @Override
//    public List<String> shortcutFieldOrder() {
//        return Collections.singletonList("tenantCode");
//    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("tenantCodes");
    }

    @Override
    public ShortcutType shortcutType() {
        return ShortcutType.GATHER_LIST;
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        // 创建网关断言对象
        // 检查
        return serverWebExchange -> {
            try {
                String auth = Objects.requireNonNull(serverWebExchange.getRequest().getHeaders().get("Authorization")).get(0);
                String token = auth.substring(7);
                Claims claims = jwtTokenUtil.getClaimsFromTokenRegardlessDate(token);
                String sub = (String) claims.get("sub");
                String tenantCode = sub.split(":")[0];
                return config.getTenantCodes().contains(tenantCode);
            }
            catch (Throwable e){
                e.printStackTrace();
                return false;
            }
        };
    }

    @Validated
    public static class Config {
        private List<String> tenantCodes;

        public TenantCodeRoutePredicateFactory.Config setTenantCodes(List<String> tenantCodes) {
            this.tenantCodes = tenantCodes;
            return this;
        }

        public List<String> getTenantCodes() {
            return this.tenantCodes;
        }

        public String toString(){
            return tenantCodes + "";
        }
    }
}
