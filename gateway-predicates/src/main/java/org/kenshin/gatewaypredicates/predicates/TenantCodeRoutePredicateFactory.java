package org.kenshin.gatewaypredicates.predicates;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import org.kenshin.gatewaypredicates.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.BetweenRoutePredicateFactory;
import org.springframework.cloud.gateway.support.DefaultServerRequest;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.reactive.function.server.HandlerStrategies;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class TenantCodeRoutePredicateFactory extends AbstractRoutePredicateFactory<TenantCodeRoutePredicateFactory.Config> {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private ObjectMapper objectMapper;

    private static final String TEST_ATTRIBUTE = "read_body_predicate_test_attribute";

    private static final String CACHE_REQUEST_BODY_OBJECT_KEY = "cachedRequestBodyObject";

    private static final String CACHE_REQUEST_TENANT_CODE = "cachedRequestTenantCode";

    private static final String INIT_TENANT_CODE = "0000";

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
        return exchange -> {
            String tenantCode = this.getCacheTenantCode(exchange);
            if(INIT_TENANT_CODE.equals(tenantCode)){
                return false;
            }

            if(tenantCode != null){
                return config.tenantCodes.contains(tenantCode);
            }

            String cachedBody = exchange.getAttribute(CACHE_REQUEST_BODY_OBJECT_KEY);
            if(cachedBody != null){
                try{
                    Map<String, String> map = this.objectMapper.readValue(cachedBody, Map.class);
                    tenantCode = map.get("tenantCode");
                    if(!StringUtils.isEmpty(tenantCode)){
                        this.setCacheTenantCode(exchange, tenantCode);
                        return config.getTenantCodes().contains(tenantCode);
                    }
                }
                catch (Throwable e){
                    e.printStackTrace();
                }
            }

            if(exchange.getRequest().getHeaders().get("Authorization") != null){
                try{
                    String auth = Objects.requireNonNull(exchange.getRequest().getHeaders().get("Authorization")).get(0);
                    String token = auth.substring(7);
                    Claims claims = jwtTokenUtil.getClaimsFromTokenRegardlessDate(token);
                    String sub = (String) claims.get("sub");
                    tenantCode = sub.split(":")[0];
                    this.setCacheTenantCode(exchange, tenantCode);
                    return config.getTenantCodes().contains(tenantCode);

                }
                catch (Throwable e){
                    e.printStackTrace();
                }
            }
//            this.setCacheTenantCode(exchange, tenantCode);
            return false;
        };
    }
    //获取缓存租户code

    /**
     * 获取缓存租户码
     * @param exchange
     * @return
     */
    public String getCacheTenantCode(ServerWebExchange exchange){
        return exchange.getAttribute(CACHE_REQUEST_TENANT_CODE);
    }

    /**
     * 设置缓存租户码
     * @param exchange
     * @param value
     */
    public void setCacheTenantCode(ServerWebExchange exchange, String value){
        if(value == null){
            value = INIT_TENANT_CODE;
        }
        exchange.getAttributes().put(CACHE_REQUEST_TENANT_CODE, value);
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
