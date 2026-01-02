# Use the official openjdk base image (version 11 or later)
FROM eclipse-temurin:8-jre-jammy

# Set the working directory inside the container
WORKDIR /app

# Copy the jar file into the container's working directory
COPY target/khadar-shoppingwebsite.jar /app/khadar-shoppingwebsite.jar

# Expose the port that the Spring Boot app will run on
EXPOSE 8080

# Command to run the Spring Boot app inside the container
CMD ["java", "-jar", "khadar-shoppingwebsite.jar"]
