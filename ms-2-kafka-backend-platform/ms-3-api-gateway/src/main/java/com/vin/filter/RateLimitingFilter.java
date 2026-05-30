package com.vin.filter;

import com.vin.service.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class RateLimitingFilter implements GlobalFilter, Ordered {

    private final RedisService redisService;

    /*
     * =========================================================
     * RATE LIMIT CONFIGURATION
     * =========================================================
     */
    private static final long MAX_REQUESTS = 10;

    private static final long WINDOW_SECONDS = 60;

    @Override
    public Mono<Void> filter(
            ServerWebExchange exchange,
            org.springframework.cloud.gateway.filter.GatewayFilterChain chain) {

        /*
         * =========================================================
         * REQUEST PATH
         * =========================================================
         */
        String path =
                exchange.getRequest()
                        .getURI()
                        .getPath();

        /*
         * =========================================================
         * SKIP PUBLIC ROUTES
         * =========================================================
         */
        if (path.contains("/actuator")
                || path.contains("/eureka")) {

            return chain.filter(exchange);
        }

        /*
         * =========================================================
         * CLIENT IP
         * =========================================================
         */
        String clientIp =
                exchange
                        .getRequest()
                        .getRemoteAddress()
                        .getAddress()
                        .getHostAddress();

        /*
         * =========================================================
         * REDIS KEY
         * =========================================================
         */
        String redisKey =
                "rate_limit:" + clientIp;

        /*
         * =========================================================
         * CHECK RATE LIMIT
         * =========================================================
         */
        return redisService
                .isAllowed(
                        redisKey,
                        WINDOW_SECONDS,
                        MAX_REQUESTS
                )

                .flatMap(isAllowed -> {

                    /*
                     * =========================================================
                     * BLOCK REQUEST
                     * =========================================================
                     */
                    if (!isAllowed) {

                        log.error(
                                "Rate limit exceeded for IP={}",
                                clientIp
                        );

                        exchange.getResponse()
                                .setStatusCode(
                                        HttpStatus.TOO_MANY_REQUESTS
                                );

                        return exchange
                                .getResponse()
                                .setComplete();
                    }

                    /*
                     * =========================================================
                     * ALLOW REQUEST
                     * =========================================================
                     */
                    return chain.filter(exchange);
                });
    }

    @Override
    public int getOrder() {

        /*
         * =========================================================
         * EXECUTE BEFORE JWT FILTER
         * =========================================================
         */
        return -3;
    }
}