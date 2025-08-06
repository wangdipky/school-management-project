#FROM maven:3.8.7-amazoncorretto-17 AS builder
#WORKDIR /app
#COPY . .
#RUN mvn clean package -DskipTests

FROM openjdk:17-oracle
#WORKDIR /app
COPY /target/identity-service-0.0.1-SNAPSHOT.jar identity-service-0.0.1-SNAPSHOT.jar
EXPOSE 8101
ENTRYPOINT ["java", "-jar", "identity-service-0.0.1-SNAPSHOT.jar"]