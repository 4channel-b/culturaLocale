# Use a base image with Java 21 JDK to compile
FROM eclipse-temurin:21.0.2_13-jdk-jammy as builder

# Add Maintainer Info
LABEL maintainer="4channel-b"

# Set the working directory in the Docker image
WORKDIR /app

# Copy the Maven wrapper and pom.xml to the image
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Make it executable
RUN chmod +x ./mvnw

# Copy the source code
COPY src src

# Install Maven
RUN apt-get update && apt-get install -y maven

# Use Maven to build the application
RUN mvn package -DskipTests

# We could use a JRE image here, but since we downloaded
# the JDK one already we're not going to waste space on the local machine
# though in production it would be better to use JRE for resource purposes.
FROM eclipse-temurin:21.0.2_13-jdk-jammy

# Copy the JAR file from the builder stage, this image shall only have this
COPY --from=builder /app/target/*.jar app.jar

# Expose port 8080 for the web server
EXPOSE 8080

# Execute!
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]