FROM eclipse-temurin:17-jdk-jammy

# Set the working directory
WORKDIR /app

# Copy Maven wrapper and project files
COPY . .

# Build the project with Maven
RUN ./mvnw clean package -DskipTests

# Copy the built JAR file to the container
COPY target/site-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your application runs on
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]
