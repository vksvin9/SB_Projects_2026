# =========================================================
# STAGE 1 — BUILD APPLICATION
# =========================================================

# Maven + Java 21 build image
FROM maven:3.9.11-eclipse-temurin-21 AS builder

# Set working directory
WORKDIR /app

# Copy pom.xml first for dependency caching
COPY pom.xml .

# Download dependencies
RUN mvn dependency:go-offline

# Copy source code
COPY src ./src

# Build executable jar
RUN mvn clean package -DskipTests

# =========================================================
# STAGE 2 — CREATE LIGHTWEIGHT RUNTIME IMAGE
# =========================================================

# Lightweight Java runtime image
FROM eclipse-temurin:21-jre

# Metadata
LABEL maintainer="vin"
LABEL project="kafka-backend-platform"
LABEL service="api-gateway"
LABEL java.version="21"

# Set working directory
WORKDIR /app

# Copy generated jar from builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose service port
EXPOSE 8080

# JVM optimizations for Docker containers
ENV JAVA_OPTS="\
-XX:+UseContainerSupport \
-XX:MaxRAMPercentage=75 \
-XX:+UseG1GC \
-XX:+UseStringDeduplication \
-Djava.security.egd=file:/dev/./urandom"

# Start Spring Boot application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]