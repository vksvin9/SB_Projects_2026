package com.vin.filter;

import com.vin.service.RedisService;
import com.vin.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements GlobalFilter, Ordered {

    private final JwtUtil jwtUtil;

    private final RedisService redisService;

    @Override
    public Mono<Void> filter(
            ServerWebExchange exchange,
            org.springframework.cloud.gateway.filter.GatewayFilterChain chain) {

        String path = exchange.getRequest().getURI().getPath();

        /*
         * =========================================================
         * PUBLIC ROUTES
         * =========================================================
         */
        if (path.contains("/auth/")
                || path.contains("/actuator")
                || path.contains("/eureka")
                || path.contains("/redis")) {

            return chain.filter(exchange);
        }

        /*
         * =========================================================
         * AUTHORIZATION HEADER VALIDATION
         * =========================================================
         */
        String authHeader =
                exchange.getRequest()
                        .getHeaders()
                        .getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader == null
                || !authHeader.startsWith("Bearer ")) {

            log.error("Missing Authorization Header");

            exchange.getResponse()
                    .setStatusCode(HttpStatus.UNAUTHORIZED);

            return exchange.getResponse().setComplete();
        }

        /*
         * =========================================================
         * EXTRACT JWT TOKEN
         * =========================================================
         */
        String token = authHeader.substring(7);

        /*
         * =========================================================
         * CHECK REDIS TOKEN BLACKLIST
         * =========================================================
         */
        return redisService
                .get("blacklist:" + token)

                .flatMap(blacklistedToken -> {

                    log.error("JWT Token Blacklisted");

                    exchange.getResponse()
                            .setStatusCode(HttpStatus.UNAUTHORIZED);

                    return exchange.getResponse().setComplete();
                })

                /*
                 * =========================================================
                 * VALIDATE JWT TOKEN
                 * =========================================================
                 */
                .switchIfEmpty(
                        Mono.defer(() -> {

                            if (!jwtUtil.isTokenValid(token)) {

                                log.error("Invalid JWT Token");

                                exchange.getResponse()
                                        .setStatusCode(HttpStatus.UNAUTHORIZED);

                                return exchange
                                        .getResponse()
                                        .setComplete();
                            }

                            log.info("JWT Token Valid");

                            return chain.filter(exchange);
                        })
                );
    }

    @Override
    public int getOrder() {

        return -2;
    }
}