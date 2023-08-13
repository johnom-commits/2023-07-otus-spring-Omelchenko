package ru.otus.homework.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class ResourceConfig {
    @Value("${app.datasource.file-name}")
    private String fileNameDataSource;
}
