package ru.otus.homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.otus.homework.config.ApplicationConfig;

@EnableConfigurationProperties(ApplicationConfig.class)
@SpringBootApplication
public class SpringBootConfigurationApp {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootConfigurationApp.class, args);
	}

}
