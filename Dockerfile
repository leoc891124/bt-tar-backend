# Use an official OpenJDK 17 image
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy Maven wrapper and project files
COPY . .

# Give execute permission to Maven wrapper (important for Render)
RUN chmod +x mvnw

# Build the app (skip tests)
RUN ./mvnw clean package -DskipTests

# Expose the default Spring Boot port
EXPOSE 8080

# Run the jar file
CMD ["java", "-jar", "target/*.jar"]