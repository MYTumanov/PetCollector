package ru.petcollector.petcollector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import ru.petcollector.petcollector.bootstrap.Bootstrap;

@SpringBootApplication
public class AuthenicatorServiceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx =  SpringApplication.run(AuthenicatorServiceApplication.class, args);
        Bootstrap bootstrap = ctx.getBean(Bootstrap.class);
        bootstrap.initClient();
    }

}
