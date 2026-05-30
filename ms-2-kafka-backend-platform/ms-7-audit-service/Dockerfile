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