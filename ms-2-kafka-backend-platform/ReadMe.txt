Kafka Backend Microervice - Get the project structure and development road map
		Not a modular project & No common liabrary all services are completely independent
		Service Template ***** - common in all services - package(com.vin),java 21, Oracle pod, yml, Lombok, devtools, Common Resp Format, handle Exception globally
		Services-use SeverceTemplate classes and dependencies(create-user-service (Producer), email-service (Consumer 1), audit-service (Consumer 2),discovery-server, api-gateway, ,JWTAuth,Apigateway,CacheReddis,OthersReqMicroServices)
		No config server or No .env file => insted use this approch - name: ${APP_NAME:ms-4-jwt-auth-service}
		Logs - Interceptor/AOP(req-in/resp-out,internal db/url calls), Structured JSON logs with Logback Correlation IDs (traceId, spanId) Micrometer Tracing + Zipkin/Jaeger ELK stack (Elasticsearch, Logstash, Kibana) 
		Multi Stage Docker File (BuildJar>BuildImage>CreateContainer>Deploy)>K8S>CI/CD
		NOTE: 
			1)always give complete file while dvelopment if new file or suggesting modification - if req ask for previouse/existing file content for reference
			2) use below DB creds
				## login via sqlplus (from host shell)
				docker exec -it oracle-db sqlplus system/Oracle123@FREEPDB1
				conn system/Oracle123@FREEPDB1;
				docker exec -it oracle-db sqlplus user_db/password@FREEPDB1
				conn user_db/password@FREEPDB1;
				docker exec -it oracle-db sqlplus app_user/app_pass@FREEPDB1
				conn app_user/app_pass@FREEPDB1;
# Kafka Backend Microservices Platform
## Complete Project Structure and Development Roadmap
This roadmap defines a **production-grade event-driven microservices architecture** using:
* Java 21 
* Spring Boot 3.x
* Oracle Database (Oracle Pod)
* Apache Kafka
* Redis Cache
* Eureka Discovery
* API Gateway
* JWT Authentication
* Docker
* Kubernetes
* CI/CD
* ELK + Zipkin/Jaeger
All services are **fully independent projects** (not Maven multi-module, no shared common library).
---
# 1. Architecture Overview
Client
   ↓
API Gateway
   ↓
JWT Authentication
   ↓
Create User Service (Producer)
   ↓
Oracle Database
   ↓
Kafka Topic: user-created
   ↓             ↓
Email Service    Audit Service
API Gateway ↔ Redis Cache
All Services → Discovery Server
All Services → ELK / Zipkin
---
# 2. Final Service List
## Core Services
0. `service-template`
1. `discovery-server`
2. `api-gateway`
3. `jwt-auth-service`
4. `create-user-service`
5. `email-service`
6. `audit-service`
## Infrastructure Containers
7. [Apache Kafka](https://kafka.apache.org?utm_source=chatgpt.com)
8. [Redis](https://redis.io?utm_source=chatgpt.com)
9. Oracle Corporation Database
## Optional Request-Based Services
* `notification-service`
* `report-service`
* `payment-service`
* `order-service`
* `product-service`
* `inventory-service`
---
# 3. Standards Used in Every Service
Each service contains its own:
* Java 21
* `application.yml`
* Lombok
* Spring Boot DevTools
* Common API response format
* Global exception handling
* Validation
* Request/response logging
* AOP-based internal method logging
* Structured JSON logs
* `traceId` and `spanId`
* Health checks
* Multi-stage Dockerfile
* Kubernetes manifests
* CI/CD pipeline
---
# 4. Independent Repository Structure
kafka-backend-platform/
│
├── service-template/
├── discovery-server/
├── api-gateway/
├── jwt-auth-service/
├── create-user-service/
├── email-service/
├── audit-service/
├── notification-service/
├── report-service/
├── payment-service/
│
├── infrastructure/
│   ├── docker-compose/
│   ├── k8s/
│   ├── monitoring/
│   ├── scripts/
│   └── env/
│
└── .github/
	└── workflows/
==========================================================================================================
# Phase-Wise Development Roadmap (Build and Test One by One)
This roadmap is optimized for your 8 GB RAM system and allows you to complete, validate, and stabilize each layer before moving to the next.
---
# Phase 1: Foundation Template (Single Service Standards)
## Goal
Create a reusable service template that every microservice will follow independently.
## Build
Create a simple Spring Boot project with:
* Java 21
* `application.yml`
* Lombok
* DevTools
* Common API response format
* Global exception handling
* Validation
* Health endpoint
* Structured JSON logging
* Request/response interceptor
* AOP method logging
## Test
* Start the application
* Call `/actuator/health`
* Test a sample REST endpoint
* Verify common response format
* Verify exception handling
* Verify JSON logs with `traceId`
## Deliverable
A standard project structure to replicate for all services.
---
refer this pom for next projects
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.5.14</version>
		<relativePath /> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.vin</groupId>
	<artifactId>ms-1-service-template</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name />
	<description />
	<url />
	<licenses>
		<license />
	</licenses>
	<developers>
		<developer />
	</developers>
	<scm>
		<connection />
		<developerConnection />
		<tag />
		<url />
	</scm>
	<properties>
		<java.version>21</java.version>
	</properties>
	<dependencies>
		<!-- Spring Web -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<!-- Validation -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>
		<!-- Actuator -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
		<!-- Distributed Tracing (traceId and spanId) -->
		<dependency>
			<groupId>io.micrometer</groupId>
			<artifactId>micrometer-tracing-bridge-brave</artifactId>
		</dependency>
		<!-- AOP -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-aop</artifactId>
		</dependency>
		<!-- Lombok -->
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<!-- DevTools -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
		<!-- JSON Logging -->
		<dependency>
			<groupId>net.logstash.logback</groupId>
			<artifactId>logstash-logback-encoder</artifactId>
			<version>8.1</version>
		</dependency>
		<!-- Testing -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>
	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>
</project>
==========================================================================================================
# Phase 2: Service Discovery
## Project
`discovery-server`
## Build
* Eureka Server
* Dockerfile
* Kubernetes manifests
## Test
* Start the server
* Open Eureka dashboard
* Verify server health
## Deliverable
All future services can register here.
---
Phase 2: Discovery Server (Eureka Server)
We will now create the first real microservice: discovery-server.
This service allows all other services to register and discover each other dynamically. This is the next milestone in your platform roadmap.
1. Create Project Using Spring Initializr
Create a new Spring Boot project inside:
ms-2-kafka-backend-platform/
Project name:
ms-2-discovery-server
Dependencies:
Eureka Server
Spring Boot Actuator
Lombok
2. Final Structure
ms-2-kafka-backend-platform/
├── ms-1-service-template/
└── ms-2-discovery-server/
3. Copy Reusable Files From Template
Copy these files from ms-1-service-template to ms-2-discovery-server:
.gitignore
src/main/resources/logback-spring.xml
Also copy package folders if desired (config, aop, exception, etc.) as a reusable base.
5. Update application.yml
File:
src/main/resources/application.yml
spring:
  application:
    name: ${APP_NAME:ms-2-discovery-server}
  cloud:
    compatibility-verifier:
      enabled: false
server:
  port: ${APP_PORT:8761}
# EUREKA CONFIGURATION
eureka:
  instance:
    hostname: localhost
    prefer-ip-address: true
    instance-id: ${spring.application.name}:${server.port}
  client:
    register-with-eureka: false
    fetch-registry: false
# ACTUATOR CONFIGURATION
management:
  endpoints:
    web:
      exposure:
        include:
          - health
          - info
  endpoint:
    health:
      show-details: always
  info:
    env:
      enabled: true
  tracing:
    sampling:
      probability: 1.0
# APPLICATION INFO
info:
  app:
    name: ${APP_NAME:ms-2-discovery-server}
    version: ${APP_VERSION:1.0.0}
    description: ${APP_DESCRIPTION:Eureka Discovery Server}
# LOGGING
logging:
  level:
    root: INFO
    com.vin: DEBUG
6. Main Application Class
Update the generated application class:
package com.vin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
@SpringBootApplication
@EnableEurekaServer
public class DiscoveryServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(DiscoveryServerApplication.class, args);
	}
}
7. Add Tracing Dependency
Ensure pom.xml contains:
<dependency>
	<groupId>io.micrometer</groupId>
	<artifactId>micrometer-tracing-bridge-brave</artifactId>
</dependency>
8. Run the Discovery Server
From:
ms-2-kafka-backend-platform/ms-2-discovery-server
run:
mvn clean spring-boot:run
9. Verify Endpoints
Eureka Dashboard
Open:
http://localhost:8761
Health
Open:
http://localhost:8761/actuator/health
Info
Open:
http://localhost:8761/actuator/info
10. Expected Result
The Eureka dashboard opens and shows:
No instances available
This is correct until other services register.
---
==========================================================================================================
# Phase 3: API Gateway
## Project
`api-gateway`
## Build
* Service registration with Eureka
* Route configuration
* JWT validation filter
* Redis-based caching/rate limiting (optional initially)
## Test
* Access downstream services via gateway
* Validate JWT protection
* Confirm routing
## Deliverable
Single entry point for clients.
---
Phase 3: API Gateway
Goal
Build a centralized entry point for all microservices with:
Eureka registration
Dynamic routing
Request tracing
Gateway logging
JWT-ready filter structure
Future Redis rate limiting support
Your gateway will become:
Client
   ↓
API Gateway
   ↓
Microservices
Project Structure
Create new independent project:
ms-3-api-gateway
Final structure:
ms-2-kafka-backend-platform/
├── ms-1-service-template/
├── ms-2-discovery-server/
└── ms-3-api-gateway/
Step 1: Create Project
Use Spring Initializr.
Project Details
Field	Value
Group	com.vin
Artifact	ms-3-api-gateway
Java	21
Spring Boot	3.5.x
Step 2: Add Dependencies
Add:
Spring Cloud Gateway
Eureka Discovery Client
Spring Boot Actuator
Lombok
Validation
DevTools
Step 3: pom.xml
Step 4: Copy Reusable Foundation
Copy from ms-1-service-template:
.gitignore
logback-spring.xml
Also copy reusable packages:
config
aop
exception
interceptor
dto
Do NOT copy service-specific logic.
Step 6: application.yml
File:
spring:
  application:
    name: ${APP_NAME:ms-3-api-gateway}
  cloud:
    compatibility-verifier:
      enabled: false
    gateway:
      # AUTO DISCOVERY ROUTING
      discovery:
        locator:
          enabled: true
          lower-case-service-id: true
      # GATEWAY ROUTES
      routes:
        # EUREKA DASHBOARD ROUTE
        - id: discovery-server
          uri: lb://ms-2-discovery-server
          predicates:
            - Path=/eureka/web
          filters:
            - SetPath=/
        # EUREKA STATIC CONTENT
        - id: discovery-server-static
          uri: lb://ms-2-discovery-server
          predicates:
            - Path=/eureka/**
        # JWT AUTH SERVICE
        - id: jwt-auth-service
          uri: lb://ms-4-jwt-auth-service
          predicates:
            - Path=/auth/**
        # CREATE USER SERVICE
        - id: create-user-service
          uri: lb://ms-5-create-user-service
          predicates:
            - Path=/users/**
server:
  port: ${APP_PORT:8080}
# EUREKA CONFIGURATION
eureka:
  instance:
    hostname: localhost
    prefer-ip-address: true
    ip-address: 127.0.0.1
    instance-id: ${spring.application.name}:${server.port}
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      defaultZone: ${DISCOVERY_SERVER_URL:http://localhost:8761/eureka}
# JWT CONFIGURATION
jwt:
  secret-key: ${JWT_SECRET_KEY:MySuperSecureJwtSecretKeyMySuperSecureJwtSecretKey}
# ACTUATOR CONFIGURATION
management:
  endpoints:
    web:
      exposure:
        include:
          - health
          - info
          - gateway
  endpoint:
    health:
      show-details: always
    gateway:
      access: unrestricted
  info:
    env:
      enabled: true
  tracing:
    sampling:
      probability: 1.0
# APPLICATION INFO
info:
  app:
    name: ${APP_NAME:ms-3-api-gateway}
    version: ${APP_VERSION:1.0.0}
    description: ${APP_DESCRIPTION:Spring Cloud API Gateway}
# LOGGING CONFIGURATION
logging:
  level:
    root: INFO
    com.vin: DEBUG
    org.springframework.cloud.gateway: INFO
    reactor.netty: INFO
    org.springframework.web: INFO
    org.springframework.security: INFO
  pattern:
    console: "%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n"
Step 7: Main Application
File:
package com.vin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class ApiGatewayApplication {
	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
}
Step 8: Add Gateway Request Logging Filter
Create:
com.vin.filter.LoggingFilter
package com.vin.filter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
@Slf4j
@Component
public class LoggingFilter implements GlobalFilter, Ordered {
	@Override
	public Mono<Void> filter(
			org.springframework.web.server.ServerWebExchange exchange,
			org.springframework.cloud.gateway.filter.GatewayFilterChain chain) {
		String path = exchange.getRequest().getURI().getPath();
		String method = exchange.getRequest().getMethod().name();
		log.info("Gateway Request -> method={}, path={}", method, path);
		return chain.filter(exchange)
				.then(Mono.fromRunnable(() ->
						log.info(
								"Gateway Response -> status={}",
								exchange.getResponse().getStatusCode()
						)));
	}
	@Override
	public int getOrder() {
		return -1;
	}
}
Step 9: Enable Discovery Registration
Start services in order:
1. Discovery Server
Run:
mvn spring-boot:run
Verify:
http://localhost:8761
2. API Gateway
Run:
mvn spring-boot:run
Step 10: Verify Eureka Registration
Open:
http://localhost:8761
Expected:
MS-3-API-GATEWAY
should appear.
Step 11: Verify Gateway Endpoints
Health
http://localhost:8080/actuator/health
Gateway Routes
http://localhost:8080/actuator/gateway/routes
Eureka Through Gateway
http://localhost:8080/eureka/web
Expected:
Eureka dashboard should open through gateway routing.
Expected Logs
You should now see:
{
  "message":"Gateway Request -> method=GET, path=/actuator/health",
  "traceId":"...",
  "spanId":"..."
}
Architecture After Phase 3
Client
   ↓
ms-3-api-gateway
   ↓
ms-2-discovery-server
Validation Checklist
Infrastructure
Eureka server running
Gateway registered in Eureka
Gateway
Dynamic routing enabled
Gateway actuator working
Logging filter working
Trace logging working
Standards
JSON logs
actuator
structured logging
tracing enabled
Deliverable
You now have:
centralized API Gateway
service discovery integration
gateway-level request logging
tracing-ready architecture
scalable entry point for all future services
Next Phase
After this is fully tested and stable:
Phase 4: JWT Authentication Service
This will introduce:
user registration
login
JWT access tokens
refresh tokens
Oracle integration
secured APIs
gateway JWT validation flow
---
Phase 3 — Continue Gateway Testing + JWT Filter Skeleton
Next step:
Implement complete JWT-ready gateway filter skeleton before Phase 4 auth service.
Step 1 — Add Required Dependencies
Step 3 — Update application.yml
Step 4 — Create JWT Utility
File
src/main/java/com/vin/util/JwtUtil.java
Code
package com.vin.util;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
@Component
@RequiredArgsConstructor
public class JwtUtil {
    @Value("${jwt.secret-key}")
    private String secretKey;
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(
                secretKey.getBytes(StandardCharsets.UTF_8)
        );
    }
    public Claims extractClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    public boolean isTokenValid(String token) {
        try {
            extractClaims(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
Step 5 — Create JWT Authentication Filter
File
src/main/java/com/vin/filter/JwtAuthenticationFilter.java
Code
package com.vin.filter;
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
    @Override
    public Mono<Void> filter(
            ServerWebExchange exchange,
            org.springframework.cloud.gateway.filter.GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        /*
         * Public Routes
         */
        if (path.contains("/auth/")
                || path.contains("/actuator")
                || path.contains("/eureka")) {
            return chain.filter(exchange);
        }
        String authHeader =
                exchange.getRequest()
                        .getHeaders()
                        .getFirst(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            log.error("Missing Authorization Header");
            exchange.getResponse()
                    .setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        String token = authHeader.substring(7);
        if (!jwtUtil.isTokenValid(token)) {
            log.error("Invalid JWT Token");
            exchange.getResponse()
                    .setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        log.info("JWT Token Valid");
        return chain.filter(exchange);
    }
    @Override
    public int getOrder() {
        return -2;
    }
}
Step 6 — Final Package Structure
com.vin
├── filter
│   ├── LoggingFilter.java
│   └── JwtAuthenticationFilter.java
│
├── util
│   └── JwtUtil.java
Step 7 — Run Services
Start Discovery Server
mvn spring-boot:run
Start API Gateway
mvn spring-boot:run
Step 8 — Verify Gateway Registration
Open:
http://localhost:8761
Verify:
MS-3-API-GATEWAY
appears.
Step 9 — Verify Public Routes
These should work WITHOUT token:
Health
http://localhost:8080/actuator/health
Gateway Routes
http://localhost:8080/actuator/gateway/routes
Eureka Through Gateway
http://localhost:8080/eureka/web
Step 10 — Verify JWT Protection
Try any protected path:
http://localhost:8080/api/test
Expected:
401 Unauthorized
Expected Logs
Missing Token
{
  "message":"Missing Authorization Header"
}
Invalid Token
{
  "message":"Invalid JWT Token"
}
Valid Token
{
  "message":"JWT Token Valid"
}
Architecture After This Step
Client
   ↓
