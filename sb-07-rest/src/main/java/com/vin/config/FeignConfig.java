package com.vin.config;

import com.vin.exception.ApiException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new ErrorDecoder() {

            @Override
            public Exception decode(String methodKey, Response response) {
                return new ApiException(
                        "Feign error: " + response.status() + " for " + methodKey
                );
            }
        };
    }
}