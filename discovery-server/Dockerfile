FROM openjdk:21
# Copy the Maven wrapper to the root of the container
COPY mvnw .
COPY .mvn .mvn

# Copy the project files and directories to the container
COPY pom.xml .
COPY src src

# Build the application without running tests
RUN ./mvnw clean package -DskipTests

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]