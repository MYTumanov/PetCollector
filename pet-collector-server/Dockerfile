FROM openjdk:17-jdk-slim

ADD ./target/pet-collector-server.jar .
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "pet-collector-server.jar"]