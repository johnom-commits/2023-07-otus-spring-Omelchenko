package ru.otus.homework.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.homework.domain.Author;
import ru.otus.homework.domain.Book;
import ru.otus.homework.domain.Comment;
import ru.otus.homework.domain.Genre;
import ru.otus.homework.repository.AuthorRepository;
import ru.otus.homework.repository.BookRepository;
import ru.otus.homework.repository.CommentRepository;
import ru.otus.homework.repository.GenreRepository;

import java.util.List;

@ChangeLog(order = "001")
public class InitMongoDBDataChangeLog {

    private Author visotskiy;

    private Author makarenko;

    private Genre novel;

    private Genre poem;

    @ChangeSet(order = "000", id = "dropDB", runAlways = true, author = "johnom")
    public void dropDB(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(order = "001", id = "initAuthors", runAlways = true, author = "johnom")
    public void initAuthors(AuthorRepository authorRepository) {
        visotskiy = authorRepository.save(new Author("Высоцкий"));
        makarenko = authorRepository.save(new Author("Макаренко"));
    }

    @ChangeSet(order = "002", id = "initGenres", runAlways = true, author = "johnom")
    public void initGenres(GenreRepository genreRepository) {
        novel = genreRepository.save(new Genre("Роман"));
        poem = genreRepository.save(new Genre("Поэзия"));
    }

    @ChangeSet(order = "003", id = "initBooks", runAlways = true, author = "johnom")
    public void initBooks(CommentRepository commentRepository, BookRepository bookRepository) {
        List<Comment> comments = List.of(
                commentRepository.save(new Comment("Отличная книга"))
        );
        List<Comment> commentsMakarenko = List.of(
                commentRepository.save(new Comment("Отличный пример педагогики"))
        );
        bookRepository.save(new Book("Нерв", visotskiy, poem, comments));
        bookRepository.save(new Book("Педагогическая поэма", makarenko, novel, commentsMakarenko));
    }
}