API Gateway
   ├── request logging
   ├── JWT validation skeleton
   ├── tracing
   ├── Eureka routing
   ↓
Future Auth Service
Important Notes
Current filter only validates:
token exists
token signature valid
NOT yet implemented:
roles
permissions
token expiry response body
refresh token flow
Redis blacklist
rate limiting
Those come after:
Phase 4 — JWT Authentication Service
Next Phase
After gateway JWT skeleton is stable:
Phase 4 — JWT Authentication Service
Will include:
Oracle integration
user entity
login
registration
JWT generation
refresh token
BCrypt password encryption
RBAC
secured APIs
---
After JWT service is complete:
Implement in gateway:
Client
   ↓
API Gateway
   ├── IP filtering
   ├── rate limiting
   ├── request logging
   ├── JWT validation
   └── bot protection
   ↓
JWT Auth Service
   ├── authenticate user
   ├── issue token
   └── validate credentials
==========================================================================================================
# Phase 4: JWT Authentication Service
## Project
`jwt-auth-service`
## Build
* User registration/login
* JWT access and refresh tokens
* Role-based authorization
* Oracle persistence
## Test
* Register user
* Login
* Receive JWT token
* Validate secured endpoint
## Deliverable
Central authentication service.
---
Phase 4 — JWT Authentication Service (ms-4-jwt-auth-service)
This phase builds the centralized authentication microservice for:
User Registration
Login Authentication
JWT Access Token
Refresh Token
Role-Based Access
Oracle Persistence
BCrypt Password Encryption
Eureka Registration
Gateway Integration
Architecture after this phase:
Client
   ↓
API Gateway
   ↓
JWT Auth Service
   ↓
Oracle DB
Your gateway JWT filter from Phase 3 will validate tokens generated here.
Step 1 — Create Project
Project Name:
ms-4-jwt-auth-service
Group:
com.vin
Java:
21
Spring Boot:
3.5.x
Step 2 — Add Dependencies
Dependencies required:
Spring Web
Spring Security
Spring Data JPA
Validation
Eureka Discovery Client
Oracle Driver
Actuator
Lombok
DevTools
Step 3 — Final Project Structure
ms-4-jwt-auth-service/
│
├── src/main/java/com/vin
│   ├── config
│   ├── controller
│   ├── dto
│   ├── entity
│   ├── repository
│   ├── security
│   ├── service
│   ├── exception
│   ├── util
│   └── JwtAuthServiceApplication.java
│
├── src/main/resources
│   ├── application.yml
│   └── logback-spring.xml
├── Dockerfile
└── pom.xml
Step 4 — Copy Shared Files From Template
Copy from ms-1-service-template:
.gitignore
logback-spring.xml
Copy reusable packages:
config
aop
exception
dto
interceptor
Do NOT copy service-specific logic.
Step 6 — application.yml
File:
spring:
  application:
    name: ${APP_NAME:ms-4-jwt-auth-service}
  datasource:
    url: ${DB_URL:jdbc:oracle:thin:@localhost:1521/FREEPDB1}
    username: ${DB_USERNAME:app_user}
    password: ${DB_PASSWORD:app_pass}
    driver-class-name: oracle.jdbc.OracleDriver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.OracleDialect
        format_sql: true
  cloud:
    compatibility-verifier:
      enabled: false
server:
  port: ${APP_PORT:8081}
# EUREKA CONFIGURATION
eureka:
  instance:
    hostname: localhost
    prefer-ip-address: true
    ip-address: 127.0.0.1
    instance-id: ${spring.application.name}:${server.port}
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      defaultZone: ${DISCOVERY_SERVER_URL:http://localhost:8761/eureka}
# JWT CONFIGURATION
jwt:
  secret-key: ${JWT_SECRET_KEY:MySuperSecureJwtSecretKeyMySuperSecureJwtSecretKey}
  access-token-expiration: ${JWT_ACCESS_TOKEN_EXPIRATION:3600000}
  refresh-token-expiration: ${JWT_REFRESH_TOKEN_EXPIRATION:604800000}
# ACTUATOR CONFIGURATION
management:
  endpoints:
    web:
      exposure:
        include:
          - health
          - info
  endpoint:
    health:
      show-details: always
  info:
    env:
      enabled: true
  tracing:
    sampling:
      probability: 1.0
# APPLICATION INFO
info:
  app:
    name: ${APP_NAME:ms-4-jwt-auth-service}
    version: ${APP_VERSION:1.0.0}
    description: ${APP_DESCRIPTION:JWT Authentication Service}
# LOGGING CONFIGURATION
logging:
  level:
    root: INFO
    com.vin: DEBUG
    org.springframework.security: INFO
    org.springframework.web: INFO
    org.hibernate.SQL: DEBUG
    org.hibernate.type.descriptor.sql: TRACE
  pattern:
    console: "%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n"
Step 7 — Main Application Class
package com.vin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class JwtAuthServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(JwtAuthServiceApplication.class, args);
    }
}
Step 8 — Create Role Enum
File:
com.vin.entity.Role.java
package com.vin.entity;
public enum Role {
    USER,
    ADMIN
}
Step 9 — Create User Entity
File:
com.vin.entity.User.java
package com.vin.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "APP_USERS")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
}
Step 10 — Create Repository
File:
com.vin.repository.UserRepository.java
package com.vin.repository;
import com.vin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface UserRepository
        extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
