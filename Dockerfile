# Use a base image with JDK 17
FROM eclipse-temurin:17-jdk-jammy as builder

# Set the working directory inside the container
WORKDIR /app

# Copy all project files to the container
COPY . .

# Run Maven to build the project and skip tests
RUN ./mvnw clean package -DskipTests

# Use a smaller runtime image to run the application
FROM eclipse-temurin:17-jdk-jammy

# Set the working directory for the runtime container
WORKDIR /app

# Copy the built JAR file from the builder stage to the runtime image
COPY --from=builder /app/target/site-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your application runs on (default is 8080 for Spring Boot)
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]
