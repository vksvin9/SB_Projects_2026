package com.vin.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import com.vin.client.ApacheHttpClientClient;
import com.vin.client.FeignClientApi;
import com.vin.client.HttpUrlConnectionClient;
import com.vin.client.JavaHttpClientClient;
import com.vin.client.OkHttpClientClient;
import com.vin.client.RestTemplateClient;
import com.vin.client.WebClientClient;
import com.vin.exception.ApiException;
import com.vin.model.Post;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

	private final HttpUrlConnectionClient httpClient;
	private final RestTemplateClient restClient;
	private final JavaHttpClientClient javaHttpClient;
	private final WebClientClient webClient;
	private final FeignClientApi feignClient;
	private final ApacheHttpClientClient apacheClient;
	private final OkHttpClientClient okHttpClient;

	public List<Post> getPosts() {
		return httpClient.getPosts();
	}

	public List<Post> getPostsUsingRestTemplate() {
		return restClient.getPosts();
	}

	public List<Post> getPostsJavaHttpClient() {
	    return javaHttpClient.getPosts();
	}
//	public List<Post> getPostsUsingWebClient() {
//		return webClient.getPosts();
//	}
	
	// ===== RESILIENT WebClient =====
	@Retry(name = "apiRetry")
	@CircuitBreaker(name = "apiCB", fallbackMethod = "webClientFallback")
	public List<Post> getPostsUsingWebClient() {
	    return webClient.getPosts();
	}

	public List<Post> webClientFallback(Exception ex) {
	    throw new ApiException("WebClient fallback triggered", ex);
	}

//	public List<Post> getPostsUsingFeign() {
//		return feignClient.getPosts();
//	}

	// ===== RESILIENT FEIGN =====
	@Retry(name = "apiRetry")
	@CircuitBreaker(name = "apiCB", fallbackMethod = "feignFallback")
	public List<Post> getPostsUsingFeign() {
		return feignClient.getPosts();
	}

	public List<Post> feignFallback(Exception ex) {
		throw new ApiException("Feign fallback triggered", ex);
	}
	
	@TimeLimiter(name = "apiTimeout")
	public CompletableFuture<List<Post>> getPostsWithTimeout() {
	    return CompletableFuture.supplyAsync(() -> feignClient.getPosts());
	}
	

	public List<Post> getPostsApache() {
	    return apacheClient.getPosts();
	}

	public List<Post> getPostsOkHttp() {
	    return okHttpClient.getPosts();
	}
}