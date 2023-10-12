package ru.petcollector.petcollector.configuration;

import java.util.concurrent.TimeUnit;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@Slf4j
@Configuration
@PropertySource("classpath:application.properties")
public class WebClientConfig {

    @Value("${pet.collector.server.host}")
    private String host;

    @Value("${pet.collector.server.port}")
    private String port;

    @NotNull
    private String baseUrl;

    public static final int TIMEOUT = 1000;

    @Bean
    public WebClient webClientWithTimeout() {
        final HttpClient clinet = HttpClient
                .create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, TIMEOUT)
                .doOnConnected(connection -> {
                    connection.addHandlerLast(new ReadTimeoutHandler(TIMEOUT, TimeUnit.MILLISECONDS));
                    connection.addHandlerLast(new WriteTimeoutHandler(TIMEOUT, TimeUnit.MILLISECONDS));
                });

        baseUrl = "http://" + host + ":" + port + "/api";
        log.info("PORT: " + port);
        log.info("HOST: " + host);
        log.info("URL: " + baseUrl);
        return WebClient.builder()
                .baseUrl(baseUrl)
                .filter(logRequest())
                .clientConnector(new ReactorClientHttpConnector(clinet))
                .build();
    }

    private static ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            log.info("Request: {} {}", clientRequest.method(), clientRequest.url());
            clientRequest.headers().forEach((name, values) -> values.forEach(value -> log.info("{}={}", name, value)));
            return Mono.just(clientRequest);
        });
    }

}
