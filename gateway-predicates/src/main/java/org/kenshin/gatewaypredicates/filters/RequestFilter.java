//package org.kenshin.gatewaypredicates.filters;
//
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.core.io.buffer.DataBuffer;
//import org.springframework.core.io.buffer.DataBufferUtils;
//import org.springframework.http.MediaType;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//@Component
//public class RequestFilter implements GlobalFilter, Ordered {
//    public static final String CACHE_REQUEST_BODY_OBJECT_KEY= "cachedRequestBody";
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain){
//        try{
//            System.out.println(111);
//            MediaType mediaType = exchange.getRequest().getHeaders().getContentType();
//            if(mediaType != null && (mediaType.equals(MediaType.APPLICATION_JSON) || mediaType.equals(MediaType.APPLICATION_JSON_UTF8))){
//                return DataBufferUtils.join(exchange.getRequest().getBody()).flatMap(dataBuffer -> {
//                    DataBufferUtils.retain(dataBuffer);
//                    Flux<DataBuffer> cachedFlux = Flux.defer(() ->
//                            Flux.just(dataBuffer.slice(0, dataBuffer.readableByteCount())
//                            ));
//                    ServerHttpRequest mutateRequest = new ServerHttpRequestDecorator(exchange.getRequest()){
//                        @Override
//                        public Flux<DataBuffer> getBody(){
//                            return cachedFlux;
//                        }
//                    };
//                    exchange.getAttributes().put(CACHE_REQUEST_BODY_OBJECT_KEY, cachedFlux);
//
//                    return chain.filter(exchange.mutate().request(mutateRequest).build());
//                });
//            }
//        }catch(Exception ex){
//            ex.printStackTrace();
//        }
//
//        return chain.filter(exchange);
//    }
//
//    @Override
//    public int getOrder(){
//        return Ordered.HIGHEST_PRECEDENCE;
//    }
//}