Step 11 — Create DTOs
Register Request
package com.vin.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class RegisterRequest {
    @NotBlank
    private String name;
    @Email
    private String email;
    @NotBlank
    private String password;
}
Login Request
package com.vin.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class LoginRequest {
    @Email
    private String email;
    @NotBlank
    private String password;
}
Auth Response
package com.vin.dto;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class AuthResponse {
    private String accessToken;
    private String refreshToken;
    private String tokenType;
}
Step 12 — Create JWT Utility
File:
com.vin.util.JwtUtil.java
package com.vin.util;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
@Component
@RequiredArgsConstructor
public class JwtUtil {
    @Value("${jwt.secret-key}")
    private String secretKey;
    @Value("${jwt.access-token-expiration}")
    private long accessTokenExpiration;
    @Value("${jwt.refresh-token-expiration}")
    private long refreshTokenExpiration;
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(
                secretKey.getBytes(StandardCharsets.UTF_8)
        );
    }
    public String generateAccessToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + accessTokenExpiration
                        )
                )
                .signWith(getSigningKey())
                .compact();
    }
    public String generateRefreshToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + refreshTokenExpiration
                        )
                )
                .signWith(getSigningKey())
                .compact();
    }
}
Step 13 — Password Encoder Config
File:
com.vin.config.SecurityConfig.java
package com.vin.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
Step 14 — Create Auth Service
File:
com.vin.service.AuthService.java
package com.vin.service;
import com.vin.dto.AuthResponse;
import com.vin.dto.LoginRequest;
import com.vin.dto.RegisterRequest;
import com.vin.entity.Role;
import com.vin.entity.User;
import com.vin.repository.UserRepository;
import com.vin.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    public String register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(
                        passwordEncoder.encode(request.getPassword())
                )
                .role(Role.USER)
                .build();
        userRepository.save(user);
        return "User Registered Successfully";
    }
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Invalid Credentials"));
        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {
            throw new RuntimeException("Invalid Credentials");
        }
        return AuthResponse.builder()
                .accessToken(
                        jwtUtil.generateAccessToken(user.getEmail())
                )
                .refreshToken(
                        jwtUtil.generateRefreshToken(user.getEmail())
                )
                .tokenType("Bearer")
                .build();
    }
}
Step 15 — Create Controller
File:
com.vin.controller.AuthController.java
package com.vin.controller;
import com.vin.dto.AuthResponse;
import com.vin.dto.LoginRequest;
import com.vin.dto.RegisterRequest;
import com.vin.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/register")
    public String register(
            @Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }
    @PostMapping("/login")
    public AuthResponse login(
            @Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
Step 16 — Run Services
Start in order:
1. Discovery Server
mvn spring-boot:run
2. API Gateway
mvn spring-boot:run
3. JWT Auth Service
mvn spring-boot:run
Step 17 — Verify Eureka Registration
Open:
http://localhost:8761
Expected:
MS-4-JWT-AUTH-SERVICE
appears.
Step 18 — Test Registration
Endpoint
POST http://localhost:8081/auth/register
Request
{
  "name":"Vin",
  "email":"vin@test.com",
  "password":"password123"
}
Expected:
User Registered Successfully
Step 19 — Test Login
Endpoint
POST http://localhost:8081/auth/login
Request
{
  "email":"vin@test.com",
  "password":"password123"
}
Expected:
{
  "accessToken":"...",
  "refreshToken":"...",
  "tokenType":"Bearer"
}
Step 20 — Validate Gateway JWT Filter
Call protected gateway endpoint:
GET http://localhost:8080/api/test
Without token:
401 Unauthorized
With token:
Authorization: Bearer <access-token>
Expected:
JWT Token Valid
Validation Checklist
Auth Service
User registration working
Login working
BCrypt encryption working
JWT generation working
Oracle persistence working
Gateway
JWT validation working
Unauthorized access blocked
Public endpoints accessible
Infrastructure
Eureka registration working
Structured logging working
TraceId/spanId working
Deliverable
You now have:
Centralized Authentication Service
JWT-based security
Oracle-backed user management
Gateway token validation
Secure microservice foundation
Important Improvements For Next Step
Current implementation is intentionally minimal and production foundation only.
Next improvements:
Refresh token API
JWT expiration handling
Role-based authorization
Spring Security filter chain
Redis token blacklist
API Gateway RBAC
UserDetailsService
Secure password policy
Account locking
Rate limiting
Audit logging
Next Phase
Phase 5 — create-user-service (Kafka Producer)
Will include:
Kafka Producer
Oracle Persistence
Event Publishing
User Created Event
Kafka Topic Configuration
Async Architecture
File context used:
---
Postman + cURL Testing Guide
Use these exact requests for testing:
Individual JWT Auth Service
API Gateway Integration
JWT Validation
Eureka Registration
1. Verify Eureka Dashboard
Browser
http://localhost:8761
Expected Services:
MS-3-API-GATEWAY
MS-4-JWT-AUTH-SERVICE
Status:
UP
2. Health Check — JWT Service
Direct Service
cURL
curl --location 'http://localhost:8081/actuator/health'
3. Register User — Direct JWT Service
cURL
curl --location 'http://localhost:8081/auth/register' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name":"Vin",
    "email":"vin@test.com",
    "password":"password123"
}'
Expected:
User Registered Successfully
4. Login User — Direct JWT Service
cURL
curl --location 'http://localhost:8081/auth/login' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email":"vin@test.com",
    "password":"password123"
}'
Expected Response:
{
  "accessToken":"xxxxx",
  "refreshToken":"xxxxx",
  "tokenType":"Bearer"
}
5. Verify Password Encryption
Run Oracle Query:
SELECT ID, NAME, EMAIL, PASSWORD, ROLE
FROM APP_USERS;
Expected:
encrypted password
USER role
6. Test Duplicate Registration
cURL
curl --location 'http://localhost:8081/auth/register' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name":"Vin",
    "email":"vin@test.com",
    "password":"password123"
}'
Expected:
Email already exists
7. Test Wrong Password
cURL
curl --location 'http://localhost:8081/auth/login' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email":"vin@test.com",
    "password":"wrongpassword"
}'
Expected:
Invalid Credentials
8. Test Through API Gateway
IMPORTANT:
Gateway must contain route:
- id: jwt-auth-service
  uri: lb://MS-4-JWT-AUTH-SERVICE
  predicates:
    - Path=/auth/**
Register Through Gateway
cURL
curl --location 'http://localhost:8080/auth/register' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name":"Test User",
    "email":"gateway@test.com",
    "password":"password123"
}'
Expected:
{
  "accessToken":"xxxxx",
  "refreshToken":"xxxxx",
  "tokenType":"Bearer"
}
9. Test Protected Route Without JWT
cURL
curl --location 'http://localhost:8080/api/test'
Expected:
401 Unauthorized
Expected Gateway Log:
Missing Authorization Header
10. Test Protected Route With JWT
cURL
curl --location 'http://localhost:8080/api/test' \
--header 'Authorization: Bearer YOUR_ACCESS_TOKEN'
Expected:
request passes gateway JWT filter
token validated
Expected logs:
JWT Token Valid
11. Verify Eureka Registration via API
Discovery Server API
cURL
curl --location 'http://localhost:8761/eureka/apps'
Expected:
XML response containing:
MS-3-API-GATEWAY
MS-4-JWT-AUTH-SERVICE
Final Expected Architecture
Client
   ↓
API Gateway
   ↓
JWT Auth Service
   ↓
Oracle Database
==========================================================================================================
# Phase 5: Create User Service (Kafka Producer)
## Project
`create-user-service`
## Build
* REST API to create user
* Oracle persistence
* Publish `USER_CREATED` event to Kafka
## Test
* Call `POST /users`
* Verify database insert
* Verify message published to Kafka
## Deliverable
Producer microservice.
---
Phase 5 — Create User Service (Kafka Producer)
Project: ms-5-create-user-service
Goal:
Build an independent producer microservice that:
Creates users
Stores users in Oracle DB
Publishes USER_CREATED events to Kafka
Registers with Eureka
Supports tracing/logging
Works through API Gateway
Architecture after Phase 5:
Client
   ↓
API Gateway
   ↓
Create User Service
   ├── Oracle DB
   └── Kafka Producer
              ↓
        USER_CREATED Topic
Based on your roadmap and completed Phases 1–4 with testing.
Step 1 — Create New Project
Project Name:
ms-5-create-user-service
Dependencies:
Spring Web
Spring Data JPA
Validation
Eureka Discovery Client
Spring for Apache Kafka
Oracle Driver
Lombok
DevTools
Actuator
Micrometer Tracing
Step 2 — Final Project Structure
ms-5-create-user-service/
│
├── src/main/java/com/vin
│   ├── config
│   ├── controller
│   ├── dto
│   ├── entity
│   ├── event
│   ├── repository
│   ├── service
│   ├── producer
│   ├── exception
│   ├── util
│   └── CreateUserServiceApplication.java
│
├── src/main/resources
│   ├── application.yml
│   └── logback-spring.xml
│
├── Dockerfile
└── pom.xml
Step 3 — pom.xml
Step 5 — application.yml
spring:
  application:
    name: ${APP_NAME:ms-5-create-user-service}
  datasource:
    url: ${DB_URL:jdbc:oracle:thin:@localhost:1521/FREEPDB1}
    username: ${DB_USERNAME:app_user}
    password: ${DB_PASSWORD:app_pass}
    driver-class-name: oracle.jdbc.OracleDriver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.OracleDialect
        format_sql: true
  kafka:
    bootstrap-servers: ${KAFKA_BOOTSTRAP_SERVERS:localhost:9092}
    producer:
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      value-serializer: org.springframework.kafka.support.serializer.JsonSerializer
      properties:
        spring:
          json:
            add:
              type:
                headers: false
  cloud:
    compatibility-verifier:
      enabled: false
server:
  port: ${APP_PORT:8082}
# EUREKA CONFIGURATION
eureka:
  instance:
    hostname: localhost
    prefer-ip-address: true
    ip-address: 127.0.0.1
    instance-id: ${spring.application.name}:${server.port}
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      defaultZone: ${DISCOVERY_SERVER_URL:http://localhost:8761/eureka}
# ACTUATOR CONFIGURATION
management:
  endpoints:
    web:
      exposure:
        include:
          - health
          - info
  endpoint:
    health:
      show-details: always
  info:
    env:
      enabled: true
  tracing:
    sampling:
      probability: 1.0
# APPLICATION INFO
info:
  app:
    name: ${APP_NAME:ms-5-create-user-service}
    version: ${APP_VERSION:1.0.0}
    description: ${APP_DESCRIPTION:Create User Kafka Producer Service}
# LOGGING CONFIGURATION
logging:
  level:
    root: INFO
    com.vin: DEBUG
    org.springframework.kafka: INFO
    org.apache.kafka: INFO
    org.hibernate.SQL: DEBUG
    org.hibernate.type.descriptor.sql: TRACE
  pattern:
    console: "%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n"
# CUSTOM KAFKA TOPICS
kafka:
  topic:
    user-created: ${KAFKA_TOPIC_USER_CREATED:user-created}
Step 6 — Main Application
package com.vin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class CreateUserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CreateUserServiceApplication.class, args);
    }
}
Step 7 — User Entity
com.vin.entity.User
package com.vin.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "MS5_USERS")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
}
Step 8 — DTO
com.vin.dto.CreateUserRequest
package com.vin.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class CreateUserRequest {
    @NotBlank
    private String name;
    @Email
    private String email;
}
Step 9 — Repository
com.vin.repository.UserRepository
package com.vin.repository;
import com.vin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository
        extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}
Step 10 — Kafka Event
com.vin.event.UserCreatedEvent
package com.vin.event;
import lombok.*;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreatedEvent {
    private Long id;
    private String name;
    private String email;
}
Step 11 — Kafka Producer
com.vin.producer.UserEventProducer
package com.vin.producer;
import com.vin.event.UserCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
@Slf4j
@Component
@RequiredArgsConstructor
public class UserEventProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    @Value("${kafka.topic.user-created}")
    private String topic;
    public void publishUserCreatedEvent(
            UserCreatedEvent event) {
        log.info(
                "Publishing USER_CREATED event for email={}",
                event.getEmail()
        );
        kafkaTemplate.send(
                topic,
                event.getEmail(),
                event
        );
    }
}
Step 12 — Service Layer
com.vin.service.UserService
package com.vin.service;
import com.vin.dto.CreateUserRequest;
import com.vin.entity.User;
import com.vin.event.UserCreatedEvent;
import com.vin.producer.UserEventProducer;
import com.vin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserEventProducer userEventProducer;
    public String createUser(CreateUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .build();
        User savedUser = userRepository.save(user);
        UserCreatedEvent event =
                UserCreatedEvent.builder()
                        .id(savedUser.getId())
                        .name(savedUser.getName())
                        .email(savedUser.getEmail())
                        .build();
        userEventProducer.publishUserCreatedEvent(event);
        return "User Created Successfully";
    }
}
Step 13 — Controller
com.vin.controller.UserController
package com.vin.controller;
import com.vin.dto.CreateUserRequest;
import com.vin.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping
    public String createUser(
            @Valid
            @RequestBody
            CreateUserRequest request) {
        return userService.createUser(request);
    }
}
Step 14 — Kafka Topic Config
com.vin.config.KafkaTopicConfig
package com.vin.config;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class KafkaTopicConfig {
    @Value("${kafka.topic.user-created}")
    private String userCreatedTopic;
    @Bean
    public NewTopic userCreatedTopic() {
        return new NewTopic(
                userCreatedTopic,
                1,
                (short) 1
        );
    }
}
Step 15 — API Gateway Route
Add this route inside:
ms-3-api-gateway/src/main/resources/application.yml
        - id: create-user-service
          uri: lb://MS-5-CREATE-USER-SERVICE
          predicates:
            - Path=/users/**
Step 16 — Start Infrastructure
Start in order:
1 Eureka
mvn spring-boot:run
2 Kafka
docker compose up -d
3 API Gateway
mvn spring-boot:run
4 Create User Service
mvn spring-boot:run
Step 17 — Verify Eureka Registration
Open:
http://localhost:8761
Expected:
MS-5-CREATE-USER-SERVICE
Status:
UP
Step 18 — Test User Creation
Direct Service
curl --location 'http://localhost:8082/users' \
--header 'Content-Type: application/json' \
--data '{
    "name":"Vin",
    "email":"vin@kafka.com"
}'
Expected:
User Created Successfully
Step 19 — Verify Oracle Insert
SELECT * FROM MS5_USERS;
Expected:
ID
NAME
EMAIL
Step 20 — Verify Kafka Event
Consume messages:
docker exec -it kafka kafka-console-consumer \
--bootstrap-server localhost:9092 \
--topic user-created \
--from-beginning
Expected:
{
  "id":1,
  "name":"Vin",
  "email":"vin@kafka.com"
}
Step 21 — Test Through Gateway
curl --location 'http://localhost:8080/users' \
--header 'Authorization: Bearer YOUR_ACCESS_TOKEN' \
--header 'Content-Type: application/json' \
--data '{
    "name":"Gateway User",
    "email":"gateway@kafka.com"
}'
Expected:
Request routed through gateway
User stored
Kafka event published
Validation Checklist
Create User Service
REST API working
Oracle persistence working
Kafka producer working
Event publishing working
Validation working
Kafka
Topic auto-created
Messages published
JSON serialization working
Gateway
Route working
JWT validation working
Infrastructure
Eureka registration working
TraceId/spanId working
Structured logs working
Expected Logs
Producer Logs
{
  "message":"Publishing USER_CREATED event for email=vin@kafka.com",
  "traceId":"...",
  "spanId":"..."
}
Deliverable After Phase 5
You now have:
Event-driven producer microservice
Oracle-backed persistence
Kafka integration
Async event publishing
Gateway-integrated architecture
Eureka-discovered service communication
---
Kafka
Apache Kafka is a:
Distributed Event Streaming Platform
Used for:
async communication
event-driven architecture
high-speed messaging between microservices
In Your Project
When user is created:
create-user-service
does NOT directly call:
email-service
audit-service
Instead:
create-user-service
    ↓
Kafka Topic (user-created)
    ↓
email-service
audit-service
This makes system:
scalable
loosely coupled
async
fault tolerant
Real Example
Producer
ms-5-create-user-service
publishes event:
{
  "id":1,
  "name":"Vin",
  "email":"vin@test.com"
}
to Kafka topic:
user-created
Consumers
Later:
email-service
Consumes event:
sends welcome email
audit-service
Consumes event:
stores audit logs
Kafka Terminology
Term	Meaning
Producer	Sends messages
Consumer	Reads messages
Topic	Queue/channel
Broker	Kafka server
Event	JSON message
ZooKeeper
ZooKeeper is:
Kafka coordination manager
Older Kafka versions use it for:
broker coordination
leader election
metadata management
Relationship
Spring Boot App
      ↓
Kafka Broker
      ↓
ZooKeeper
Why You Need Both
Right now your architecture is:
create-user-service
        ↓
     Kafka
        ↓
email-service
audit-service
Without Kafka:
no async communication
no event-driven system
Why Kafka Failed
Your app tried:
localhost:9092
But Kafka server was not running.
So Spring Boot waited for Kafka topic creation and timed out.
What You Need Installed
You do NOT install manually.
Use Docker containers:
zookeeper container
kafka container
Final Architecture
Client
   ↓
API Gateway
   ↓
Create User Service
   ↓
Kafka Topic (user-created)
   ↓             ↓
Email Service    Audit Service
This is the core foundation of:
scalable backend systems
fintech systems
e-commerce systems
high-throughput microservices
real-time systems
---
Step 1 — Verify Docker Installed
Run:
docker --version
Expected:
Docker version xx.xx.x
Then:
docker compose version
If Docker NOT Installed
Install:
Docker Desktop
For Windows:
Docker Desktop Official Site
During installation:
Enable WSL2
Restart system
After install:
Start:
Docker Desktop
Wait until:
Engine running
Step 2 — Create Kafka Infrastructure Folder
Create:
infrastructure/docker-compose
Inside create:
docker-compose.yml
Step 3 — Add Docker Compose File
docker-compose.yml
version: '3.8'
services:
  zookeeper:
    image: confluentinc/cp-zookeeper:7.5.0
    container_name: zookeeper
    environment:
      ZOOKEEPER_CLIENT_PORT: 2181
      ZOOKEEPER_TICK_TIME: 2000
    ports:
      - "2181:2181"
  kafka:
    image: confluentinc/cp-kafka:7.5.0
    container_name: kafka
    depends_on:
      - zookeeper
    ports:
      - "9092:9092"
    environment:
      KAFKA_BROKER_ID: 1
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://localhost:9092
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
Step 4 — Start Kafka
Open terminal inside:
infrastructure/docker-compose
Run:
docker compose up -d
Step 5 — Verify Containers Running
Run:
docker ps
Expected:
zookeeper
kafka
STATUS should be:
Up
Step 6 — Verify Kafka Reachable
Run:
docker exec -it kafka kafka-topics \
--bootstrap-server localhost:9092 \
--list
If NO error:
Kafka is fixed.
Step 7 — Restart Create User Service
Stop:
ms-5-create-user-service
Restart:
mvn spring-boot:run
Step 8 — Verify Logs
You should NOT see:
Connection to node -1
OR
Timed out waiting to get existing topics
Instead you should see:
Started CreateUserServiceApplication
quickly.
Step 9 — Verify Topic Created
Run:
docker exec -it kafka kafka-topics \
--bootstrap-server localhost:9092 \
--list
Expected:
user-created
Step 10 — Test API Again
curl --location 'http://localhost:8080/users' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJ2aW5AdGVzdC5jb20iLCJpYXQiOjE3Nzk1NjA5OTYsImV4cCI6MTc3OTU2NDU5Nn0.nRmnmVILwpZe-zZnMwxrrKcWZ9b1Gqb5BerPBvckZn0yxplrqq5hPYnasH7kzcFZ' \
--data '{
    "name":"Kafka User",
    "email":"kafka@test.com"
}'
Expected:
User Created Successfully
Step 11 — Verify Kafka Message
Run consumer:
docker exec -it kafka kafka-console-consumer --bootstrap-server localhost:9092 --topic user-created --from-beginning
Expected:
{
  "id":1,
  "name":"Kafka User",
  "email":"kafka@test.com"
}
What You Fixed
You now have:
Kafka broker running
ZooKeeper running
Kafka topic creation working
Producer service connected
Gateway → Service → Kafka flow working
This is your first real:
event-driven microservice setup
async backend architecture
scalable messaging system foundation
==========================================================================================================
# Phase 6: Email Service (Kafka Consumer 1)
## Project
`email-service`
## Build
* Kafka consumer for `USER_CREATED`
* Log or send welcome email
## Test
* Trigger user creation
* Verify consumer receives event
## Deliverable
First subscriber.
---
Step 1 — Create New Project
Project Name:
ms-6-email-service
Dependencies:
Spring Web
Spring for Apache Kafka
Eureka Discovery Client
Validation
Actuator
Lombok
DevTools
Micrometer Tracing
Step 2 — Final Project Structure
ms-6-email-service/
│
├── src/main/java/com/vin
│   ├── config
│   ├── consumer
│   ├── event
│   ├── service
│   ├── exception
│   ├── util
│   └── EmailServiceApplication.java
│
├── src/main/resources
│   ├── application.yml
│   └── logback-spring.xml
│
├── Dockerfile
└── pom.xml
Step 3 — Root pom.xml Module Entry
Add inside root pom.xml:
<module>ms-6-email-service</module>
Step 4 — Complete pom.xml
File:
ms-6-email-service/pom.xml
Step 5 — Copy Shared Files
Copy from:
ms-1-service-template
Files:
.gitignore
src/main/resources/logback-spring.xml
Optional reusable packages:
config
aop
exception
util
Step 6 — application.yml
File:
src/main/resources/application.yml
spring:
  application:
    name: ${APP_NAME:ms-6-email-service}
  kafka:
    bootstrap-servers: ${KAFKA_BOOTSTRAP_SERVERS:localhost:9092}
    consumer:
      group-id: ${KAFKA_CONSUMER_GROUP:email-group}
      auto-offset-reset: earliest
      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
      value-deserializer: org.springframework.kafka.support.serializer.JsonDeserializer
      properties:
        spring:
          json:
            trusted:
              packages: "*"
  cloud:
    compatibility-verifier:
      enabled: false
server:
  port: ${APP_PORT:8083}
# EUREKA CONFIGURATION
eureka:
  instance:
    hostname: localhost
    prefer-ip-address: true
    ip-address: 127.0.0.1
    instance-id: ${spring.application.name}:${server.port}
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      defaultZone: ${DISCOVERY_SERVER_URL:http://localhost:8761/eureka}
# ACTUATOR CONFIGURATION
management:
  endpoints:
    web:
      exposure:
        include:
          - health
          - info
  endpoint:
    health:
      show-details: always
  info:
    env:
      enabled: true
  tracing:
    sampling:
      probability: 1.0
# APPLICATION INFO
info:
  app:
    name: ${APP_NAME:ms-6-email-service}
    version: ${APP_VERSION:1.0.0}
    description: ${APP_DESCRIPTION:Kafka Email Consumer Service}
# LOGGING CONFIGURATION
logging:
  level:
    root: INFO
    com.vin: DEBUG
    org.springframework.kafka: INFO
    org.apache.kafka: INFO
  pattern:
    console: "%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n"
# CUSTOM TOPICS
kafka:
  topic:
    user-created: ${KAFKA_TOPIC_USER_CREATED:user-created}
Step 7 — Main Application Class
File:
src/main/java/com/vin/EmailServiceApplication.java
package com.vin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class EmailServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(
                EmailServiceApplication.class,
                args
        );
    }
}
Step 8 — Create Event Class
File:
src/main/java/com/vin/event/UserCreatedEvent.java
package com.vin.event;
import lombok.*;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreatedEvent {
    private Long id;
    private String name;
    private String email;
}
Step 9 — Create Email Service
File:
src/main/java/com/vin/service/EmailSenderService.java
package com.vin.service;
import com.vin.event.UserCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class EmailSenderService {
    public void sendWelcomeEmail(
            UserCreatedEvent event) {
        log.info(
                """
                =========================================
                SENDING WELCOME EMAIL
                User ID    : {}
                User Name  : {}
                User Email : {}
                Welcome email sent successfully
                =========================================
                """,
                event.getId(),
                event.getName(),
                event.getEmail()
        );
    }
}
Step 10 — Create Kafka Consumer
File:
src/main/java/com/vin/consumer/UserCreatedConsumer.java
package com.vin.consumer;
import com.vin.event.UserCreatedEvent;
import com.vin.service.EmailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
@Slf4j
@Component
@RequiredArgsConstructor
public class UserCreatedConsumer {
    private final EmailSenderService emailSenderService;
    @KafkaListener(
            topics = "${kafka.topic.user-created}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consumeUserCreatedEvent(
            UserCreatedEvent event) {
        log.info(
                "USER_CREATED event received for email={}",
                event.getEmail()
        );
        emailSenderService.sendWelcomeEmail(event);
    }
}
Step 11 — Kafka Consumer Config
File:
src/main/java/com/vin/config/KafkaConsumerConfig.java
package com.vin.config;
import com.vin.event.UserCreatedEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import java.util.HashMap;
import java.util.Map;
@Configuration
public class KafkaConsumerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;
    @Bean
    public ConsumerFactory<String, UserCreatedEvent> consumerFactory() {
        JsonDeserializer<UserCreatedEvent> deserializer =
                new JsonDeserializer<>(UserCreatedEvent.class);
        deserializer.addTrustedPackages("*");
        Map<String, Object> config = new HashMap<>();
        config.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServers
        );
        config.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                "email-group"
        );
        config.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class
        );
        config.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                JsonDeserializer.class
        );
        config.put(
                ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,
                "earliest"
        );
        return new DefaultKafkaConsumerFactory<>(
                config,
                new StringDeserializer(),
                deserializer
        );
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<
            String,
            UserCreatedEvent> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<
                String,
                UserCreatedEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
Step 12 — Run Services
Start services in order:
1. Discovery Server
mvn spring-boot:run
2. Kafka Infrastructure
docker compose up -d
3. API Gateway
mvn spring-boot:run
4. Create User Service
mvn spring-boot:run
5. Email Service
mvn spring-boot:run
Step 13 — Verify Eureka Registration
Open:
http://localhost:8761
Expected:
MS-6-EMAIL-SERVICE
Status:
UP
Step 14 — Test User Creation
Request:
curl --location 'http://localhost:8080/users' \
--header 'Authorization: Bearer YOUR_ACCESS_TOKEN' \
--header 'Content-Type: application/json' \
--data '{
    "name":"Kafka Email User",
    "email":"email@test.com"
}'
Expected:
User Created Successfully
Step 15 — Verify Email Consumer Logs
Expected Logs:
USER_CREATED event received for email=email@test.com
Then:
=========================================
SENDING WELCOME EMAIL
User ID    : 1
User Name  : Kafka Email User
User Email : email@test.com
Welcome email sent successfully
=========================================
Step 16 — Verify Kafka Still Working
Run:
docker exec -it kafka kafka-console-consumer \
--bootstrap-server localhost:9092 \
--topic user-created \
--from-beginning
Expected Event:
{
  "id":1,
  "name":"Kafka Email User",
  "email":"email@test.com"
}
Validation Checklist
Email Service
Kafka consumer working
Event deserialization working
Consumer group working
Welcome email simulation working
Structured logs working
Kafka
Producer still publishing
Consumer receiving messages
Topic working correctly
Async communication validated
Infrastructure
Eureka registration working
TraceId/spanId working
JSON logs working
Expected Architecture After Phase 6
Client
   ↓
API Gateway
   ↓
Create User Service
   ├── Oracle DB
   └── Kafka Producer
              ↓
        Topic: user-created
              ↓
      Email Service
           ↓
   Welcome Email
Deliverable After Phase 6
You now have:
First Kafka consumer microservice
Async event consumption
Producer → Consumer architecture
Event-driven communication
Independent subscriber service
Scalable messaging foundation
Important Next Step
Next phase:
Phase 7 — Audit Service (Kafka Consumer 2)
Will include:
Kafka consumer
Oracle persistence
Audit event storage
Event history tracking
Multiple consumers for same topic
Real event fan-out architecture
==========================================================================================================
# Phase 7: Audit Service (Kafka Consumer 2)
## Project
`audit-service`
## Build
* Kafka consumer for `USER_CREATED`
* Persist audit record
## Test
* Trigger user creation
* Verify audit entry in database
## Deliverable
Second subscriber.
---
Phase 7 — Audit Service (Kafka Consumer 2)
Project: ms-7-audit-service
Goal:
Build a second Kafka consumer microservice that:
Consumes USER_CREATED events
Persists audit records into Oracle DB
Registers with Eureka
Supports tracing/logging
Demonstrates Kafka fan-out architecture
Architecture after Phase 7:
Client
   ↓
API Gateway
   ↓
Create User Service
   ├── Oracle DB
   └── Kafka Producer
              ↓
        Topic: user-created
          ↓             ↓
   Email Service    Audit Service
                          ↓
                    Oracle Audit DB
Reference roadmap file:
Step 1 — Create New Project
Project Name:
ms-7-audit-service
Dependencies:
Spring Web
Spring Data JPA
Spring for Apache Kafka
Eureka Discovery Client
Oracle Driver
Validation
Lombok
DevTools
Actuator
Micrometer Tracing
Step 2 — Final Project Structure
ms-7-audit-service/
│
├── src/main/java/com/vin
│   ├── config
│   ├── consumer
│   ├── dto
│   ├── entity
│   ├── event
│   ├── repository
│   ├── service
│   ├── exception
│   ├── util
│   └── AuditServiceApplication.java
│
├── src/main/resources
│   ├── application.yml
│   └── logback-spring.xml
│
├── Dockerfile
└── pom.xml
Step 3 — Root pom.xml Module Entry
Add inside root pom.xml:
<module>ms-7-audit-service</module>
Step 4 — Complete pom.xml
File:
ms-7-audit-service/pom.xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.5.14</version>
		<relativePath />
	</parent>
	<groupId>com.vin</groupId>
	<artifactId>ms-7-audit-service</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<properties>
		<java.version>21</java.version>
		<spring-cloud.version>2025.0.0</spring-cloud.version>
	</properties>
	<dependencies>
		<!-- Spring Web -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<!-- Spring Data JPA -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<!-- Kafka -->
		<dependency>
			<groupId>org.springframework.kafka</groupId>
			<artifactId>spring-kafka</artifactId>
		</dependency>
		<!-- Eureka Client -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
		</dependency>
		<!-- Oracle Driver -->
		<dependency>
			<groupId>com.oracle.database.jdbc</groupId>
			<artifactId>ojdbc11</artifactId>
			<scope>runtime</scope>
		</dependency>
		<!-- Validation -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>
		<!-- Actuator -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
		<!-- Tracing -->
		<dependency>
			<groupId>io.micrometer</groupId>
			<artifactId>micrometer-tracing-bridge-brave</artifactId>
		</dependency>
		<!-- Lombok -->
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<!-- DevTools -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
		<!-- JSON Logging -->
		<dependency>
			<groupId>net.logstash.logback</groupId>
			<artifactId>logstash-logback-encoder</artifactId>
			<version>8.1</version>
		</dependency>
		<!-- Testing -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>
	<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-dependencies</artifactId>
				<version>${spring-cloud.version}</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>
	<build>
		<plugins>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<version>3.14.0</version>
				<configuration>
					<source>21</source>
					<target>21</target>
				</configuration>
			</plugin>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>
</project>
Step 5 — Copy Shared Files
Copy from:
ms-1-service-template
Files:
.gitignore
src/main/resources/logback-spring.xml
Optional reusable packages:
config
aop
exception
util
Step 6 — application.yml
File:
src/main/resources/application.yml
spring:
  application:
    name: ${APP_NAME:ms-7-audit-service}
  datasource:
    url: ${DB_URL:jdbc:oracle:thin:@localhost:1521/FREEPDB1}
    username: ${DB_USERNAME:app_user}
    password: ${DB_PASSWORD:app_pass}
    driver-class-name: oracle.jdbc.OracleDriver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.OracleDialect
        format_sql: true
  kafka:
    bootstrap-servers: ${KAFKA_BOOTSTRAP_SERVERS:localhost:9092}
    consumer:
      group-id: ${KAFKA_CONSUMER_GROUP:audit-group}
      auto-offset-reset: earliest
      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
      value-deserializer: org.springframework.kafka.support.serializer.JsonDeserializer
      properties:
        spring:
          json:
            trusted:
              packages: "*"
  cloud:
    compatibility-verifier:
      enabled: false
server:
  port: ${APP_PORT:8084}
# EUREKA CONFIGURATION
eureka:
  instance:
    hostname: localhost
    prefer-ip-address: true
    ip-address: 127.0.0.1
    instance-id: ${spring.application.name}:${server.port}
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      defaultZone: ${DISCOVERY_SERVER_URL:http://localhost:8761/eureka}
# ACTUATOR CONFIGURATION
management:
  endpoints:
    web:
      exposure:
        include:
          - health
          - info
  endpoint:
    health:
      show-details: always
  info:
    env:
      enabled: true
  tracing:
    sampling:
      probability: 1.0
# APPLICATION INFO
info:
  app:
    name: ${APP_NAME:ms-7-audit-service}
    version: ${APP_VERSION:1.0.0}
    description: ${APP_DESCRIPTION:Kafka Audit Consumer Service}
# LOGGING CONFIGURATION
logging:
  level:
    root: INFO
    com.vin: DEBUG
    org.springframework.kafka: INFO
    org.apache.kafka: INFO
    org.hibernate.SQL: DEBUG
    org.hibernate.type.descriptor.sql: TRACE
  pattern:
    console: "%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n"
# CUSTOM TOPICS
kafka:
  topic:
    user-created: ${KAFKA_TOPIC_USER_CREATED:user-created}
Step 7 — Main Application Class
File:
src/main/java/com/vin/AuditServiceApplication.java
package com.vin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class AuditServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(
                AuditServiceApplication.class,
                args
        );
    }
}
Step 8 — Create Event Class
File:
src/main/java/com/vin/event/UserCreatedEvent.java
package com.vin.event;
import lombok.*;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreatedEvent {
    private Long id;
    private String name;
    private String email;
}
Step 9 — Create Audit Entity
File:
src/main/java/com/vin/entity/AuditLog.java
package com.vin.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "MS7_AUDIT_LOGS")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String eventType;
    @Column(nullable = false)
    private String entityId;
    @Column(nullable = false)
    private String entityName;
    @Column(nullable = false)
    private String entityEmail;
    @Column(nullable = false)
    private LocalDateTime createdAt;
}
Step 10 — Create Repository
File:
src/main/java/com/vin/repository/AuditLogRepository.java
package com.vin.repository;
import com.vin.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
public interface AuditLogRepository
        extends JpaRepository<AuditLog, Long> {
}
Step 11 — Create Audit Service
File:
src/main/java/com/vin/service/AuditService.java
package com.vin.service;
import com.vin.entity.AuditLog;
import com.vin.event.UserCreatedEvent;
import com.vin.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
@Slf4j
@Service
@RequiredArgsConstructor
public class AuditService {
    private final AuditLogRepository auditLogRepository;
    public void saveAuditLog(
            UserCreatedEvent event) {
        AuditLog auditLog =
                AuditLog.builder()
                        .eventType("USER_CREATED")
                        .entityId(String.valueOf(event.getId()))
                        .entityName(event.getName())
                        .entityEmail(event.getEmail())
                        .createdAt(LocalDateTime.now())
                        .build();
        auditLogRepository.save(auditLog);
        log.info(
                "Audit log saved for email={}",
                event.getEmail()
        );
    }
}
Step 12 — Create Kafka Consumer
File:
src/main/java/com/vin/consumer/UserCreatedConsumer.java
package com.vin.consumer;
import com.vin.event.UserCreatedEvent;
import com.vin.service.AuditService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
@Slf4j
@Component
@RequiredArgsConstructor
public class UserCreatedConsumer {
    private final AuditService auditService;
    @KafkaListener(
            topics = "${kafka.topic.user-created}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consumeUserCreatedEvent(
            UserCreatedEvent event) {
        log.info(
                "USER_CREATED event received in audit-service for email={}",
                event.getEmail()
        );
        auditService.saveAuditLog(event);
    }
}
Step 13 — Kafka Consumer Config
File:
src/main/java/com/vin/config/KafkaConsumerConfig.java
package com.vin.config;
import com.vin.event.UserCreatedEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import java.util.HashMap;
import java.util.Map;
@Configuration
public class KafkaConsumerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;
    @Bean
    public ConsumerFactory<String, UserCreatedEvent> consumerFactory() {
        JsonDeserializer<UserCreatedEvent> deserializer =
                new JsonDeserializer<>(UserCreatedEvent.class);
        deserializer.addTrustedPackages("*");
        Map<String, Object> config = new HashMap<>();
        config.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServers
        );
        config.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                "audit-group"
        );
        config.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class
        );
        config.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                JsonDeserializer.class
        );
        config.put(
                ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,
                "earliest"
        );
        return new DefaultKafkaConsumerFactory<>(
                config,
                new StringDeserializer(),
                deserializer
        );
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<
            String,
            UserCreatedEvent> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<
                String,
                UserCreatedEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
Step 14 — Run Services
Start services in order:
1. Discovery Server
mvn spring-boot:run
2. Kafka Infrastructure
docker compose up -d
3. API Gateway
mvn spring-boot:run
4. JWT Auth Service
mvn spring-boot:run
5. Create User Service
mvn spring-boot:run
6. Email Service
mvn spring-boot:run
7. Audit Service
mvn spring-boot:run
Step 15 — Verify Eureka Registration
Open:
http://localhost:8761
Expected:
MS-7-AUDIT-SERVICE
Status:
UP
Step 16 — Test User Creation
curl --location 'http://localhost:8080/users' \
--header 'Authorization: Bearer YOUR_ACCESS_TOKEN' \
--header 'Content-Type: application/json' \
--data '{
    "name":"Audit User",
    "email":"audit@test.com"
}'
Expected:
User Created Successfully
Step 17 — Verify Email Service Still Receives Event
Expected logs in ms-6-email-service:
USER_CREATED event received for email=audit@test.com
Step 18 — Verify Audit Service Logs
Expected logs:
USER_CREATED event received in audit-service for email=audit@test.com
Then:
Audit log saved for email=audit@test.com
Step 19 — Verify Oracle Audit Table
Run query:
SELECT *
FROM MS7_AUDIT_LOGS;
Expected:
ID	EVENT_TYPE	ENTITY_ID	ENTITY_NAME	ENTITY_EMAIL	CREATED_AT
Step 20 — Verify Kafka Fan-Out Architecture
Now the same Kafka event is consumed by:
ms-6-email-service
ms-7-audit-service
This validates:
Multiple consumers
Independent consumer groups
Event fan-out
Async architecture
Loose coupling
Validation Checklist
Audit Service
Kafka consumer working
Oracle persistence working
Audit records saved
Structured logs working
Event deserialization working
Kafka
Multiple consumers receiving same event
Consumer groups isolated correctly
Topic working correctly
Infrastructure
Eureka registration working
TraceId/spanId working
JSON logs working
Expected Architecture After Phase 7
Client
   ↓
API Gateway
   ↓
Create User Service
   ├── Oracle DB
   └── Kafka Producer
              ↓
        Topic: user-created
          ↓             ↓
   Email Service    Audit Service
          ↓               ↓
   Welcome Email     Oracle Audit DB
Deliverable After Phase 7
You now have:
Multi-consumer Kafka architecture
Oracle-backed audit tracking
Event fan-out implementation
Async subscriber pattern
Production-grade event pipeline foundation
Next Phase
Phase 8 — Redis Integration
Will include:
Redis container
Spring Redis integration
Cache abstraction
API response caching
JWT/token blacklist support
Rate limiting foundation
TTL-based cache eviction
==========================================================================================================
# Phase 8: Redis Integration
## Infrastructure
[Redis](https://redis.io?utm_source=chatgpt.com)
## Build
* Cache frequently accessed data
* Optional token blacklist or rate limiting
## Test
* Cache retrieval
* TTL expiration
## Deliverable
Caching layer.
---
Phase 8 — Redis Cache Integration
Project Focus:
Redis Infrastructure
Spring Data Redis
Cache Manager
API Response Caching
JWT Token Blacklist Foundation
Redis Health Validation
Gateway Performance Foundation
Using standards from service-template and previous phases.
Final Architecture After Phase 8
Client
   ↓
API Gateway
   ├── JWT Validation
   ├── Redis Cache
   ├── Future Rate Limiting
   ↓
Microservices
PHASE 8 IMPLEMENTATION
Step 1 — Create Redis Infrastructure
Inside:
infrastructure/docker-compose
Update existing:
docker-compose.yml
Complete docker-compose.yml
version: '3.8'
services:
  zookeeper:
    image: confluentinc/cp-zookeeper:7.5.0
    container_name: zookeeper
    environment:
      ZOOKEEPER_CLIENT_PORT: 2181
      ZOOKEEPER_TICK_TIME: 2000
    ports:
      - "2181:2181"
  kafka:
    image: confluentinc/cp-kafka:7.5.0
    container_name: kafka
    depends_on:
      - zookeeper
    ports:
      - "9092:9092"
    environment:
      KAFKA_BROKER_ID: 1
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://localhost:9092
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
  redis:
    image: redis:7.2
    container_name: redis
    ports:
      - "6379:6379"
    restart: always
Step 2 — Start Redis
Run:
docker compose up -d
Step 3 — Verify Redis Running
Run:
docker ps
Expected:
redis
Status:
Up
Step 4 — Verify Redis Connection
Run:
docker exec -it redis redis-cli
Inside Redis shell:
ping
Expected:
PONG
Exit:
exit
Step 5 — Update API Gateway
We will integrate Redis inside:
ms-3-api-gateway
Because gateway is the correct centralized caching/security layer.
Step 6 — Update pom.xml
File:
ms-3-api-gateway/pom.xml
Add dependency:
<!-- Redis -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis-reactive</artifactId>
</dependency>
Step 7 — Update application.yml
File:
ms-3-api-gateway/src/main/resources/application.yml
Add:
spring:
  data:
    redis:
      host: ${REDIS_HOST:localhost}
      port: ${REDIS_PORT:6379}
      timeout: 60000
Final Redis Section Example
spring:
  application:
    name: ${APP_NAME:ms-3-api-gateway}
  data:
    redis:
      host: ${REDIS_HOST:localhost}
      port: ${REDIS_PORT:6379}
      timeout: 60000
  cloud:
    compatibility-verifier:
      enabled: false
Step 9 — Create Redis Service
Package:
com.vin.service
File:
RedisService.java
Complete RedisService.java
package com.vin.service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.time.Duration;
@Slf4j
@Service
@RequiredArgsConstructor
public class RedisService {
    private final ReactiveRedisTemplate<String, String> redisTemplate;
    public Mono<Boolean> save(
            String key,
            String value,
            long timeoutSeconds) {
        log.info(
                "Saving data into Redis key={}",
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
    public Mono<String> get(String key) {
        log.info(
                "Fetching data from Redis key={}",
                key
        );
        return redisTemplate
                .opsForValue()
                .get(key);
    }
    public Mono<Boolean> delete(String key) {
        log.info(
                "Deleting Redis key={}",
                key
        );
        return redisTemplate
                .delete(key)
                .map(count -> count > 0);
    }
}
Step 10 — Create Redis Test Controller
Package:
com.vin.controller
File:
RedisController.java
Complete RedisController.java
package com.vin.controller;
import com.vin.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
@RestController
@RequestMapping("/redis")
@RequiredArgsConstructor
public class RedisController {
    private final RedisService redisService;
    @PostMapping("/save")
    public Mono<String> save(
            @RequestParam String key,
            @RequestParam String value) {
        return redisService
                .save(key, value, 300)
                .map(saved -> "Data Saved Into Redis");
    }
    @GetMapping("/get")
    public Mono<String> get(
            @RequestParam String key) {
        return redisService.get(key);
    }
    @DeleteMapping("/delete")
    public Mono<String> delete(
            @RequestParam String key) {
        return redisService
                .delete(key)
                .map(deleted -> "Redis Key Deleted");
    }
}
Step 11 — Update JWT Filter
We now prepare blacklist support.
File:
com.vin.filter.JwtAuthenticationFilter
Inject:
private final RedisService redisService;
Updated Constructor
Because you already use:
@RequiredArgsConstructor
Spring injects automatically.
Add Token Blacklist Validation
Inside filter method after token extraction:
return redisService.get("blacklist:" + token)
        .flatMap(blacklistedToken -> {
            log.error("JWT Token Blacklisted");
            exchange.getResponse()
                    .setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        })
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
Step 12 — Complete Updated Filter
Replace entire method:
@Override
public Mono<Void> filter(
        ServerWebExchange exchange,
        org.springframework.cloud.gateway.filter.GatewayFilterChain chain) {
    String path = exchange.getRequest().getURI().getPath();
    /*
     * Public Routes
     */
    if (path.contains("/auth/")
            || path.contains("/actuator")
            || path.contains("/eureka")
            || path.contains("/redis")) {
        return chain.filter(exchange);
    }
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
    String token = authHeader.substring(7);
    return redisService
            .get("blacklist:" + token)
            .flatMap(blacklistedToken -> {
                log.error("JWT Token Blacklisted");
                exchange.getResponse()
                        .setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange
                        .getResponse()
                        .setComplete();
            })
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
Step 13 — Run Services
Start:
Infrastructure
docker compose up -d
Discovery Server
mvn spring-boot:run
API Gateway
mvn spring-boot:run
Step 14 — Verify Redis Health
Open:
http://localhost:8080/actuator/health
Expected:
"redis": {
  "status": "UP"
}
Step 15 — Test Redis Save
curl --location --request POST 'http://localhost:8080/redis/save?key=user1&value=vin'
Expected:
Data Saved Into Redis
Step 16 — Test Redis Get
curl --location 'http://localhost:8080/redis/get?key=user1'
Expected:
vin
Step 17 — Test Redis Delete
curl --location --request DELETE 'http://localhost:8080/redis/delete?key=user1'
Expected:
Redis Key Deleted
Step 18 — Verify Redis Data Directly
Run:
docker exec -it redis redis-cli
Commands:
keys *
Expected:
stored redis keys
Validation Checklist
Redis Infrastructure
Redis container running
Redis reachable
Redis CLI working
API Gateway
Redis connected
Reactive Redis working
Cache save/get/delete working
JWT blacklist foundation working
Security Foundation
Token blacklist support ready
Future logout support ready
Distributed cache layer ready
Standards
Structured logging
TraceId/spanId
Eureka integration
Template package structure reused
Deliverable After Phase 8
You now have:
Distributed Redis cache
Gateway Redis integration
JWT blacklist foundation
Reactive caching infrastructure
Production-ready caching layer
Foundation for rate limiting/session management
Next Phase
Phase 9 — Redis Rate Limiting + API Protection
Will include:
IP rate limiting
User throttling
Redis counters
Sliding window algorithm
Gateway abuse protection
DOS protection
Production API throttling
---
Phase 8A — Redis Rate Limiting + API Protection
Goal:
Implement production-grade API protection in:
ms-3-api-gateway
Features:
IP Rate Limiting
User Throttling
Redis Counters
Sliding Window Protection
DOS Protection
API Abuse Prevention
Distributed Throttling
Architecture:
Client
   ↓
