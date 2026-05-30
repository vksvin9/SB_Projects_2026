package com.vin.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Range;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisService {

    private final ReactiveStringRedisTemplate redisTemplate;

    /*
     * =========================================================
     * SAVE VALUE
     * =========================================================
     */
    public Mono<Boolean> save(
            String key,
            String value,
            long timeoutSeconds) {

        log.info(
                "Saving Redis key={}",
                key
        );

        return redisTemplate
                .opsForValue()
                .set(
                        key,
                        value,
                        Duration.ofSeconds(timeoutSeconds)
                );
    }

    /*
     * =========================================================
     * GET VALUE
     * =========================================================
     */
    public Mono<String> get(String key) {

        log.info(
                "Fetching Redis key={}",
                key
        );

        return redisTemplate
                .opsForValue()
                .get(key);
    }

    /*
     * =========================================================
     * DELETE VALUE
     * =========================================================
     */
    public Mono<Boolean> delete(String key) {

        log.info(
                "Deleting Redis key={}",
                key
        );

        return redisTemplate
                .delete(key)
                .map(count -> count > 0);
    }

    /*
     * =========================================================
     * SLIDING WINDOW RATE LIMITING
     * =========================================================
     *
     * FLOW:
     *
     * 1. Remove requests older than rolling window
     * 2. Add current request timestamp
     * 3. Count active requests in current rolling window
     * 4. Apply Redis expiration
     * 5. Return whether request is allowed
     *
     * EXAMPLE:
     *
     * limit = 10 requests
     * window = 60 seconds
     *
     * At 10:01:30
     * only requests after 10:00:30 are counted
     *
     * This is a true rolling/sliding window.
     * =========================================================
     */
    public Mono<Boolean> isAllowed(
            String key,
            long windowSeconds,
            long maxRequests) {

        /*
         * =========================================================
         * CURRENT REQUEST TIMESTAMP
         * =========================================================
         */
        long currentTime =
                System.currentTimeMillis();

        /*
         * =========================================================
         * WINDOW START TIMESTAMP
         *
         * Example:
         *
         * currentTime = 10:01:30
         * windowStart = 10:00:30
         *
         * Requests older than this will be removed.
         * =========================================================
         */
        long windowStart =
                currentTime - (windowSeconds * 1000);

        /*
         * =========================================================
         * UNIQUE REQUEST ID
         *
         * Redis ZSET requires unique members.
         * =========================================================
         */
        String requestId =
                currentTime + "-" + UUID.randomUUID();

        log.info(
                "Sliding window rate limit check key={} requestId={}",
                key,
                requestId
        );

        /*
         * =========================================================
         * REDIS ZSET FLOW
         * =========================================================
         */
        return redisTemplate

                /*
                 * =========================================================
                 * ACCESS REDIS SORTED SET OPERATIONS
                 * =========================================================
                 */
                .opsForZSet()

                /*
                 * =========================================================
                 * REMOVE OLD REQUESTS
                 *
                 * Equivalent Redis Command:
                 *
                 * ZREMRANGEBYSCORE key 0 windowStart
                 *
                 * Removes requests outside rolling window.
                 * =========================================================
                 */
                .removeRangeByScore(
                        key,
                        Range.closed(
                                0.0,
                                (double) windowStart
                        )
                )

                /*
                 * =========================================================
                 * ADD CURRENT REQUEST
                 *
                 * Equivalent Redis Command:
                 *
                 * ZADD key currentTime requestId
                 *
                 * score  = timestamp
                 * member = request id
                 * =========================================================
                 */
                .then(
                        redisTemplate
                                .opsForZSet()
                                .add(
                                        key,
                                        requestId,
                                        currentTime
                                )
                )

                /*
                 * =========================================================
                 * COUNT ACTIVE REQUESTS
                 *
                 * Equivalent Redis Command:
                 *
                 * ZCARD key
                 *
                 * Counts requests inside current rolling window.
                 * =========================================================
                 */
                .then(
                        redisTemplate
                                .opsForZSet()
                                .size(key)
                )

                /*
                 * =========================================================
                 * APPLY REDIS KEY EXPIRATION
                 *
                 * Automatically cleans unused keys.
                 * =========================================================
                 */
                .flatMap(requestCount -> {

                    log.info(
                            "Sliding window request count key={} count={}",
                            key,
                            requestCount
                    );

                    return redisTemplate

                            /*
                             * =========================================================
                             * SET EXPIRATION
                             * =========================================================
                             */
                            .expire(
                                    key,
                                    Duration.ofSeconds(windowSeconds)
                            )

                            /*
                             * =========================================================
                             * ALLOW / BLOCK DECISION
                             *
                             * TRUE  = request allowed
                             * FALSE = rate limit exceeded
                             * =========================================================
                             */
                            .thenReturn(
                                    requestCount <= maxRequests
                            );
                });
    }
}