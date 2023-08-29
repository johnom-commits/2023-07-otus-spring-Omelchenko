package ru.otus.homework.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.homework.service.IOServiceStream;

@Getter
@Configuration
@PropertySource("classpath:application.property")
public class ApplicationConfig {

    @Value("${app.datasource.file-name}")
    private String fileNameDataSource;

    @Bean
    public IOServiceStream ioServiceStream() {
        return new IOServiceStream(System.out, System.in);
    }
}
