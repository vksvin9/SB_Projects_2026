package com.vin.config;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http)
            throws Exception {

        http

                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth

                        // PUBLIC PAGES
                        .requestMatchers(
                                "/",
                                "/login",
                                "/register",
                                "/post/**",
                                "/css/**",
                                "/js/**",
                                "/images/**"
                        )

                        .permitAll()

                        // PROTECTED PAGES
                        .requestMatchers(
                                "/dashboard",
                                "/create",
                                "/save",
                                "/edit/**",
                                "/delete/**",
                                "/comment/**",
                                "/profile/**"
                        )

                        .authenticated()

                        .anyRequest()

                        .permitAll()
                )

                .formLogin(form -> form

                        .loginPage("/login")

                        .defaultSuccessUrl(
                                "/dashboard",
                                true
                        )

                        .permitAll()
                )

                .logout(logout -> logout

                        .logoutSuccessUrl("/")

                        .permitAll()
                );

        return http.build();
    }
}