API Gateway
   ├── JWT Validation
   ├── Redis Blacklist
   ├── Redis Rate Limiting
   ↓
Microservices
STEP 1 — No New Dependency Needed
Already added:
spring-boot-starter-data-redis-reactive
No additional dependency required.
STEP 2 — Final Package Structure
Inside:
ms-3-api-gateway
Add:
com.vin
├── filter
│   ├── JwtAuthenticationFilter.java
│   ├── LoggingFilter.java
│   └── RateLimitingFilter.java
│
├── service
│   └── RedisService.java
STEP 3 — Update RedisService
Replace entire class.
File:
com.vin.service.RedisService
Complete RedisService.java
package com.vin.service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.time.Duration;
@Slf4j
@Service
@RequiredArgsConstructor
public class RedisService {
    private final ReactiveStringRedisTemplate redisTemplate;
    /*
     * =========================================================
     * SAVE
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
     * GET
     * =========================================================
     */
    public Mono<String> get(String key) {
        return redisTemplate
                .opsForValue()
                .get(key);
    }
    /*
     * =========================================================
     * DELETE
     * =========================================================
     */
    public Mono<Boolean> delete(String key) {
        return redisTemplate
                .delete(key)
                .map(count -> count > 0);
    }
    /*
     * =========================================================
     * INCREMENT RATE LIMIT COUNTER
     * =========================================================
     */
    public Mono<Long> incrementRequestCount(
            String key,
            long durationSeconds) {
        return redisTemplate
                .opsForValue()
                .increment(key)
                .flatMap(count ->
                        redisTemplate
                                .expire(
                                        key,
                                        Duration.ofSeconds(durationSeconds)
                                )
                                .thenReturn(count)
                );
    }
}
STEP 4 — Create Rate Limiting Filter
Package:
com.vin.filter
File:
RateLimitingFilter.java
Complete RateLimitingFilter.java
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
     * CONFIGURATION
     * =========================================================
     */
    private static final long MAX_REQUESTS = 10;
    private static final long WINDOW_SECONDS = 60;
    @Override
    public Mono<Void> filter(
            ServerWebExchange exchange,
            org.springframework.cloud.gateway.filter.GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
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
         * GET CLIENT IP
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
         * REDIS RATE LIMIT KEY
         * =========================================================
         */
        String redisKey = "rate_limit:" + clientIp;
        /*
         * =========================================================
         * INCREMENT COUNTER
         * =========================================================
         */
        return redisService
                .incrementRequestCount(
                        redisKey,
                        WINDOW_SECONDS
                )
                .flatMap(requestCount -> {
                    log.info(
                            "IP={} Request Count={}",
                            clientIp,
                            requestCount
                    );
                    /*
                     * =========================================================
                     * RATE LIMIT EXCEEDED
                     * =========================================================
                     */
                    if (requestCount > MAX_REQUESTS) {
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
                    return chain.filter(exchange);
                });
    }
    @Override
    public int getOrder() {
        return -3;
    }
}
STEP 5 — Filter Execution Order
Final order:
Filter	Order
RateLimitingFilter	-3
JwtAuthenticationFilter	-2
LoggingFilter	-1
Correct production order.
STEP 6 — Restart Infrastructure
Ensure Redis running:
docker compose up -d
Verify:
docker ps
Expected:
redis
STEP 7 — Restart Gateway
Inside:
ms-3-api-gateway
Run:
mvn spring-boot:run
STEP 8 — Verify Health
Open:
http://localhost:8080/actuator/health
Expected:
"redis": {
  "status": "UP"
}
STEP 9 — Test Rate Limiting
Run repeatedly:
curl --location 'http://localhost:8080/actuator/health'
After 10 requests within 60 seconds:
Expected:
429 TOO MANY REQUESTS
STEP 10 — Expected Logs
{
  "message":"IP=127.0.0.1 Request Count=11"
}
Then:
{
  "message":"Rate limit exceeded for IP=127.0.0.1"
}
STEP 11 — Verify Redis Counters
Run:
docker exec -it redis redis-cli
Inside Redis:
keys *
Expected:
rate_limit:127.0.0.1
Check value:
get rate_limit:127.0.0.1
STEP 12 — Production Improvements Coming Next
Current implementation:
✅ Distributed
✅ Redis-backed
✅ Gateway centralized
✅ DOS protection
✅ API abuse protection
Next improvements:
Sliding window algorithm
Token bucket algorithm
User-level throttling
JWT user throttling
Route-specific throttling
Burst handling
Dynamic limits
Admin bypass
API tiering
Redis cluster support
Deliverable After Phase 8A
You now have:
Production API throttling
Redis distributed counters
DOS protection layer
Gateway abuse prevention
Centralized traffic control
Scalable Redis-based protection system
Architecture After Phase 8A
Client
   ↓
