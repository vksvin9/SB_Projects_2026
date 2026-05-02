# =========================
# BASE IMAGE (JAVA 21)
# =========================
FROM eclipse-temurin:21-jdk-jammy

# =========================
# WORKDIR
# =========================
WORKDIR /app

# =========================
# COPY JAR
# =========================
COPY target/sb-09-docker3-db-compose-0.0.1-SNAPSHOT.jar app.jar

# =========================
# EXPOSE PORT
# =========================
EXPOSE 8080

# =========================
# MEMORY LIMITED START
# =========================
ENTRYPOINT ["java","-Xms256m","-Xmx512m","-jar","app.jar"]