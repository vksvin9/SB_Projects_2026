package com.vin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService uds;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf().disable();

        http.authorizeHttpRequests(auth -> auth

            // PUBLIC
            .requestMatchers("/h2-console/**").permitAll()
            .requestMatchers("/auth/register").permitAll()
            
            // ADMIN ONLY
            .requestMatchers("/users/**").hasRole("ADMIN")
            .requestMatchers(HttpMethod.POST, "/students/**").hasRole("ADMIN")
            .requestMatchers(HttpMethod.PUT, "/students/**").hasRole("ADMIN")
            .requestMatchers(HttpMethod.DELETE, "/students/**").hasRole("ADMIN")

            // USER + ADMIN
            .requestMatchers(HttpMethod.GET, "/students/**").hasAnyRole("USER", "ADMIN")

            .anyRequest().authenticated()
        );

        http.httpBasic();

        http.headers().frameOptions().disable();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}