API Gateway
   ├── Rate Limiting
   ├── JWT Validation
   ├── Redis Blacklist
   ├── Logging
   ↓
Microservices
---
Step 1 — Restart Gateway
Inside:
ms-3-api-gateway
Run:
mvn spring-boot:run
Step 2 — Verify Redis Running
Run:
docker ps
Expected:
redis
Status:
Up
Step 3 — Test Rate Limiting
Current configuration:
MAX_REQUESTS = 10
WINDOW_SECONDS = 60
Meaning:
Only 10 requests allowed in rolling 60 seconds
Step 4 — Send Multiple Requests Quickly
Run this command repeatedly within 1 minute:
curl --location 'http://localhost:8080/actuator/health'
Or use loop:
Windows PowerShell
for ($i=1; $i -le 15; $i++) {
  Invoke-WebRequest `
    -Uri "http://localhost:8080/actuator/health"
}
Linux / Git Bash
for i in {1..15}
do
  curl http://localhost:8080/actuator/health
done
Step 5 — Expected Behavior
Request Number	Expected
1 → 10	200 OK
11+	429 TOO MANY REQUESTS
Step 6 — Expected Gateway Logs
Allowed:
{
  "message":"Sliding window request count key=rate_limit:127.0.0.1 count=5"
}
Blocked:
{
  "message":"Rate limit exceeded for IP=127.0.0.1"
}
Step 7 — Verify Rolling Window Works
Wait:
60 seconds
Then retry:
curl --location 'http://localhost:8080/actuator/health'
Expected again:
200 OK
Because old requests automatically expired from rolling window.
Step 8 — Verify Redis Data
Open Redis CLI:
docker exec -it redis redis-cli
View Keys
keys *
Expected:
rate_limit:127.0.0.1
Inspect Sliding Window Entries
ZRANGE rate_limit:127.0.0.1 0 -1 WITHSCORES
Expected:
request-id-1
1716540010000
request-id-2
1716540015000
Where:
Value	Meaning
member	unique request id
score	request timestamp
Step 9 — Verify Rolling Cleanup
Wait more than:
60 seconds
Then:
ZRANGE rate_limit:127.0.0.1 0 -1 WITHSCORES
Expected:
older requests removed
because:
removeRangeByScore()
automatically cleans requests outside rolling window.
What You Have Built
You now have:
✅ Redis sliding window throttling
✅ Distributed rate limiting
✅ Rolling 60-second window
✅ Gateway DOS protection
✅ Production-grade API throttling
✅ Horizontally scalable protection layer
This is now real gateway engineering architecture.
==========================================================================================================
# Phase 9: Dockerization
## Build
Create multi-stage Dockerfiles for every service:
1. Build JAR
2. Build image
3. Run container
Add Docker Compose for:
* Kafka
* Redis
* Oracle/PostgreSQL
* Core services
## Test
* Build all images
* Start containers
* Verify end-to-end flow
## Deliverable
Containerized local environment.
---
Phase 9: Dockerization + Multi-Stage Build + Docker Compose Infrastructure
This phase converts all completed services into production-ready containerized microservices.
Current completed services:
ms-2-discovery-server
ms-3-api-gateway
ms-4-jwt-auth-service
ms-5-create-user-service
ms-6-email-service
ms-7-audit-service
Now we will:
Dockerize every service
Create multi-stage Dockerfiles
Create centralized Docker Compose infrastructure
Run entire platform locally
Validate inter-service communication
Prepare for Kubernetes phase
Reference roadmap uploaded by user:
Final Architecture After Phase 9
Client
   ↓
API Gateway
   ↓
Microservices
   ↓
Kafka + Oracle + Redis
   ↓
Email/Audit/Event Processing
All Services
   ↓
Docker Containers
   ↓
Docker Compose Network
Step 1 — Final Infrastructure Structure
Create:
kafka-backend-platform/
│
├── infrastructure/
│   ├── docker-compose/
│   │   ├── docker-compose.yml
│   │   └── .env
│   │
│   ├── dockerfiles/
│   │
│   └── scripts/
│
├── ms-2-discovery-server/
├── ms-3-api-gateway/
├── ms-4-jwt-auth-service/
├── ms-5-create-user-service/
├── ms-6-email-service/
└── ms-7-audit-service/
Step 2 — Standard Multi-Stage Dockerfile
Use SAME structure for all services.
Create inside every service root:
Dockerfile
Step 3 — Discovery Server Dockerfile
File:
ms-2-discovery-server/Dockerfile
# ===============================
# STAGE 1 — BUILD APPLICATION
# ===============================
FROM maven:3.9.11-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests
# ===============================
# STAGE 2 — CREATE RUNTIME IMAGE
# ===============================
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "app.jar"]
Step 4 — API Gateway Dockerfile
File:
ms-3-api-gateway/Dockerfile
FROM maven:3.9.11-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
Step 5 — JWT Auth Service Dockerfile
File:
ms-4-jwt-auth-service/Dockerfile
FROM maven:3.9.11-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
Step 6 — Create User Service Dockerfile
File:
ms-5-create-user-service/Dockerfile
FROM maven:3.9.11-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]
Step 7 — Email Service Dockerfile
File:
ms-6-email-service/Dockerfile
FROM maven:3.9.11-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "app.jar"]
Step 8 — Audit Service Dockerfile
File:
ms-7-audit-service/Dockerfile
FROM maven:3.9.11-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "app.jar"]
Step 9 — Create Docker Compose
File:
infrastructure/docker-compose/docker-compose.yml
version: '3.9'
services:
  # ==========================================
  # ZOOKEEPER
  # ==========================================
  zookeeper:
    image: confluentinc/cp-zookeeper:7.5.0
    container_name: zookeeper
    environment:
      ZOOKEEPER_CLIENT_PORT: 2181
      ZOOKEEPER_TICK_TIME: 2000
    ports:
      - "2181:2181"
  # ==========================================
  # KAFKA
  # ==========================================
  kafka:
    image: confluentinc/cp-kafka:7.5.0
    container_name: kafka
    depends_on:
      - zookeeper
    ports:
      - "9092:9092"
    environment:
      KAFKA_BROKER_ID: 1
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://kafka:9092
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
  # ==========================================
  # REDIS
  # ==========================================
  redis:
    image: redis:8.2
    container_name: redis
    ports:
      - "6379:6379"
  # ==========================================
  # DISCOVERY SERVER
  # ==========================================
  discovery-server:
    build:
      context: ../../ms-2-discovery-server
    container_name: discovery-server
    ports:
      - "8761:8761"
  # ==========================================
  # API GATEWAY
  # ==========================================
  api-gateway:
    build:
      context: ../../ms-3-api-gateway
    container_name: api-gateway
    depends_on:
      - discovery-server
    ports:
      - "8080:8080"
    environment:
      DISCOVERY_SERVER_URL: http://discovery-server:8761/eureka
  # ==========================================
  # JWT AUTH SERVICE
  # ==========================================
  jwt-auth-service:
    build:
      context: ../../ms-4-jwt-auth-service
    container_name: jwt-auth-service
    depends_on:
      - discovery-server
    ports:
      - "8081:8081"
    environment:
      DISCOVERY_SERVER_URL: http://discovery-server:8761/eureka
      DB_URL: jdbc:oracle:thin:@host.docker.internal:1521/FREEPDB1
      DB_USERNAME: app_user
      DB_PASSWORD: app_pass
  # ==========================================
  # CREATE USER SERVICE
  # ==========================================
  create-user-service:
    build:
      context: ../../ms-5-create-user-service
    container_name: create-user-service
    depends_on:
      - kafka
      - discovery-server
    ports:
      - "8082:8082"
    environment:
      DISCOVERY_SERVER_URL: http://discovery-server:8761/eureka
      KAFKA_BOOTSTRAP_SERVERS: kafka:9092
      DB_URL: jdbc:oracle:thin:@host.docker.internal:1521/FREEPDB1
      DB_USERNAME: app_user
      DB_PASSWORD: app_pass
  # ==========================================
  # EMAIL SERVICE
  # ==========================================
  email-service:
    build:
      context: ../../ms-6-email-service
    container_name: email-service
    depends_on:
      - kafka
      - discovery-server
    ports:
      - "8083:8083"
    environment:
      DISCOVERY_SERVER_URL: http://discovery-server:8761/eureka
      KAFKA_BOOTSTRAP_SERVERS: kafka:9092
  # ==========================================
  # AUDIT SERVICE
  # ==========================================
  audit-service:
    build:
      context: ../../ms-7-audit-service
    container_name: audit-service
    depends_on:
      - kafka
      - discovery-server
    ports:
      - "8084:8084"
    environment:
      DISCOVERY_SERVER_URL: http://discovery-server:8761/eureka
      KAFKA_BOOTSTRAP_SERVERS: kafka:9092
      DB_URL: jdbc:oracle:thin:@host.docker.internal:1521/FREEPDB1
      DB_USERNAME: app_user
      DB_PASSWORD: app_pass
Step 10 — Build All Services
Run individually inside every service:
mvn clean package -DskipTests
Step 11 — Start Complete Platform
Go to:
infrastructure/docker-compose
Run:
docker compose up --build -d
Step 12 — Verify Containers
Run:
docker ps
Expected:
zookeeper
kafka
redis
discovery-server
api-gateway
jwt-auth-service
create-user-service
email-service
audit-service
Step 13 — Verify Eureka
Open:
http://localhost:8761
Expected services:
MS-3-API-GATEWAY
MS-4-JWT-AUTH-SERVICE
MS-5-CREATE-USER-SERVICE
MS-6-EMAIL-SERVICE
MS-7-AUDIT-SERVICE
Status:
UP
Step 14 — Verify API Gateway
Health:
http://localhost:8080/actuator/health
Routes:
http://localhost:8080/actuator/gateway/routes
Step 15 — Test User Registration
curl --location 'http://localhost:8080/auth/register' \
--header 'Content-Type: application/json' \
--data '{
    "name":"Vin",
    "email":"docker@test.com",
    "password":"password123"
}'
Step 16 — Test Login
curl --location 'http://localhost:8080/auth/login' \
--header 'Content-Type: application/json' \
--data '{
    "email":"docker@test.com",
    "password":"password123"
}'
Copy access token.
Step 17 — Test Kafka Flow
curl --location 'http://localhost:8080/users' \
--header 'Authorization: Bearer YOUR_ACCESS_TOKEN' \
--header 'Content-Type: application/json' \
--data '{
    "name":"Docker Kafka User",
    "email":"kafka-docker@test.com"
}'
Expected:
Oracle insert
Kafka event published
Email service consumes
Audit service consumes
Step 18 — Verify Logs
Check logs:
docker logs create-user-service
docker logs email-service
docker logs audit-service
Expected:
USER_CREATED published
welcome email simulated
audit event stored
Step 19 — Verify Kafka Topic
docker exec -it kafka kafka-topics \
--bootstrap-server kafka:9092 \
--list
Expected:
user-created
Step 20 — Stop Platform
docker compose down
Validation Checklist
Infrastructure
Kafka running
Zookeeper running
Redis running
Docker networking working
Discovery
Eureka registration working
All services UP
Gateway
Routing working
JWT validation working
Kafka
Producer publishing
Consumers consuming
Topic auto-created
Database
Oracle connectivity working
User data persisted
Audit data persisted
Logging
Structured logs working
traceId/spanId working
Deliverable After Phase 9
You now have:
Fully containerized microservices platform
Multi-stage Docker builds
Docker Compose orchestration
Kafka event-driven architecture
Oracle-backed services
Gateway + Eureka architecture
Production-style local deployment
Kubernetes-ready system
Next Phase
Phase 10 — Kubernetes Deployment (K8S)
Will include:
Kubernetes manifests
ConfigMaps
Secrets
Deployments
Services
Ingress
Horizontal scaling
Kafka deployment
Redis deployment
Oracle connectivity
Rolling updates
Health probes
Resource limits
Production-grade container orchestration
---
Yes. That is the correct production-style approach.
You should push all microservice images to a Docker Registry, then pull and run them on any other system/server.
Most common options:
Docker Hub
GitHub Container Registry (GHCR)
Amazon Web Services ECR
Google Artifact Registry
Microsoft Azure Container Registry
For your current learning + portfolio + freelancing:
Use:
Docker Hub
or GitHub Container Registry
Docker Hub is easiest initially.
Recommended Architecture
Local Development Laptop
Run:
only required services locally
lightweight Docker infrastructure
Another Powerful System / Cloud VM
Run:
full docker compose
all microservices
Kafka
Redis
monitoring stack
This is exactly how real deployments work.
What You Should Push
Push these service images:
ms-2-discovery-server
ms-3-api-gateway
ms-4-jwt-auth-service
ms-5-create-user-service
ms-6-email-service
ms-7-audit-service
Workflow
Code
  ↓
Build Docker Image
  ↓
Push to Docker Hub
  ↓
Pull on another machine
  ↓
Run using Docker Compose
Step 1 — Create Docker Hub Account
Open:
Docker Hub Signup
Example username:
vindev
Step 2 — Login From Terminal
docker login
Enter:
username
password
Step 3 — Build Image
Example:
Inside:
ms-2-discovery-server
Run:
docker build -t vindev/ms-2-discovery-server:v1 .
Step 4 — Push Image
docker push vindev/ms-2-discovery-server:v1
Step 5 — Repeat For All Services
Examples:
docker build -t vindev/ms-3-api-gateway:v1 .
docker push vindev/ms-3-api-gateway:v1
docker build -t vindev/ms-4-jwt-auth-service:v1 .
docker push vindev/ms-4-jwt-auth-service:v1
Better Versioning Strategy
Instead of:
latest
Use:
v1
v1.0.1
v1.1.0
dev
qa
prod
Industry standard.
Then On Another System
Install:
Docker Desktop
Docker Compose
Run:
docker pull vindev/ms-2-discovery-server:v1
or directly via compose.
BEST PRACTICE — Use Images In docker-compose.yml
Instead of:
build:
  context: ../../ms-2-discovery-server
Use:
image: vindev/ms-2-discovery-server:v1
Example:
discovery-server:
  image: vindev/ms-2-discovery-server:v1
  container_name: discovery-server
  ports:
    - "8761:8761"
Now no source code needed on another machine.
Only:
docker compose up -d
Everything downloads automatically.
Massive Advantage
Your another system only needs:
Docker
docker-compose.yml
NOT:
Java
Maven
IntelliJ
source code
This is the real power of containers.
Recommended Production Flow For You
Development Laptop
Code + test.
GitHub
Push source code.
Docker Hub
Push container images.
Cloud VM / Another PC
Pull and run containers.
Strong Recommendation
Use BOTH:
Purpose	Platform
Source Code	GitHub
Docker Images	Docker Hub
This is standard DevOps workflow.
Next Important Upgrade
After this:
Phase 9.5 — CI/CD Pipeline
Automate:
Git Push
   ↓
GitHub Actions
   ↓
Build JAR
   ↓
Build Docker Image
   ↓
Push To Docker Hub
Then deployment becomes automatic.
This aligns perfectly with your:
DevOps growth
freelancing
scalable backend systems
production architecture goals.
---
Goal
You want this flow:
Your Laptop
   ↓
Code + Build
   ↓
Docker Image
   ↓
Docker Hub
   ↓
Another System / Cloud VM
   ↓
docker compose up
   ↓
Complete Platform Running
This is the correct DevOps + microservices workflow.
Recommended Starting Strategy
Since you have:
8 GB RAM
multiple microservices
Kafka
Oracle
Docker
DO THIS:
Machine	Responsibility
Current Laptop	Development
Another System / Cloud	Full Deployment
PHASE A — Prepare Docker Hub
Step 1 — Create Docker Hub Account
Open:
Docker Hub Signup
Create username.
Example:
vindev
Remember this username.
Step 2 — Verify Docker Installed
Run:
docker --version
Then:
docker compose version
Expected:
version output.
Step 3 — Login To Docker Hub
Run:
docker login
Enter:
Docker Hub username
password
Expected:
Login Succeeded
PHASE B — Prepare Every Microservice
You already have:
Dockerfiles
Spring Boot services
Good.
Now we standardize image naming.
Step 4 — Go To First Service
Example:
ms-2-discovery-server
Step 5 — Build Docker Image
Run:
docker build -t vindev/ms-2-discovery-server:v1 .
Explanation:
Part	Meaning
vindev	Docker Hub username
ms-2-discovery-server	image name
v1	version tag
Step 6 — Verify Image
Run:
docker images
Expected:
vindev/ms-2-discovery-server
Step 7 — Push Image To Docker Hub
Run:
docker push vindev/ms-2-discovery-server:v1
Wait until upload completes.
Step 8 — Repeat For ALL Services
API Gateway
Inside:
ms-3-api-gateway
Run:
docker build -t vindev/ms-3-api-gateway:v1 .
docker push vindev/ms-3-api-gateway:v1
JWT Auth Service
docker build -t vindev/ms-4-jwt-auth-service:v1 .
docker push vindev/ms-4-jwt-auth-service:v1
Create User Service
docker build -t vindev/ms-5-create-user-service:v1 .
docker push vindev/ms-5-create-user-service:v1
Email Service
docker build -t vindev/ms-6-email-service:v1 .
docker push vindev/ms-6-email-service:v1
Audit Service
docker build -t vindev/ms-7-audit-service:v1 .
docker push vindev/ms-7-audit-service:v1
PHASE C — Update docker-compose.yml
Now IMPORTANT change.
Replace:
build:
  context: ../../ms-2-discovery-server
WITH:
image: vindev/ms-2-discovery-server:v1
Final Production Style
Discovery Server
discovery-server:
  image: vindev/ms-2-discovery-server:v1
  container_name: discovery-server
  ports:
    - "8761:8761"
API Gateway
api-gateway:
  image: vindev/ms-3-api-gateway:v1
  container_name: api-gateway
  ports:
    - "8080:8080"
JWT Auth Service
jwt-auth-service:
  image: vindev/ms-4-jwt-auth-service:v1
  container_name: jwt-auth-service
  ports:
    - "8081:8081"
Continue Same For All Services
Now compose will:
download images
run containers
without source code.
PHASE D — Push Source Code To GitHub
You should ALSO push:
source code
docker-compose
Kubernetes manifests later
to GitHub.
Step 9 — Create GitHub Repository
Open:
GitHub
Create repository:
kafka-backend-platform
Step 10 — Push Code
Inside root project:
git init
git add .
git commit -m "Initial microservices platform"
Add remote:
git remote add origin https://github.com/YOUR_USERNAME/kafka-backend-platform.git
Push:
git push -u origin main
PHASE E — Run On Another System
On another machine:
Step 11 — Install Docker Desktop
Install:
Docker Desktop
Step 12 — Copy docker-compose.yml
Only copy:
docker-compose.yml
No source code needed.
Step 13 — Run Platform
Run:
docker compose up -d
Docker automatically:
pulls images
creates containers
starts services
PHASE F — Best Practice Improvements
After basic deployment works:
Step 14 — Add .env File
Example:
DB_USERNAME=app_user
DB_PASSWORD=app_pass
JWT_SECRET_KEY=MySecretKey
Then use:
environment:
  DB_USERNAME: ${DB_USERNAME}
Step 15 — Add CI/CD Later
Next milestone:
Git Push
   ↓
GitHub Actions
   ↓
Auto Docker Build
   ↓
Push To Docker Hub
Fully automated DevOps pipeline.
Your BEST Current Workflow
Laptop
Use for:
coding
testing
building images
Docker Hub
Use for:
storing images
GitHub
Use for:
storing source code
Another System / Cloud VM
Use for:
full deployment
Kafka testing
performance testing
Kubernetes later
Recommended Next Action TODAY
Do ONLY this first:
1. Create Docker Hub account
2. Push ONLY discovery-server image
Test full flow.
Commands:
docker login
docker build -t YOUR_USERNAME/ms-2-discovery-server:v1 .
docker push YOUR_USERNAME/ms-2-discovery-server:v1
Once successful:
repeat for all services.
This is the correct professional DevOps path for your architecture.
--Goal
Run your complete microservices platform on ANOTHER system using Docker Hub images.
WHAT YOU NEED ON ANOTHER SYSTEM
Install only:
Docker Desktop
docker-compose.yml
NO:
Java
Maven
IntelliJ
source code
required.
COMPLETE STEP-BY-STEP FLOW
STEP 1 — Push Images From Your Current Laptop
You already built images locally.
Example:
docker build -t vindev/ms-2-discovery-server:v1 .
Now push:
docker push vindev/ms-2-discovery-server:v1
Repeat for ALL services.
Final Images
You should have:
vindev/ms-2-discovery-server:v1
vindev/ms-3-api-gateway:v1
vindev/ms-4-jwt-auth-service:v1
vindev/ms-5-create-user-service:v1
vindev/ms-6-email-service:v1
vindev/ms-7-audit-service:v1
STEP 2 — Create Production docker-compose.yml
IMPORTANT:
Use image:
NOT:
build:
Example
discovery-server:
  image: vindev/ms-2-discovery-server:v1
Docker will pull automatically from Docker Hub.
STEP 3 — Copy docker-compose.yml To Another System
You can:
GitHub
pendrive
Google Drive
email
Only this file needed.
STEP 4 — Install Docker Desktop On Another System
Install:
Docker Desktop
Verify:
docker --version
docker compose version
STEP 5 — Create Folder On Another System
Example:
microservices-platform
Inside put:
docker-compose.yml
STEP 6 — Open Terminal In That Folder
Example:
cd microservices-platform
STEP 7 — Start Complete Platform
Run:
docker compose up -d
WHAT HAPPENS NOW
Docker automatically:
Pulls images from Docker Hub
   ↓
Creates containers
   ↓
Starts services
   ↓
Creates Docker network
You do NOT manually pull each image.
Compose handles everything.
STEP 8 — Verify Running Containers
Run:
docker ps
Expected:
discovery-server
api-gateway
jwt-auth-service
create-user-service
email-service
audit-service
kafka
redis
zookeeper
STEP 9 — Verify Eureka
Open browser:
http://localhost:8761
Expected:
All services UP
STEP 10 — Verify Gateway
Open:
http://localhost:8080/actuator/health
STEP 11 — Test Login API
curl --location 'http://localhost:8080/auth/login'
OPTIONAL — Pull Images Manually
You usually DON'T need this.
But possible:
docker pull vindev/ms-2-discovery-server:v1
VERY IMPORTANT
If images are PRIVATE:
Login required:
docker login
If PUBLIC:
no login needed.
HOW TO UPDATE APPLICATION LATER
Suppose code changes.
On Your Laptop
Rebuild
docker build -t vindev/ms-2-discovery-server:v2 .
Push
docker push vindev/ms-2-discovery-server:v2
On Another System
Update compose:
image: vindev/ms-2-discovery-server:v2
Then:
docker compose pull
Then:
docker compose up -d
Now new version deployed.
IMPORTANT INDUSTRY CONCEPT
This is exactly how:
cloud deployment
Kubernetes
DevOps pipelines
production microservices
work.
Containers are built ONCE and run ANYWHERE.
Your Actual Real Workflow
Development Machine
Code
Build
Push Image
Deployment Machine
Pull Image
Run Containers
BEST PRACTICE FOR YOU
Keep Images PUBLIC initially
Easier for:
learning
portfolio
testing
freelancing demos
Later:
make repositories private.
MOST IMPORTANT FILE NOW
Your next critical asset is:
docker-compose.yml
This becomes your:
deployment file
server startup file
production orchestration file
future Kubernetes reference
This is your real deployment foundation.
---
==========================================================================================================
9A Docker hub 
# Your Correct DevOps Execution Architecture
This is the proper architecture for your setup:
* Development Laptop (8 GB RAM)
  * coding
  * unit/integration testing
  * individual container validation
  * image creation
  * push to GitHub + Docker Hub
* DevOps Machine / VPS
  * full platform deployment
  * docker compose orchestration
  * performance/integration testing
  * future CI/CD runner
  * future Kubernetes host
This is the correct enterprise workflow. 
---
# COMPLETE IMPLEMENTATION FLOW
┌──────────────────────────────────────────────────────────┐
│                 DEVELOPMENT LAPTOP (8GB)                │
├──────────────────────────────────────────────────────────┤
│                                                          │
│  Code Spring Boot Services                              │
│        ↓                                                 │
│  Run Services Locally                                   │
│        ↓                                                 │
│  Create docker-compose Oracle+Infra+Platform and run Ora│
│        ↓                                                 │
│  Test Individually                                      │
│        ↓                                                 │
│  Test Dependency Groups                                 │
│        ↓                                                 │
│  Create Dockerfile                                      │
│        ↓                                                 │
│  Build Docker Image                                     │
│        ↓                                                 │
│  Run Container Individually                             │
│        ↓                                                 │
│  Validate Logs + Health                                 │
│        ↓                                                 │
│  Push Source → GitHub                                   │
│        ↓                                                 │
│  Push Images → Docker Hub                               │
│                                                          │
└──────────────────────────────────────────────────────────┘
                           │
                           │
                           ▼
┌──────────────────────────────────────────────────────────┐
│                  GITHUB + DOCKER HUB                    │
├──────────────────────────────────────────────────────────┤
│                                                          │
│  GitHub                                                  │
│    ├── source code                                       │
│    ├── docker-compose.yml                                │
│    ├── infra configs                                     │
│    └── future CI/CD                                      │
│                                                          │
│  Docker Hub                                              │
│    ├── discovery image                                   │
│    ├── gateway image                                     │
│    ├── auth image                                        │
│    ├── producer image                                    │
│    └── consumer images                                   │
│                                                          │
└──────────────────────────────────────────────────────────┘
                           │
                           │
                           ▼
┌──────────────────────────────────────────────────────────┐
│                  DEVOPS MACHINE / VPS                   │
├──────────────────────────────────────────────────────────┤
│                                                          │
│  git clone repository                                   │
│        ↓                                                 │
│  docker compose up -d                                   │
│        ↓                                                 │
│  Docker pulls images from Docker Hub                    │
│        ↓                                                 │
│  Containers start                                        │
│        ↓                                                 │
│  Full platform runs                                      │
│                                                          │
│  Oracle                                                  │
│  Kafka                                                   │
│  Redis                                                   │
│  Eureka                                                  │
│  Gateway                                                 │
│  JWT Auth                                                │
│  Producer                                                │
│  Consumers                                               │
│                                                          │
└──────────────────────────────────────────────────────────┘
---
# CORRECT TESTING EXECUTION ORDER
This is the most important part.
You should NOT start everything together initially.
---
# LEVEL 1 — LOCAL SERVICE TESTING
## Goal
Verify pure Spring Boot runtime.
Spring Boot App
     ↓
Local Testing
---
# Order
| Order | Service             |
| ----- | ------------------- |
| 1     | discovery-server    |
| 2     | api-gateway         |
| 3     | jwt-auth-service    |
| 4     | create-user-service |
| 5     | email-service       |
| 6     | audit-service       |
---
# Validate
| Validation            | Check     |
| --------------------- | --------- |
| app startup           | no errors |
| actuator              | UP        |
| Eureka registration   | working   |
| API endpoints         | working   |
| DB connection         | working   |
| Kafka publish/consume | working   |
---
Step 1: Validate Oracle Compose
docker compose -f docker-compose-oracle.yml config
If successful:
docker compose -f docker-compose-oracle.yml up -d
Step 2: Check Oracle Container
docker ps
You should see:
oracle-db
Step 3: Watch Startup
docker logs -f oracle-db
Wait until:
DATABASE IS READY TO USE
Step 4: Check Health
docker inspect --format="{{.State.Health.Status}}" oracle-db
Expected:
healthy
Step 5: Verify Login
docker exec -it oracle-db sqlplus app_user/app_pass@FREEPDB1
Then:
SELECT 'DB OK' FROM dual;
Expected:
DB OK
Step 6: Run JWT Service Locally
Since you're not running JWT inside Docker yet, your Spring Boot app runs on Windows and Oracle runs inside Docker.
Use:
DB_URL: jdbc:oracle:thin:@localhost:1521/FREEPDB1
DB_USERNAME: app_user
DB_PASSWORD: app_pass
Not
jdbc:oracle:thin:@oracle-db:1521/FREEPDB1
because oracle-db only resolves inside Docker networks.
For local Spring Boot:
DB_URL: jdbc:oracle:thin:@localhost:1521/FREEPDB1
Step 7: Start JWT
cd ms-4-jwt-auth-service
mvn spring-boot:run
Validation Goal
If JWT starts successfully and:
http://localhost:8081/actuator/health
returns UP, then:
Oracle Compose ✅
Oracle Connectivity ✅
JWT Database Connectivity ✅
At that point we can move to:
Phase 9A
Dockerfile for Discovery Server
without involving Kafka or the rest of the platform yet.
---
# LEVEL 2 — DEPENDENCY GROUP TESTING
Now run ONLY required dependency groups.
This is critical for 8 GB RAM.
---
# GROUP A — Discovery Layer
discovery-server
        ↓
api-gateway
Test:
* Eureka registration
* gateway routing
---
# GROUP B — Authentication Layer
oracle-db
     ↓
discovery-server
     ↓
api-gateway
     ↓
jwt-auth-service
Test:
* registration
* login
* JWT token
---
# GROUP C — Kafka Producer Layer
oracle-db
     ↓
zookeeper
     ↓
kafka
     ↓
discovery-server
     ↓
api-gateway
     ↓
create-user-service
Test:
* Kafka publish
* Oracle insert
---
# GROUP D — Full Event Flow
oracle-db
     ↓
zookeeper
     ↓
kafka
     ↓
discovery-server
     ↓
api-gateway
     ↓
create-user-service
          ↓
     user-created topic
          ↓
 ┌────────┴────────┐
 ↓                 ↓
email-service   audit-service
Test:
* producer publish
* email consume
* audit consume
* audit DB insert
---
# LEVEL 3 — DOCKER IMAGE TESTING
Now move to containers.
---
# FLOW
Service
   ↓
Dockerfile
   ↓
Docker Build
   ↓
Docker Run
   ↓
Container Testing
---
# Example
## Build
docker build -t vksvin9/kafka-backend-discovery-server:v1 .
## Run
docker run -p 8761:8761 \
vksvin9/kafka-backend-discovery-server:v1
## Validate
http://localhost:8761
---
# LEVEL 4 — LOCAL COMPOSE TESTING
Now full orchestration.
---
# FLOW
docker-compose.yml
        ↓
docker compose up -d
        ↓
Complete platform startup
---
# Components
oracle
zookeeper
kafka
redis
discovery-server
api-gateway
jwt-auth-service
create-user-service
email-service
audit-service
---
# Validate
| Check          | Expected    |
| -------------- | ----------- |
| docker ps      | all running |
| Eureka         | all UP      |
| login API      | working     |
| Kafka flow     | working     |
| consumers      | consuming   |
| DB persistence | working     |
---
# LEVEL 5 — PUSH TO CLOUD LAYER
After local compose stability.
---
# PUSH SOURCE
GitHub
Contains:
* source code
* Dockerfiles
* compose files
* scripts
---
# PUSH IMAGES
Docker Hub
Contains:
* deployable images
---
# LEVEL 6 — REMOTE DEVOPS MACHINE
Now deployment machine only needs:
| Required       | Needed |
| -------------- | ------ |
| Docker         | YES    |
| Docker Compose | YES    |
| Git            | YES    |
| Java           | NO     |
| Maven          | NO     |
| IntelliJ       | NO     |
---
# REMOTE FLOW
git clone
      ↓
docker compose up -d
      ↓
images pulled automatically
      ↓
platform starts
---
# YOUR FINAL ENTERPRISE ARCHITECTURE
                 ┌─────────────────┐
                 │   DEVELOPER     │
                 │     LAPTOP      │
                 └────────┬────────┘
                          │
          ┌───────────────┼────────────────┐
          │               │                │
          ▼               ▼                ▼
   Spring Boot      Docker Images      Source Code
    Testing            Build             Commit
          │               │                │
          ▼               ▼                ▼
   Local Runtime      Docker Hub        GitHub
          │               │                │
          └───────────────┼────────────────┘
                          ▼
                 DevOps Deployment Machine
                          │
                          ▼
                docker compose up -d
                          │
                          ▼
               Full Microservices Platform
---
# BEST PRACTICE FOR YOUR 8 GB SYSTEM
## Laptop
ONLY:
* coding
* local testing
* single containers
* lightweight compose testing
DO NOT:
* run ELK
* run Kubernetes
* run monitoring stack
* run full observability
locally initially.
---
# DevOps Machine
Use for:
* full compose deployment
* Kafka heavy testing
* future CI/CD
* future K8S
* future monitoring
---
# YOUR IMMEDIATE NEXT ACTION
Do ONLY this now:
# Step 1
Stabilize:
* discovery-server
* api-gateway
* jwt-auth-service
locally.
# Step 2
Create Dockerfile for:
* discovery-server
# Step 3
Build image.
# Step 4
Run container.
# Step 5
Validate runtime.
THEN move sequentially service-by-service. 
---
lets begin from testing locally >> testing invidual >> testing req grouping >> create docker-compose for another machine >> build image and push to gitea & docker hub >> use docker-compose up in anither machine
---
Phase 9B — Dockerfiles + Individual Container Testing
Current status:
Phase 9A ✅ Complete
Local Execution:
Oracle      ✅
Kafka       ✅
Redis       ✅
Zipkin      ✅
Discovery   ✅
Gateway     ✅
JWT Auth    ✅
Create User ✅
Email       ✅
Audit       ✅
Now we move to:
Phase 9B
Dockerfiles + Individual Container Testing
Goal:
Build Image
↓
Run Container
↓
Connect To Infrastructure
↓
Verify Eureka Registration
↓
Verify Health
↓
Verify Logs
↓
Freeze Image
Phase 9B Architecture
Infrastructure containers already running:
oracle-db
zookeeper
kafka
redis
zipkin
Network:
backend-network
Every service container will join:
backend-network
IMPORTANT CONTAINER HOSTNAMES
Inside Docker containers:
Oracle      -> oracle-db:1521
Kafka       -> kafka:29092
Redis       -> redis:6379
Zipkin      -> zipkin:9411
Discovery   -> discovery-server:8761
Not:
localhost
Environment variables will handle this.
PHASE 9B EXECUTION ORDER
1. Discovery Server
2. API Gateway
3. JWT Auth Service
4. Create User Service
5. Email Service
6. Audit Service
STEP 1 — DISCOVERY SERVER
Verify Project Builds
From root:
mvn clean package -DskipTests
Verify:
ms-2-discovery-server/target/*.jar
exists.
Dockerfile Strategy
Inside:
ms-2-discovery-server
Create:
Dockerfile
Dockerfile
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8761
ENTRYPOINT ["java","-jar","app.jar"]
Build Image
From project root:
docker build \
-t discovery-server:v1 \
./ms-2-discovery-server
Verify:
docker images
Expected:
discovery-server   v1
Run Discovery Container
docker run -d \
--name discovery-server \
--network backend-network \
-p 8761:8761 \
discovery-server:v1
Verify Logs
docker logs -f discovery-server
Expected:
Started DiscoveryServerApplication
and
Tomcat started on port 8761
Verify Eureka UI
Open:
http://localhost:8761
Expected:
Eureka Dashboard
Verify Health
curl http://localhost:8761/actuator/health
Expected:
{
  "status":"UP"
}
Freeze Image
Tag version:
docker tag discovery-server:v1 discovery-server:9b
Snapshot After Discovery
Container:
docker ps
Expected:
discovery-server
running.
STOP HERE
Do not build Gateway yet.
First complete and verify:
Discovery Server Image Build
Discovery Server Container Run
Eureka UI Verification
Health Check Verification
Then send:
1. Dockerfile
2. docker build output
3. docker ps
4. discovery-server logs
and we'll continue with:
Phase 9B Step 2
API Gateway Docker Container
---
Step 1: Verify Files

Inside:

ms-2-discovery-server

Verify:

Dockerfile
.dockerignore
pom.xml
src/

exist.

Step 2: Build Discovery Image

Run:

docker build -t discovery-server:v1 .

Important:

The final dot (.) is required.

The dot means:

Current folder = Docker build context
Step 3: Verify Image

After build succeeds:

docker images

Expected:

discovery-server   v1
Step 4: Start Discovery Container

First make sure the Docker network exists:

docker network ls

You should see:

backend-network

If not:

docker network create backend-network

Then run:

docker run -d ^
--name discovery-server ^
--network backend-network ^
-p 8761:8761 ^
discovery-server:v1

PowerShell uses:

^

for multiline commands in many examples, but you can also use a single line:

docker run -d --name discovery-server --network backend-network -p 8761:8761 discovery-server:v1
Step 5: Verify Container
docker ps

Expected:

discovery-server

running.

Step 6: Verify Logs
docker logs -f discovery-server

Look for:

Started DiscoveryServerApplication

and:

Tomcat started on port(s): 8761
Step 7: Verify Eureka

Open:

http://localhost:8761

Run these commands and send back:

docker build -t discovery-server:v1 .

docker images

docker ps

docker logs discovery-server

The most important output is the docker build output, because if the build fails we need to fix that before moving forward.
---
Build Image

Inside:

ms-3-api-gateway

Run:

docker build -t api-gateway:v1 .

Verify:

docker images

Expected:

api-gateway:v1
Run Container

Use Docker service names, not localhost:

docker run -d `
--name api-gateway `
--network backend-network `
-p 8080:8080 `
-e REDIS_HOST=redis `
-e DISCOVERY_SERVER_URL=http://discovery-server:8761/eureka `
-e ZIPKIN_ENDPOINT=http://zipkin:9411/api/v2/spans `
api-gateway:v1

PowerShell alternative (single line):

docker run -d --name api-gateway --network backend-network -p 8080:8080 -e REDIS_HOST=redis -e DISCOVERY_SERVER_URL=http://discovery-server:8761/eureka -e ZIPKIN_ENDPOINT=http://zipkin:9411/api/v2/spans api-gateway:v1
Validation
Container Running
docker ps

Expected:

discovery-server
api-gateway
Logs
docker logs api-gateway

Look for:

Started ApiGatewayApplication

and:

Registered with Eureka
Eureka Dashboard

Open:

http://localhost:8761

Expected registrations:

MS-2-DISCOVERY-SERVER
MS-3-API-GATEWAY
Health
curl http://localhost:8080/actuator/health

Expected:

{
  "status":"UP"
}
Phase 9B Progress
Discovery Server     ✅
API Gateway          ⏳ IN PROGRESS
JWT Auth Service     ⏳
Create User Service  ⏳
Email Service        ⏳
Audit Service        ⏳

Build and run API Gateway first. Then send:

docker build output
docker ps
docker logs api-gateway

and we'll proceed to JWT Auth Service.
---
JWT Auth Service is ready for containerization. The configuration is already following the same environment-variable strategy as the previous services.

Dependencies Identified

This service requires:

Oracle      ✅
Eureka      ✅
Zipkin      ✅
JWT Secret  ✅

This service does NOT require:

Kafka       ❌
Redis       ❌

based on the configuration you shared.

Dockerfile

Use the same standard template.

Only change:

LABEL service="jwt-auth-service"

and

EXPOSE 8081
.dockerignore
target
.git
.idea
.vscode
README.md
Build Image

Inside:

ms-4-jwt-auth-service

Run:

docker build -t jwt-auth-service:v1 .

Verify:

docker images

Expected:

jwt-auth-service:v1
Run Container

Since Oracle is already containerized:

oracle-db

Use:

docker run -d --name jwt-auth-service --network backend-network -p 8081:8081 `
-e DB_URL=jdbc:oracle:thin:@oracle-db:1521/FREEPDB1 `
-e DB_USERNAME=app_user `
-e DB_PASSWORD=app_pass `
-e DISCOVERY_SERVER_URL=http://discovery-server:8761/eureka `
-e ZIPKIN_ENDPOINT=http://zipkin:9411/api/v2/spans `
-e JWT_SECRET_KEY=MySuperSecureJwtSecretKeyMySuperSecureJwtSecretKey `
jwt-auth-service:v1

Single line version:

docker run -d --name jwt-auth-service --network backend-network -p 8081:8081 -e DB_URL=jdbc:oracle:thin:@oracle-db:1521/FREEPDB1 -e DB_USERNAME=app_user -e DB_PASSWORD=app_pass -e DISCOVERY_SERVER_URL=http://discovery-server:8761/eureka -e ZIPKIN_ENDPOINT=http://zipkin:9411/api/v2/spans -e JWT_SECRET_KEY=MySuperSecureJwtSecretKeyMySuperSecureJwtSecretKey jwt-auth-service:v1
Validation Checklist
Container
docker ps

Expected:

discovery-server
api-gateway
jwt-auth-service
Logs
docker logs jwt-auth-service

Look for:

Started JwtAuthServiceApplication
HikariPool started
Registering application with Eureka
Oracle Connectivity

Look for:

HHH000412

or

Initialized JPA EntityManagerFactory

No Oracle connection exceptions should appear.

Eureka Registration

Open:

http://localhost:8761

Expected:

MS-2-DISCOVERY-SERVER
MS-3-API-GATEWAY
MS-4-JWT-AUTH-SERVICE
Health
curl http://localhost:8081/actuator/health

Expected:

{
  "status":"UP"
}
Important Observation

You are now testing the first service that uses Oracle.

So the major thing we are validating is:

Container
   ↓
Oracle Container
   ↓
Oracle JDBC
   ↓
Hibernate/JPA
   ↓
EntityManager

If JWT Auth starts successfully, we know the Oracle container strategy is correct and the remaining Oracle-backed services will be much easier.

Next action:

Build jwt-auth-service image
Run container
Send docker logs jwt-auth-service

That will tell us whether the Oracle connectivity strategy is fully validated.
---
Correct. For containerization, the pom.xml and application.yml are sufficient.

ms-6-email-service Dockerfile

Create:

ms-6-email-service/Dockerfile

# =========================================================
# STAGE 1 - BUILD APPLICATION
# =========================================================

FROM maven:3.9.11-eclipse-temurin-21 AS builder

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src ./src

RUN mvn clean package -DskipTests

# =========================================================
# STAGE 2 - RUNTIME IMAGE
# =========================================================

FROM eclipse-temurin:21-jre

LABEL maintainer="vin"
LABEL project="ms-2-kafka-backend-platform"
LABEL service="ms-6-email-service"
LABEL version="1.0.0"

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8083

ENV JAVA_OPTS="\
-XX:+UseContainerSupport \
-XX:MaxRAMPercentage=75 \
-XX:+UseG1GC \
-XX:+UseStringDeduplication \
-Djava.security.egd=file:/dev/./urandom"

ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar app.jar"]
.dockerignore

Create:

ms-6-email-service/.dockerignore

target
.git
.idea
.vscode
README.md
Build Image

Inside:

cd ms-6-email-service

Build:

docker build -t email-service:v1 .

Verify:

docker images

Expected:

email-service    v1
Run Container

Since Email Service only requires:

Kafka
Eureka
Zipkin

Run:

docker run -d \
--name email-service \
--network backend-network \
-p 8083:8083 \
-e KAFKA_BOOTSTRAP_SERVERS=kafka:29092 \
-e DISCOVERY_SERVER_URL=http://discovery-server:8761/eureka \
-e ZIPKIN_ENDPOINT=http://zipkin:9411/api/v2/spans \
email-service:v1

Windows PowerShell single line:

docker run -d --name email-service --network backend-network -p 8083:8083 -e KAFKA_BOOTSTRAP_SERVERS=kafka:29092 -e DISCOVERY_SERVER_URL=http://discovery-server:8761/eureka -e ZIPKIN_ENDPOINT=http://zipkin:9411/api/v2/spans email-service:v1
Validation 1 - Container Running
docker ps

Expected:

discovery-server
api-gateway
jwt-auth-service
create-user-service
email-service
Validation 2 - Logs
docker logs -f email-service

Look for:

Started ...
Tomcat started on port 8083

Discovery Client initialized

Registering application MS-6-EMAIL-SERVICE

Kafka consumer started

Most important:

Subscribed to topic user-created

or

Partitions assigned
Validation 3 - Eureka

Open:

http://localhost:8761

Expected:

MS-2-DISCOVERY-SERVER
MS-3-API-GATEWAY
MS-4-JWT-AUTH-SERVICE
MS-5-CREATE-USER-SERVICE
MS-6-EMAIL-SERVICE
Validation 4 - Health
curl http://localhost:8083/actuator/health

Expected:

{
  "status":"UP"
}
End-to-End Kafka Validation

Create a user through Gateway:

POST http://localhost:8080/users

Expected flow:

Gateway
    ↓
Create User Service
    ↓
Oracle Save
    ↓
Kafka Publish
    ↓
Email Service Consume

Then immediately check:

docker logs -f email-service

Expected consumer log:

Received user-created event

User Name : xxx
Email     : xxx

Sending email...

At that point:

Oracle Save      ✅
Kafka Publish    ✅
Kafka Consume    ✅
Email Service    ✅

Phase 9B will be approximately 90% complete.

Next service after successful validation:

ms-7-audit-service

which will complete the full event-driven chain:

Gateway
 ↓
Create User
 ↓
Oracle Save
 ↓
Kafka Publish
 ↓
Email Consume
 ↓
Audit Consume
 ↓
Oracle Audit Save
---
Phase 9B — Email Service Complete Flow
Step 1 — Clean Builder Cache
docker builder prune -f

Verify:

docker system df
Step 2 — Build Image
cd ms-6-email-service

docker build -t email-service:v1 .

Verify:

docker images | findstr email-service

Expected:

email-service    v1
Step 3 — Start Discovery Server

Verify image:

docker images | findstr discovery-server

Run:

docker run -d ^
--name discovery-server ^
--network microservices-network ^
-p 8761:8761 ^
discovery-server:v1

Verify:

docker logs -f discovery-server

Open:

http://localhost:8761

Expected:

No instances available
Step 4 — Run Email Service

Because application.yml uses:

server:
  port: 8083

Run:

docker run -d --name email-service --network backend-network -p 8083:8083 -e KAFKA_BOOTSTRAP_SERVERS=kafka:29092 -e DISCOVERY_SERVER_URL=http://discovery-server:8761/eureka -e ZIPKIN_ENDPOINT=http://zipkin:9411/api/v2/spans email-service:v1

Step 5 — Verify Container
docker ps

Expected:

email-service
UP
Step 6 — Check Logs
docker logs -f email-service

Look for:

Started EmailServiceApplication

Registered with Eureka

Kafka Consumer Started
Step 7 — Health Check
curl http://localhost:8083/actuator/health

Expected:

{
  "status":"UP"
}
Step 8 — Verify Eureka Registration

Open:

http://localhost:8761

Expected:

MS-6-EMAIL-SERVICE

registered.

Phase 9B — Audit Service
Step 9 — Build Audit Service
cd ..

cd ms-7-audit-service

docker builder prune -f

docker build -t audit-service:v1 .

Verify:

docker images | findstr audit-service
Step 10 — Run Audit Service

Use the same pattern as Email Service.

Example:

docker run -d ^
--name audit-service ^
--network microservices-network ^
-p 8084:8084 ^
-e APP_PORT=8084 ^
-e APP_NAME=ms-7-audit-service ^
-e DISCOVERY_SERVER_URL=http://discovery-server:8761/eureka ^
-e KAFKA_BOOTSTRAP_SERVERS=kafka:9092 ^
-e DB_HOST=oracle-db ^
audit-service:v1

Adjust only if your audit service uses different environment variable names.

Step 11 — Verify Audit Service
docker logs -f audit-service

Expected:

Started AuditServiceApplication

Oracle Connected

Kafka Listener Started

Registered with Eureka
Step 12 — Health Check
curl http://localhost:8084/actuator/health

Expected:

{
  "status":"UP"
}
Step 13 — Verify Eureka

Expected services:

DISCOVERY-SERVER
MS-6-EMAIL-SERVICE
MS-7-AUDIT-SERVICE
Full Platform Validation

Start remaining containers:

docker run ...
api-gateway

docker run ...
jwt-auth-service

docker run ...
create-user-service

(or docker start if they already exist)

Verify:

docker ps

Expected:

oracle-db
zookeeper
kafka

discovery-server
api-gateway
jwt-auth-service
create-user-service
email-service
audit-service
End-to-End Test

Create a user through Gateway.

Expected flow:

API Gateway
        ↓
JWT Auth
        ↓
Create User Service
        ↓
Oracle User Save
        ↓
Kafka Publish
        ↓
Email Service Consume
        ↓
Audit Service Consume
        ↓
Oracle Audit Save

Verify:

Create User Service
docker logs create-user-service

Look for:

Published UserCreatedEvent
Email Service
docker logs email-service

Look for:

Received UserCreatedEvent
Audit Service
docker logs audit-service

Look for:

Audit Record Saved
Oracle

Verify audit table contains the new event.

Phase 9B Complete Criteria
✓ Discovery Server running
✓ API Gateway running
✓ JWT Auth Service running
✓ Create User Service running
✓ Email Service running
✓ Audit Service running

✓ Eureka registration working
✓ Kafka producer working
✓ Kafka consumers working
✓ Oracle persistence working

✓ End-to-End Docker Flow Successful

After this, Phase 9C is:

Docker Compose Platform
→ Single command startup
→ Centralized environment variables
→ Complete platform deployment on another machine
---
Dockerfile
# =========================================================
# STAGE 1 - BUILD APPLICATION
# =========================================================

FROM maven:3.9.11-eclipse-temurin-21 AS builder

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src ./src

RUN mvn clean package -DskipTests

# =========================================================
# STAGE 2 - RUNTIME IMAGE
# =========================================================

FROM eclipse-temurin:21-jre

LABEL maintainer="vin"
LABEL project="ms-2-kafka-backend-platform"
LABEL service="ms-7-audit-service"
LABEL version="1.0.0"

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8084

ENV JAVA_OPTS="\
-XX:+UseContainerSupport \
-XX:MaxRAMPercentage=75 \
-XX:+UseG1GC \
-XX:+UseStringDeduplication \
-Djava.security.egd=file:/dev/./urandom"

ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar app.jar"]
.dockerignore
target/
.idea/
.vscode/
.git/
.gitignore
README.md
*.log
Build
cd ..\ms-7-audit-service
docker builder prune -f
docker build -t audit-service:v1 .
Verify Image
docker images | findstr audit-service

Expected:

audit-service    v1
Run Container

Since Eureka is currently down, run exactly like Email Service:

docker run -d --name audit-service --network backend-network -p 8084:8084 -e DB_URL=jdbc:oracle:thin:@oracle-db:1521/FREEPDB1 -e DB_USERNAME=app_user -e DB_PASSWORD=app_pass -e KAFKA_BOOTSTRAP_SERVERS=kafka:29092 -e DISCOVERY_SERVER_URL=http://discovery-server:8761/eureka -e ZIPKIN_ENDPOINT=http://zipkin:9411/api/v2/spans audit-service:v1
Validation
Container
docker ps
Logs
docker logs -f audit-service

Expected:

Started AuditServiceApplication
HikariPool started
Subscribed to topic(s): user-created
Successfully joined group
Health
curl http://localhost:8084/actuator/health

Expected:

{"status":"UP"}
Success Criteria

You should see:

Kafka Consumer Started
Oracle Connected
Subscribed to user-created
Started AuditServiceApplication

Eureka errors can be ignored for now exactly like Email Service.

After the build finishes, send:

docker build output

or

docker images | findstr audit-service
---
Phase 9D Plan
Step 1

Verify all images exist:

docker images

Expected:

discovery-server:v1
api-gateway:v1
jwt-auth-service:v1
create-user-service:v1
email-service:v1
audit-service:v1
Step 2

Choose naming convention.

Example:

<dockerhub-username>/discovery-server:v1
<dockerhub-username>/api-gateway:v1
<dockerhub-username>/jwt-auth-service:v1
<dockerhub-username>/create-user-service:v1
<dockerhub-username>/email-service:v1
<dockerhub-username>/audit-service:v1
Step 3

Tag images.

Example:

docker tag discovery-server:v1 <username>/discovery-server:v1

Repeat for all services.

Step 4

Login:

docker login
Step 5

Push one image first:

docker push <username>/discovery-server:v1

Verify.

Then push remaining services.

Why This Phase Matters

Once images are in a registry:

No Maven
No Java
No Source Code
No IDE

is required on the target machine.

Only:

docker compose up -d

using pulled images.

Before proceeding, verify:

docker images

and tell me:

1. Docker Hub username?
2. Gitea Registry already configured or not?
---
Tag Images
docker tag discovery-server:v1 vksvin9/discovery-server:v1

docker tag api-gateway:v1 vksvin9/api-gateway:v1

docker tag jwt-auth-service:v1 vksvin9/jwt-auth-service:v1

docker tag create-user-service:v1 vksvin9/create-user-service:v1

docker tag email-service:v1 vksvin9/email-service:v1

docker tag audit-service:v1 vksvin9/audit-service:v1
Verify Tags
docker images

You should now see:

vksvin9/discovery-server:v1
vksvin9/api-gateway:v1
vksvin9/jwt-auth-service:v1
vksvin9/create-user-service:v1
vksvin9/email-service:v1
vksvin9/audit-service:v1
Login
docker login -u vksvin9

Enter the new PAT when prompted.

Push Images
docker push vksvin9/discovery-server:v1

docker push vksvin9/api-gateway:v1

docker push vksvin9/jwt-auth-service:v1

docker push vksvin9/create-user-service:v1

docker push vksvin9/email-service:v1

docker push vksvin9/audit-service:v1
Verify on Docker Hub

You should have six repositories:

discovery-server
api-gateway
jwt-auth-service
create-user-service
email-service
audit-service

each containing:

v1

tag.

After tagging, run:

docker images

and share the updated output before starting the pushes
---
Next Phase (9E)

Now stop thinking like a developer machine.

Think like a deployment machine.

Create:

docker-compose-platform-deploy.yml

Replace every:

build:
  context:
  dockerfile:

with:

image: vksvin9/discovery-server:v1

Example:

discovery-server:
  image: vksvin9/discovery-server:v1

Do the same for:

api-gateway
jwt-auth-service
create-user-service
email-service
audit-service

Keep:

environment:
depends_on:
healthcheck:
volumes:
ports:
logging:
networks:

unchanged.

Fresh Machine Deployment Architecture

Machine 2:

docker-compose-oracle.yml
          ↓

docker-compose-infra.yml
          ↓

docker-compose-platform-deploy.yml

No source code.

No Maven.

No JDK.

No IDE.

Only Docker.

That is the real proof that the platform is deployable.

Recommendation

Before creating the deploy compose, verify on Docker Hub that all 6 repositories exist and show:

Tag: v1

If they do, the next step is:

Phase 9E
Create docker-compose-platform-deploy.yml

using Docker Hub images instead of local builds. This file will be the one used on the future 16 GB deployment machine.
---
Recommended Sequence
Phase 9E

Create:

docker-compose-platform-deploy.yml

using Docker Hub images.

Phase 9F

Create Deployment Repository in Gitea

Example:

ms-2-kafka-backend-platform-deployment

Push:

docker-compose-oracle.yml
docker-compose-infra.yml
docker-compose-platform-deploy.yml
README.md

Commit:

git init
git add .
git commit -m "Initial deployment repository"
git remote add origin <gitea-url>
git push -u origin main
Phase 9G

Fresh Machine Validation

On another machine:

git clone <deployment-repository>

docker compose -f docker-compose-oracle.yml up -d

docker compose -f docker-compose-infra.yml up -d

docker compose -f docker-compose-platform-deploy.yml up -d

Expected:

Clone Repo
↓
Pull Images From Docker Hub
↓
Start Platform

No source code required.

Why This Is Better

You now separate:

Application Repository
ms-2-kafka-backend-platform

Contains:

Java code
Dockerfiles
POM files
Tests
Deployment Repository
ms-2-kafka-backend-platform-deployment

Contains:

Compose files
Environment configs
Deployment scripts
Runbooks

This separation becomes extremely useful when you later build:

Gitea CI/CD
        ↓
Build Image
        ↓
Push Docker Hub
        ↓
Update Deployment Repo
        ↓
Deploy

So yes, before moving into CI/CD, I would push the deployment artifacts (compose files and deployment documentation) into a dedicated Gitea deployment repository. That becomes the source of truth for server deployments.
---


==========================================================================================================
# Phase 10: Kubernetes
## Build
For each service:
* Deployment
* Service
* ConfigMap
* Secret
* Ingress
## Test
* Deploy to [Minikube](https://minikube.sigs.k8s.io?utm_source=chatgpt.com)
* Verify pods and service communication
## Deliverable
Kubernetes-ready platform.
---
==========================================================================================================
# Phase 11: Distributed Tracing
## Build
* Micrometer Tracing
* [Zipkin](https://zipkin.io?utm_source=chatgpt.com) or [Jaeger](https://www.jaegertracing.io?utm_source=chatgpt.com)
## Test
* Execute end-to-end request
* Confirm trace visibility
## Deliverable
Request flow tracing.
---
==========================================================================================================
# Phase 12: Centralized Logging
## Build
* Structured JSON logs
* [Elasticsearch](https://www.elastic.co/elasticsearch?utm_source=chatgpt.com)
* [Logstash](https://www.elastic.co/logstash?utm_source=chatgpt.com)
* [Kibana](https://www.elastic.co/kibana?utm_source=chatgpt.com)
## Test
* Search logs by `traceId`
## Deliverable
Central log aggregation.
---
==========================================================================================================
# Phase 13: Metrics and Dashboards
## Build
* [Prometheus](https://prometheus.io?utm_source=chatgpt.com)
* [Grafana](https://grafana.com?utm_source=chatgpt.com)
## Test
* View JVM and application metrics
## Deliverable
Monitoring dashboards.
---
==========================================================================================================
# Phase 14: CI/CD Pipeline
## Build
Using [GitHub Actions](https://github.com/features/actions?utm_source=chatgpt.com) or [Jenkins](https://www.jenkins.io?utm_source=chatgpt.com):
* Build
* Test
* Docker image creation
* Push to registry
* Kubernetes deployment
## Test
* Commit code and verify automatic deployment
## Deliverable
Automated build and deployment.
---
==========================================================================================================
# Phase 15: Additional Business Services
Add independently as needed:
* notification-service
* report-service
* payment-service
* order-service
* product-service
* inventory-service
---
# Recommended Execution Order
1. Foundation Template
2. discovery-server
3. jwt-auth-service
4. api-gateway
5. create-user-service
6. email-service
7. audit-service
8. Redis
9. Docker/Docker-compose
10. Kubernetes
11. Tracing
12. ELK
13. Metrics
14. CI/CD
==========================================================================================================
==========================================================================================================
==========================================================================================================
==========================================================================================================
==========================================================================================================
==========================================================================================================
==========================================================================================================
==========================================================================================================