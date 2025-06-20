package ru.otus.homework.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.homework.dto.AuthorDto;
import ru.otus.homework.dto.GenreDto;
import ru.otus.homework.repositories.BookRepository;
import ru.otus.homework.service.AuthorService;
import ru.otus.homework.service.BookService;
import ru.otus.homework.service.GenreService;

import java.util.List;

@Controller
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class GenreController {

    GenreService genreService;
    AuthorService authorService;
    MapperFacade orikaMapperFacade;
    BookService bookService;

    @GetMapping("/books")
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "books";
    }

    @GetMapping("/genres")
    public String getGenres(Model model) {
        model.addAttribute("genres", getGenreDtos());
        return "genres";
    }

    @GetMapping("/authors")
    public String getAuthors(Model model) {
        model.addAttribute("authors", getAuthorsDtos());
        return "authors";
    }

    private List<AuthorDto> getAuthorsDtos() {
        return orikaMapperFacade.mapAsList(authorService.getAllAuthors(), AuthorDto.class);
    }

    private List<GenreDto> getGenreDtos() {
        return orikaMapperFacade.mapAsList(genreService.getAllGenres(), GenreDto.class);
    }
}

