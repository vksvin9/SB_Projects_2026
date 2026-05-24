package com.vin.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class LoggingFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(
            org.springframework.web.server.ServerWebExchange exchange,
            org.springframework.cloud.gateway.filter.GatewayFilterChain chain) {

        String path = exchange.getRequest().getURI().getPath();
        String method = exchange.getRequest().getMethod().name();

        log.info("Gateway Request -> method={}, path={}", method, path);

        return chain.filter(exchange)
                .then(Mono.fromRunnable(() ->
                        log.info(
                                "Gateway Response -> status={}",
                                exchange.getResponse().getStatusCode()
                        )));
    }

    @Override
    public int getOrder() {
        return -1;
    }
}