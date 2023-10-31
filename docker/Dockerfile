# Use a base image with Java 17
FROM openjdk:17-jdk-alpine

# Set a working directory within the container
WORKDIR /app

# Copy the JAR file from your local build context to the container
COPY ../build/libs/forum-italk-0.0.1-SNAPSHOT.jar /app

# Copy the application.properties into the container's classpath
COPY ../src/main/resources/application.properties /app

# Expose the port your Spring Boot application listens on
EXPOSE 8080

# Define environment variables for your PostgreSQL database
ENV SPRING_DATASOURCE_URL=${POSTGRES_URL}
ENV SPRING_DATASOURCE_USERNAME=${DB_USERNAME}
ENV SPRING_DATASOURCE_PASSWORD=${DB_PASSWORD}

# Copy your .env file (or other config files) into the container
COPY ../.env /app

# Run your Spring Boot application
CMD ["java", "-jar", "forum-italk-0.0.1-SNAPSHOT.jar"]
