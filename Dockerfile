# ---- Build Stage ----
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# ---- Runtime Stage ----
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Install curl & bash for waiting
RUN apk add --no-cache bash curl

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

CMD ["sh", "-c", "until nc -z mysql-db 3306; do echo waiting for mysql...; sleep 2; done && java -jar app.jar"]
