# 1) BASE IMAGE (Java 21 runtime)
# Uses Eclipse Temurin JDK 21 on Ubuntu Jammy → required for your Java 21 app
FROM eclipse-temurin:21-jdk-jammy

# 2) WORKING DIRECTORY
# All subsequent commands run inside /app in the container
WORKDIR /app

# 3) COPY JAR INTO CONTAINER
# Copies your built JAR from local target/ → container /app/
# Keeps the SAME file name inside container
COPY target/sb-09-dokcer1-hello-0.0.1-SNAPSHOT.jar sb-09-dokcer1-hello-0.0.1-SNAPSHOT.jar

# 4) EXPOSE PORT
# Documents that the app listens on port 8080 (Spring Boot default)
# (Note: this does NOT publish the port; -p does that at runtime)
EXPOSE 8080

# 5) START COMMAND (ENTRYPOINT)
# Runs the JAR when the container starts
# Must exactly match the copied JAR name
ENTRYPOINT ["java","-jar","sb-09-dokcer1-hello-0.0.1-SNAPSHOT.jar"]