# Use Maven and OpenJDK 21 for the build phase
FROM maven:3.9.8-eclipse-temurin-21 AS build

# Set working directory
WORKDIR /app

# Copy pom.xml to resolve dependencies
COPY pom.xml .

# Go offline to download dependencies
RUN mvn dependency:go-offline

# Copy the source code into the container
COPY src ./src

# Build the app, skipping tests
RUN mvn package -DskipTests

# Use OpenJDK 21 for the runtime phase
FROM openjdk:21

# Set working directory
WORKDIR /app

# Copy the built jar file from the build container
COPY --from=build /app/target/RestaurantQueue-0.0.1-SNAPSHOT.jar .

# Expose port 8080
EXPOSE 8080

# Set the entrypoint to run the jar file
ENTRYPOINT ["java", "-jar", "/app/RestaurantQueue-0.0.1-SNAPSHOT.jar"]
