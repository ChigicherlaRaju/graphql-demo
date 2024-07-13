# Use the official OpenJDK base image
FROM eclipse-temurin:21-jdk-alpine

# Install bash and clean up package cache in a single step
RUN apk update && \
    apk add --no-cache bash && \
    rm -rf /var/cache/apk/*

# Set the working directory in the container
WORKDIR /app

# Copy the application JAR file into the container
COPY target/graphql-demo.jar /app/graphql-demo.jar

# Create a directory for logs
RUN mkdir -p /app/logs

# Expose the port the application runs on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "/app/graphql-demo.jar"]
