package ru.otus.homework.config;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.homework.domain.Genre;
import ru.otus.homework.dto.GenreDto;

@Configuration
public class OrikaConfig {

  /*  @Bean
    public MapperFacade mapperFacade() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(Genre.class, GenreDto.class)
                .field("id", "id")
                .field("name", "name")
                .byDefault()
                .register();

        return mapperFactory.getMapperFacade();
    }*/
}
