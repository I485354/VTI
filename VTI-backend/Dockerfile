FROM openjdk:17-jdk-slim

WORKDIR /app

RUN apt-get update && apt-get install -y git

RUN git clone https://github.com/I485354/VTI.git . && cd VTI-backend

WORKDIR /app/VTI-backend

RUN chmod +x ./mvnw

RUN ./mvnw package

ENTRYPOINT ["java", "-jar", "target/VTI-backend-0.0.1-SNAPSHOT.jar"]