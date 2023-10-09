package ru.otus.homework.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.convertor.AuthorConverter;
import ru.otus.homework.convertor.BookDtoConverter;
import ru.otus.homework.convertor.GenreConverter;
import ru.otus.homework.domain.Book;
import ru.otus.homework.dto.BookCreateDto;
import ru.otus.homework.dto.BookUpdateDto;
import ru.otus.homework.service.AuthorService;
import ru.otus.homework.service.BookService;
import ru.otus.homework.service.GenreService;

@ShellComponent
@RequiredArgsConstructor
public class ShellAppRunner {

    private final AuthorService authorService;

    private final GenreService genreService;

    private final BookService bookService;

    private final BookDtoConverter bookDtoConverter;

    private final AuthorConverter authorConverter;

    private final GenreConverter genreConverter;

    @ShellMethod(value = "save new book", key = {"insert", "i"})
    public void addBook(@ShellOption String title, @ShellOption String authorId, @ShellOption String genreId) {
        BookCreateDto book = new BookCreateDto(title, Long.parseLong(authorId), Long.parseLong(genreId));
        bookService.saveBook(book);
    }

    @ShellMethod(value = "update book", key = {"update", "u"})
    public void updateBook(@ShellOption String id,
                           @ShellOption String title,
                           @ShellOption String authorId,
                           @ShellOption String genreId) {
        BookUpdateDto book = new BookUpdateDto(Long.parseLong(id),
                title,
                Long.parseLong(authorId),
                Long.parseLong(genreId));
        bookService.update(book);
    }

    @ShellMethod(value = "read all books", key = {"all-books", "ab"})
    public String readBooks() {
        final StringBuilder builder = new StringBuilder();
        bookService.getAll().forEach(
                book -> builder.append(bookDtoConverter.convert(book))
                        .append("\n")
        );
        return formatText(builder);
    }

    @ShellMethod(value = "read book", key = {"book", "b"})
    public String readBook(@ShellOption String id) {
        Book book = bookService.getById(Long.parseLong(id));
        return bookDtoConverter.convert(book);
    }

    @ShellMethod(value = "delete book by id", key = {"delete-book", "db"})
    public void deleteBook(@ShellOption String id) {
        bookService.deleteById(Long.parseLong(id));
    }

    @ShellMethod(value = "list all authors", key = {"authors", "a"})
    public String authors() {
        final StringBuilder builder = new StringBuilder();
        authorService.getAllAuthors().forEach(
                author -> builder.append(authorConverter.convert(author))
                        .append("\n")
        );
        return formatText(builder);
    }

    @ShellMethod(value = "list all genres", key = {"genres", "g"})
    public String genres() {
        final StringBuilder builder = new StringBuilder();
        genreService.getAllGenres().forEach(
                genre -> builder.append(genreConverter.convert(genre))
                        .append("\n")
        );
        return formatText(builder);
    }

    private String formatText(StringBuilder builder) {
        int n = builder.length();
        builder.delete(n - 1, n);
        return builder.toString();
    }
}
