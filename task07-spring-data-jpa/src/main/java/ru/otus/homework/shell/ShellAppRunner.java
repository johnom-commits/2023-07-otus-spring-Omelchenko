package ru.otus.homework.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.convertor.AuthorConverter;
import ru.otus.homework.convertor.BookConverter;
import ru.otus.homework.convertor.CommentConverter;
import ru.otus.homework.convertor.GenreConverter;
import ru.otus.homework.domain.Comment;
import ru.otus.homework.dto.BookCreateDto;
import ru.otus.homework.dto.BookDto;
import ru.otus.homework.dto.BookUpdateDto;
import ru.otus.homework.dto.CommentDto;
import ru.otus.homework.service.AuthorService;
import ru.otus.homework.service.BookService;
import ru.otus.homework.service.CommentService;
import ru.otus.homework.service.GenreService;

@ShellComponent
@RequiredArgsConstructor
public class ShellAppRunner {

    private final AuthorService authorService;

    private final GenreService genreService;

    private final BookService bookService;

    private final BookConverter bookConverter;

    private final AuthorConverter authorConverter;

    private final GenreConverter genreConverter;

    private final CommentService commentService;

    private final CommentConverter commentConverter;

    @SuppressWarnings("unused")
    @ShellMethod(value = "save new book", key = {"insert", "i"})
    public void addBook(@ShellOption String title, @ShellOption String authorId, @ShellOption String genreId) {
        BookCreateDto book = new BookCreateDto(
                title,
                Long.parseLong(authorId),
                Long.parseLong(genreId)
        );
        bookService.saveBook(book);
    }

    @SuppressWarnings("unused")
    @ShellMethod(value = "update book", key = {"update", "u"})
    public void updateBook(@ShellOption String id,
                           @ShellOption String title,
                           @ShellOption String authorId,
                           @ShellOption String genreId) {
        BookUpdateDto book = new BookUpdateDto(
                Long.parseLong(id),
                title,
                Long.parseLong(authorId),
                Long.parseLong(genreId)
        );
        bookService.update(book);
    }

    @SuppressWarnings("unused")
    @ShellMethod(value = "read all books", key = {"all-books", "ab"})
    public String readBooks() {
        final StringBuilder builder = new StringBuilder();
        bookService.getAll().forEach(
                book -> builder
                        .append(bookConverter.convert(book))
                        .append("\n")
        );
        return formatText(builder);
    }

    @SuppressWarnings("unused")
    @ShellMethod(value = "read book", key = {"book", "b"})
    public String readBook(@ShellOption String id) {
        BookDto book = bookService.getById(Long.parseLong(id));
        return "%s. %s, %s, %s".formatted(
                book.id(),
                book.title(),
                book.authorDto().name(),
                book.genreDto().name()
        );
    }

    @SuppressWarnings("unused")
    @ShellMethod(value = "delete book by id", key = {"delete-book", "db"})
    public void deleteBook(@ShellOption String id) {
        bookService.deleteById(Long.parseLong(id));
    }

    @SuppressWarnings("unused")
    @ShellMethod(value = "list all authors", key = {"authors", "a"})
    public String authors() {
        final StringBuilder builder = new StringBuilder();
        authorService.getAllAuthors().forEach(
                author -> builder
                        .append(authorConverter.convert(author))
                        .append("\n")
        );
        return formatText(builder);
    }

    @SuppressWarnings("unused")
    @ShellMethod(value = "list all genres", key = {"genres", "g"})
    public String genres() {
        final StringBuilder builder = new StringBuilder();
        genreService.getAllGenres().forEach(
                genre -> builder
                        .append(genreConverter.convert(genre))
                        .append("\n")
        );
        return formatText(builder);
    }

    @SuppressWarnings("unused")
    @ShellMethod(value = "list comments by book id", key = {"comment-book-id", "cb"})
    public String comments(@ShellOption String id) {
        final StringBuilder builder = new StringBuilder();
        commentService.getByBookId(Long.parseLong(id))
                .forEach(comment -> builder
                        .append(commentConverter.convert(comment))
                        .append("\n")
                );
        return formatText(builder);
    }

    @SuppressWarnings("unused")
    @ShellMethod(value = "comment by id", key = {"comment", "c"})
    public String comment(@ShellOption String id) {
        Comment comment = commentService.getById(Long.parseLong(id));
        return commentConverter.convert(comment);
    }

    @SuppressWarnings("unused")
    @ShellMethod(value = "add comment", key = {"add-comment", "ac"})
    public void addComment(@ShellOption String bookId, String text) {
        commentService.create(
                new CommentDto(text, Long.parseLong(bookId))
        );
    }

    @SuppressWarnings("unused")
    @ShellMethod(value = "delete comment", key = {"delete-comment", "dc"})
    public void deleteComment(@ShellOption String id) {
        commentService.delete(Long.parseLong(id));
    }

    @SuppressWarnings("unused")
    @ShellMethod(value = "update comment", key = {"update-comment", "uc"})
    public void updateComment(@ShellOption String id, String text) {
        commentService.update(Long.parseLong(id), text);
    }

    private String formatText(StringBuilder builder) {
        int n = builder.length();
        if (n == 0) {
            return "Звиняйте, хлопцы, бананьев нема";
        }
        builder.delete(n - 1, n);
        return builder.toString();
    }
}
