#Managing version using Parent pom (Tighly coupled should install parent before all)

#Created maven Project using terminal with minimum req
mvn archetype:generate -DgroupId=com.vin -DartifactId=user-service -DarchetypeArtifactId=maven-archetype-simple -DinteractiveMode=false
mvn archetype:generate -DgroupId=com.vin -DartifactId=user-service -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false

===========Run from Terminal (Recommended for real-world)===========
#Using Maven
cd sb-01-user-service-child
mvn spring-boot:run
#OR (build + run jar)
mvn clean package
java -jar target/sb-01-crud-jpa-h2-swg-0.0.1-SNAPSHOT.jar
