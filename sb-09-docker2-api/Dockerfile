# ---------- STAGE 1: BUILD ----------
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app
COPY . .

RUN mvn clean package -DskipTests

# ---------- STAGE 2: RUNTIME ----------
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

COPY --from=build /app/target/sb-09-docker2-api-0.0.1-SNAPSHOT.jar sb-09-docker2-api-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","sb-09-docker2-api-0.0.1-SNAPSHOT.jar"]