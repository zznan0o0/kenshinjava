package org.kenshin.gatewaypredicates.predicates;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import org.springframework.cloud.gateway.handler.AsyncPredicate;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.web.reactive.function.server.HandlerStrategies;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;

/**
 * Predicate that reads the body and applies a user provided predicate to run on the body.
 * The body is cached in memory so that possible subsequent calls to the predicate do not
 * need to deserialize again.
 */

@Component
public class MyReadBodyRoutePredicateFactory
        extends AbstractRoutePredicateFactory<org.springframework.cloud.gateway.handler.predicate.ReadBodyRoutePredicateFactory.Config> {

    protected static final Log log = LogFactory
            .getLog(org.springframework.cloud.gateway.handler.predicate.ReadBodyRoutePredicateFactory.class);

    private static final String TEST_ATTRIBUTE = "read_body_predicate_test_attribute";

    private static final String CACHE_REQUEST_BODY_OBJECT_KEY = "cachedRequestBodyObject";

    private final List<HttpMessageReader<?>> messageReaders;

    public MyReadBodyRoutePredicateFactory() {
        super(org.springframework.cloud.gateway.handler.predicate.ReadBodyRoutePredicateFactory.Config.class);
        this.messageReaders = HandlerStrategies.withDefaults().messageReaders();
    }

    public MyReadBodyRoutePredicateFactory(List<HttpMessageReader<?>> messageReaders) {
        super(org.springframework.cloud.gateway.handler.predicate.ReadBodyRoutePredicateFactory.Config.class);
        this.messageReaders = messageReaders;
    }

    @Override
    @SuppressWarnings("unchecked")
    public AsyncPredicate<ServerWebExchange> applyAsync(org.springframework.cloud.gateway.handler.predicate.ReadBodyRoutePredicateFactory.Config config) {
        return new AsyncPredicate<ServerWebExchange>() {
            @Override
            public Publisher<Boolean> apply(ServerWebExchange exchange) {
                Class inClass = config.getInClass();

                Object cachedBody = exchange.getAttribute(CACHE_REQUEST_BODY_OBJECT_KEY);
                Mono<?> modifiedBody;
                // We can only read the body from the request once, once that happens if
                // we try to read the body again an exception will be thrown. The below
                // if/else caches the body object as a request attribute in the
                // ServerWebExchange so if this filter is run more than once (due to more
                // than one route using it) we do not try to read the request body
                // multiple times
                if (cachedBody != null) {
                    try {
                        boolean test = config.getPredicate().test(cachedBody);
                        exchange.getAttributes().put(TEST_ATTRIBUTE, test);
                        return Mono.just(test);
                    }
                    catch (ClassCastException e) {
                        if (log.isDebugEnabled()) {
                            log.debug("Predicate test failed because class in predicate "
                                    + "does not match the cached body object", e);
                        }
                    }
                    return Mono.just(false);
                }
                else {
                    return ServerWebExchangeUtils.cacheRequestBodyAndRequest(exchange,
                            (serverHttpRequest) -> ServerRequest
                                    .create(exchange.mutate().request(serverHttpRequest)
                                            .build(), messageReaders)
                                    .bodyToMono(inClass)
                                    .doOnNext(objectValue -> exchange.getAttributes().put(
                                            CACHE_REQUEST_BODY_OBJECT_KEY, objectValue))
                                    .map(objectValue -> config.getPredicate()
                                            .test(objectValue))
                                    //总是返回true
                                    .thenReturn(true));
                }
            }

            @Override
            public String toString() {
                return String.format("ReadBody: %s", config.getInClass());
            }
        };
    }

    @Override
    @SuppressWarnings("unchecked")
    public Predicate<ServerWebExchange> apply(org.springframework.cloud.gateway.handler.predicate.ReadBodyRoutePredicateFactory.Config config) {
        throw new UnsupportedOperationException(
                "ReadBodyPredicateFactory is only async.");
    }

    public static class Config {

        private Class inClass;

        private Predicate predicate;

        private Map<String, Object> hints;

        public Class getInClass() {
            return inClass;
        }

        public MyReadBodyRoutePredicateFactory.Config setInClass(Class inClass) {
            this.inClass = inClass;
            return this;
        }

        public Predicate getPredicate() {
            return predicate;
        }

        public MyReadBodyRoutePredicateFactory.Config setPredicate(Predicate predicate) {
            this.predicate = predicate;
            return this;
        }

        public <T> MyReadBodyRoutePredicateFactory.Config setPredicate(Class<T> inClass, Predicate<T> predicate) {
            setInClass(inClass);
            this.predicate = predicate;
            return this;
        }

        public Map<String, Object> getHints() {
            return hints;
        }

        public MyReadBodyRoutePredicateFactory.Config setHints(Map<String, Object> hints) {
            this.hints = hints;
            return this;
        }

    }

}
