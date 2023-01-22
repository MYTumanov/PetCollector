FROM amazoncorretto:17-alpine3.17-jdk

ADD ./target/pet-collector-server.jar .
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "pet-collector-server.jar"]