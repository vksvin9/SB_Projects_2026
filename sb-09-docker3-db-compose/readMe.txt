# stop + clean
docker compose down -v

# build jar
mvn clean package -DskipTests

# run system
docker compose up --build