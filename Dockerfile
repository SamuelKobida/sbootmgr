FROM openjdk:19-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/SpringBoot-0.0.1.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]