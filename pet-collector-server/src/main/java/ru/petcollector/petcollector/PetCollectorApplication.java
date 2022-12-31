package ru.petcollector.petcollector;

import org.jetbrains.annotations.NotNull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import ru.petcollector.petcollector.component.Bootstrap;

@SpringBootApplication
public class PetCollectorApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context =  SpringApplication.run(PetCollectorApplication.class, args);
		@NotNull final Bootstrap bootstrap = new Bootstrap(context);
		// bootstrap.run();
	}

}
