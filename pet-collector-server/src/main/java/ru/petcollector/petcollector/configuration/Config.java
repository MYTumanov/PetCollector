package ru.petcollector.petcollector.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import ru.petcollector.petcollector.repository.UserRepository;

@Configuration
@EnableMongoRepositories(basePackageClasses = UserRepository.class)
@ComponentScan("ru.petcollector.petcollector")
public class Config {
}
