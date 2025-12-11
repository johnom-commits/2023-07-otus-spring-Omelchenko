package ru.otus.homework.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.homework.domain.Author;
import ru.otus.homework.dto.AuthorDto;
import ru.otus.homework.dto.GenreDto;
import ru.otus.homework.mapper.AuthorMapping;
import ru.otus.homework.repositories.AuthorRepository;
import ru.otus.homework.service.AuthorService;
import ru.otus.homework.service.AuthorServiceImpl;
import ru.otus.homework.service.BookService;
import ru.otus.homework.service.GenreService;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MainController.class)
@Import(MainControllerTest.TestConfig.class)
@TestPropertySource(properties = "mongock.enabled=false")
class MainControllerTest {

    @TestConfiguration
    public static class TestConfig {
        @Bean
        public AuthorMapping authorMapping() {
            return new AuthorMapping();
        }

        @Bean
        public AuthorService authorService(AuthorRepository authorRepository) {
            return new AuthorServiceImpl(authorRepository, authorMapping());
        }

        @MockBean
        public static AuthorRepository authorRepository;
    }

    @Autowired
    private MockMvc mockMvc;
//    @MockBean
//    private AuthorRepository authorRepository;
    @MockBean
    private GenreService genreService;
    @Autowired
    private AuthorService authorService;
    @MockBean
    private BookService bookService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getAllBooks() {
    }

    @Test
    void addNewBook() {
    }

    @Test
    void getGenres() throws Exception {
        List<GenreDto> genres = List.of(GenreDto.builder().name("novel").build());
        when(genreService.getAllGenres()).thenReturn(genres);

        mockMvc.perform(get("/genres"))
                .andExpect(status().isOk())
                .andExpect(view().name("genres"))
                .andExpect(model().attributeExists("genres"));
    }

    @Test
    void getAuthors() throws Exception {
        List<Author> authors = List.of(Author.builder().name("Высоцкий").build());
        when(TestConfig.authorRepository.findAll()).thenReturn(authors);

        mockMvc.perform(get("/authors"))
                .andExpect(status().isOk())
                .andExpect(view().name("authors"))
                .andExpect(model().attributeExists("authors"));
    }

    @Test
    void saveBook() {
    }
}