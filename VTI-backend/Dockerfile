FROM openjdk:17-jdk-slim

WORKDIR /app

RUN apt-get update && apt-get install -y maven && apt-get clean

COPY . /app

WORKDIR /app

RUN chmod +x ./mvnw

RUN ./mvnw clean package -DskipTests

ENV SPRING_PROFILES_ACTIVE=prod


ENTRYPOINT ["java", "-jar", "target/VTI-backend-0.0.1-SNAPSHOT.jar"]