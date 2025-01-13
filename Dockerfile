# Build stage
FROM maven:3-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:17-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar techx-api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "techx-api.jar"]