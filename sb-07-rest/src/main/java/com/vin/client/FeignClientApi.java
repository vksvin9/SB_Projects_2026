package com.vin.client;

import com.vin.model.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(
        name = "json-placeholder-client",
        url = "${api.base-url}",
        configuration = com.vin.config.FeignConfig.class
)
public interface FeignClientApi {
    @GetMapping("/posts")
    List<Post> getPosts();
}