FROM openjdk:17-jdk-slim

ADD ./target/pet-collector-server.jar .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "pet-collector-server.jar"]