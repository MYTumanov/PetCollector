FROM amazoncorretto:17-alpine3.17-jdk

ADD ./target/pet-collector-authenticator.jar .
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "pet-collector-authenticator.jar"]