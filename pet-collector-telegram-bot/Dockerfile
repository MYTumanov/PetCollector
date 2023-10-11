FROM amazoncorretto:17-alpine3.17-jdk

USER 0
RUN mkdir -p ./pet-collector-telegram-bot/target

ADD ./target/pet-collector-telegram-bot.jar .
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "pet-collector-telegram-bot.jar"]