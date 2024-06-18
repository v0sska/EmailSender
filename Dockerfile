# Stage 1: Build the application using Maven
FROM maven:3.8.4-openjdk-17-slim AS build

WORKDIR /app

# Copy the pom.xml and the source code
COPY pom.xml .
COPY src ./src

# Build the application and skip tests
RUN mvn clean package -DskipTests

# Stage 2: Run the application using OpenJDK 17
FROM openjdk:17-alpine

# Set the working directory
WORKDIR /app

# Copy the built jar file from the previous stage
COPY --from=build /app/target/*.jar app.jar

# Define the command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
