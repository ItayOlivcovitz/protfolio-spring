# Use a base image with JDK 17
FROM eclipse-temurin:17-jdk-jammy as builder

# Set the working directory inside the container
WORKDIR /app

# Copy all project files to the container
COPY . .

# Ensure Maven wrapper has executable permissions
RUN chmod +x mvnw

# Run Maven to build the project, skip tests, and list the target directory for debugging
RUN ./mvnw clean package -DskipTests && ls -l /app/target

# Use a smaller runtime image to run the application
FROM eclipse-temurin:17-jdk-jammy

# Set the working directory for the runtime container
WORKDIR /app

# Copy the built JAR file from the builder stage to the runtime image
COPY --from=builder /app/target/*.jar app.jar

# Debugging: List files in the runtime container
RUN echo "Contents of /app:" && ls -l /app

# Expose the port your application runs on (default is 8080 for Spring Boot)
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]
