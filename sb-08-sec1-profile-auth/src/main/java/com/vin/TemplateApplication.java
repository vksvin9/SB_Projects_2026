package com.vin;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.vin.entity.User;
import com.vin.repository.UserRepository;

@SpringBootApplication
public class TemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(TemplateApplication.class, args);
	}
	@Bean
	CommandLineRunner init(UserRepository repo, PasswordEncoder encoder) {
	    return args -> {

	        // ADMIN
	        repo.save(User.builder()
	                .username("admin")
	                .password(encoder.encode("admin"))
	                .role("ROLE_ADMIN")
	                .build());

	        // NORMAL USER
	        repo.save(User.builder()
	                .username("user")
	                .password(encoder.encode("user"))
	                .role("ROLE_USER")
	                .build());
	    };
	}
}
