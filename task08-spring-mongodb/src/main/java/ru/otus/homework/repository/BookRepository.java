package ru.otus.homework.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework.domain.Book;

public interface BookRepository extends MongoRepository<Book, String> {
}
