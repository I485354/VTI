FROM openjdk:17-jdk-slim

WORKDIR /app

RUN apt-get update && apt-get install -y maven && apt-get clean

#RUN git clone https://github.com/I485354/VTI.git . && cd VTI-backend

COPY . /app

WORKDIR /app

RUN chmod +x ./mvnw

RUN ./mvnw clean package -DskipTests


ENTRYPOINT ["java", "-jar", "target/VTI-backend-0.0.1-SNAPSHOT.jar"]