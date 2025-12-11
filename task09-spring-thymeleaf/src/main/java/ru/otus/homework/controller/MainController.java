package ru.otus.homework.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.otus.homework.dto.BookDto;
import ru.otus.homework.service.AuthorService;
import ru.otus.homework.service.BookService;
import ru.otus.homework.service.GenreService;

@Controller
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MainController {

    GenreService genreService;
    AuthorService authorService;
    BookService bookService;

    @GetMapping("/books")
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "books";
    }

    @GetMapping("/new_book")
    public String addNewBook() {
        return "new-book";
    }

    @GetMapping("/genres")
    public String getGenres(Model model) {
        model.addAttribute("genres", genreService.getAllGenres());
        return "genres";
    }

    @GetMapping("/authors")
    public String getAuthors(Model model) {
        model.addAttribute("authors", authorService.getAllAuthors());
        return "authors";
    }

//    @GetMapping("/add")
//    public String showAddBookForm(Model model) {
//        model.addAttribute("book", new BookDto()); // Book - ваша модель
//        return "add-book"; // Имя Thymeleaf-шаблона (add-book.html)
//    }

    @PostMapping("/books/save")
    public String saveBook(BookDto book) {
        // Сохранение книги в БД (ваша логика)
        return "redirect:/books"; // Редирект с сообщением об успехе
    }
}

