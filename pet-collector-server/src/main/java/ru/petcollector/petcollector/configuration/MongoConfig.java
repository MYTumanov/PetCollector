package ru.petcollector.petcollector.configuration;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
@EnableMongoAuditing
@PropertySource("classpath:application.properties")
@EnableMongoRepositories(basePackages = "ru.petcollector.petcollector.repository")
public class MongoConfig extends AbstractMongoClientConfiguration {

    @NotNull
    @Value("${spring.data.mongodb.host}")
    private String host;

    @NotNull
    @Value("${spring.data.mongodb.port}")
    private String port;

    @NotNull
    @Value("${spring.data.mongodb.username:}")
    private String username;

    @NotNull
    @Value("${spring.data.mongodb.password:}")
    private String password;

    @NotNull
    @Value("${spring.data.mongodb.database}")
    private String database;

    @NotNull
    @Override
    public MongoClient mongoClient() {
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString("mongodb://" + host + ":" + port))
                .build();
        return MongoClients.create(settings);
    }

    @NotNull
    @Override
    protected String getDatabaseName() {
        return database;
    }

}
