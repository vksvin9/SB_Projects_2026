# =========================
# STAGE 1: BUILD (Maven)
# =========================
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

# copy only pom first (cache optimization)
COPY pom.xml .

# download dependencies
RUN mvn dependency:go-offline

# copy source code
COPY src ./src

# build jar
RUN mvn clean package -DskipTests


# =========================
# STAGE 2: RUNTIME (Java)
# =========================
FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

# copy jar from build stage
COPY --from=build /app/target/sb-09-docker4-db-wo-compose-0.0.1-SNAPSHOT.jar sb-09-docker4-db-wo-compose-0.0.1-SNAPSHOT.jar

EXPOSE 8080

# limit memory (important for your system)
ENTRYPOINT ["java","-Xms256m","-Xmx512m","-jar","sb-09-docker4-db-wo-compose-0.0.1-SNAPSHOT.jar"]