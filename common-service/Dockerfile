#FROM maven:3.8.7-amazoncorretto-17 AS builder
#WORKDIR /app
#COPY . .
#RUN mvn clean package -DskipTests

FROM openjdk:17-oracle
#WORKDIR /app
COPY target/master-data-service-0.0.1-SNAPSHOT.jar master-data-service-0.0.1-SNAPSHOT.jar
EXPOSE 8100
ENTRYPOINT ["java", "-jar", "master-data-service-0.0.1-SNAPSHOT.jar"]