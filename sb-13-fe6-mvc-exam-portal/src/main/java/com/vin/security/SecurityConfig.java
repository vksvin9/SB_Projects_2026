package com.vin.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.io.IOException;

@Configuration
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsService(
            PasswordEncoder passwordEncoder) {

        UserDetails admin =
                User.builder()
                        .username("admin")
                        .password(
                                passwordEncoder.encode(
                                        "admin123"))
                        .roles("ADMIN")
                        .build();

        UserDetails student =
                User.builder()
                        .username("student")
                        .password(
                                passwordEncoder.encode(
                                        "student123"))
                        .roles("STUDENT")
                        .build();

        return new InMemoryUserDetailsManager(
                admin,
                student
        );
    }

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

                        .requestMatchers(
                                "/",
                                "/login",
                                "/authenticate",
                                "/css/**",
                                "/js/**",
                                "/images/**",
                                "/h2-console/**"
                        ).permitAll()

                        .requestMatchers("/admin/**")
                        .hasRole("ADMIN")

                        .requestMatchers("/student/**")
                        .hasRole("STUDENT")

                        .anyRequest()
                        .authenticated()
                )

                .formLogin(form -> form

                        .loginPage("/login")

                        .loginProcessingUrl(
                                "/authenticate")

                        .successHandler(
                                this::loginSuccessHandler
                        )

                        .failureUrl(
                                "/login?error=true")

                        .permitAll()
                )

                .logout(logout -> logout

                        .logoutUrl("/logout")

                        .logoutSuccessUrl(
                                "/login?logout=true")

                        .permitAll()
                )

                .sessionManagement(session -> session

                        .maximumSessions(1)

                        .maxSessionsPreventsLogin(false)
                )

                .exceptionHandling(ex -> ex

                        .accessDeniedPage(
                                "/access-denied")
                )

                .httpBasic(
                        Customizer.withDefaults());

        http.headers(headers ->
                headers.frameOptions(
                        frame -> frame.disable()));

        return http.build();
    }

    private void loginSuccessHandler(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication)

            throws IOException, ServletException {

        boolean isAdmin =
                authentication.getAuthorities()
                        .stream()
                        .anyMatch(auth ->
                                auth.getAuthority()
                                        .equals(
                                                "ROLE_ADMIN"));

        boolean isStudent =
                authentication.getAuthorities()
                        .stream()
                        .anyMatch(auth ->
                                auth.getAuthority()
                                        .equals(
                                                "ROLE_STUDENT"));

        if (isAdmin) {

            response.sendRedirect(
                    "/admin/dashboard");

        } else if (isStudent) {

            response.sendRedirect(
                    "/student/dashboard");

        } else {

            response.sendRedirect("/");
        }
    }
}