package ru.otus.homework.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.homework.domain.Author;
import ru.otus.homework.domain.Genre;
import ru.otus.homework.repositories.AuthorRepository;
import ru.otus.homework.repositories.GenreRepository;

@ChangeLog(order = "000")
public class InitMongoDBDataChangeLog {
    private Genre novel;
    private Genre poem;
    private Genre story;
    private Author visotskiy;
    private Author makarenko;

    @ChangeSet(order = "001", id = "dropDB", runAlways = true, author = "johnom")
    public void dropDB(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(order = "002", id = "initGenres", runAlways = true, author = "johnom")
    public void initGenres(GenreRepository genreRepository) {
        novel = genreRepository.save(Genre.builder().name("Роман").build());
        poem = genreRepository.save(Genre.builder().name("Поэзию").build());
        story = genreRepository.save(Genre.builder().name("Рассказ").build());
    }

    @ChangeSet(order = "003", id = "initAuthors", runAlways = true, author = "johnom")
    public void initAuthors(AuthorRepository authorRepository) {
        visotskiy = authorRepository.save(Author.builder().name("Высоцкий").build());
        makarenko = authorRepository.save(Author.builder().name("Макаренко").build());
    }
}
