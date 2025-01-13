# Build stage
FROM maven:3-eclipse-temurin-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:17-alpine
COPY --from=build /target/*.jar techx-api.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","techx-api.jar"]