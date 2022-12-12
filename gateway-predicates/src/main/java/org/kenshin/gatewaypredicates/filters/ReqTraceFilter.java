//package org.kenshin.gatewaypredicates.filters;
//
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.cloud.gateway.filter.factory.rewrite.CachedBodyOutputMessage;
//import org.springframework.cloud.gateway.support.BodyInserterContext;
//import org.springframework.cloud.gateway.support.DefaultServerRequest;
//import org.springframework.core.Ordered;
//import org.springframework.core.io.buffer.DataBuffer;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
//import org.springframework.stereotype.Component;
//import org.springframework.web.reactive.function.BodyInserter;
//import org.springframework.web.reactive.function.BodyInserters;
//import org.springframework.web.reactive.function.server.ServerRequest;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//import javax.annotation.Resource;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
//@Component
//public class ReqTraceFilter implements GlobalFilter, GatewayFilter, Ordered {
//
////    @Resource
////    private IPlatformFeignClient platformFeignClient;
//
//    /**
//     * httpheader，traceId的key名称
//     */
//    private static final String REQUESTID = "traceId";
//
//    private static final String CONTENT_TYPE = "Content-Type";
//
//    private static final String CONTENT_TYPE_JSON = "application/json";
//
//    private static final String GATEWAY_ROUTE_BEAN = "org.springframework.cloud.gateway.support.ServerWebExchangeUtils.gatewayRoute";
//
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        ServerHttpRequest request = exchange.getRequest();
//        //判断过滤器是否执行
////        String requestUrl = RequestUtils.getCurrentRequest(request);
////        if (!RequestUtils.isFilter(requestUrl)) {
//            String bodyStr = "";
//            String contentType = request.getHeaders().getFirst(CONTENT_TYPE);
//            String method = request.getMethodValue();
//            //判断是否为POST请求
//            if (null != contentType && HttpMethod.POST.name().equalsIgnoreCase(method) && contentType.contains(CONTENT_TYPE_JSON)) {
//                ServerRequest serverRequest = new DefaultServerRequest(exchange);
//                List<String> list = new ArrayList<>();
//                // 读取请求体
//                Mono<String> modifiedBody = serverRequest.bodyToMono(String.class)
//                        .flatMap(body -> {
//                            //记录请求体日志
//                            final String nId = saveRequestOperLog(exchange, body);
//                            //记录日志id
//                            list.add(nId);
//                            return Mono.just(body);
//                        });
//
//                BodyInserter bodyInserter = BodyInserters.fromPublisher(modifiedBody, String.class);
//                HttpHeaders headers = new HttpHeaders();
//                headers.putAll(exchange.getRequest().getHeaders());
//                headers.remove(HttpHeaders.CONTENT_LENGTH);
//
//                CachedBodyOutputMessage outputMessage = new CachedBodyOutputMessage(exchange, headers);
//                return bodyInserter.insert(outputMessage, new BodyInserterContext())
//                        .then(Mono.defer(() -> {
//                            ServerHttpRequestDecorator decorator = new ServerHttpRequestDecorator(
//                                    exchange.getRequest()) {
//                                @Override
//                                public HttpHeaders getHeaders() {
//                                    long contentLength = headers.getContentLength();
//                                    HttpHeaders httpHeaders = new HttpHeaders();
//                                    httpHeaders.putAll(super.getHeaders());
//                                    httpHeaders.put(REQUESTID,list);
//                                    if (contentLength > 0) {
//                                        httpHeaders.setContentLength(contentLength);
//                                    } else {
//                                        httpHeaders.set(HttpHeaders.TRANSFER_ENCODING, "chunked");
//                                    }
//                                    return httpHeaders;
//                                }
//
//                                @Override
//                                public Flux<DataBuffer> getBody() {
//                                    return outputMessage.getBody();
//                                }
//                            };
//
//                            return chain.filter(exchange.mutate().request(decorator).build());
//                        }));
//            }
//            if (HttpMethod.GET.name().equalsIgnoreCase(method)) {
//                bodyStr = request.getQueryParams().toString();
//                String nId = saveRequestOperLog(exchange, bodyStr);
//                ServerHttpRequest userInfo = exchange.getRequest().mutate()
//                        .header(REQUESTID, nId).build();
//                return chain.filter(exchange.mutate().request(userInfo).build());
//            }
//
////        }
//        return chain.filter(exchange);
//    }
//
//
//    /**
//     * 保存请求日志
//     *
//     * @param exchange
//     * @param requestParameters
//     * @return
//     */
//    private String saveRequestOperLog(ServerWebExchange exchange, String requestParameters) {
////        log.debug("接口请求参数：{}", requestParameters);
//        ServerHttpRequest request = exchange.getRequest();
//        String ip = Objects.requireNonNull(request.getRemoteAddress()).getAddress().getHostAddress();
////        SaveOperLogVO vo = new  SaveOperLogVO();
////        vo.setIp(ip);
////        vo.setReqUrl(RequestUtils.getCurrentRequest(request));
////        vo.setReqMethod(request.getMethodValue());
////        vo.setRequestParameters(requestParameters);
//
////        Route route = exchange.getAttribute(GATEWAY_ROUTE_BEAN);
//        //是否配置路由
////        if (route != null) {
////            vo.setSubsystem(route.getId());
////        }
////        ResEntity<String> res = platformFeignClient.saveOperLog(vo);
////        log.debug("当前请求ID返回的数据：{}", res);
////        return res.getData();
//        return "";
//    }
//
//    @Override
//    public int getOrder() {
//        return 5;
//    }
//}