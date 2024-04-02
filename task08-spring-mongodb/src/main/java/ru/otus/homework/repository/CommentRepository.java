package ru.otus.homework.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework.domain.Comment;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {

    List<Comment> findByBookId(String bookId);

    void deleteByBookId(String bookId);
}
