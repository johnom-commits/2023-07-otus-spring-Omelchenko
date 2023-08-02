package ru.otus.homework.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.core.io.Resource;

@Getter
@AllArgsConstructor
public class ResourceConfig {
    private Resource source;
}
