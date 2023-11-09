package ru.otus.homework.repository;

import ru.otus.homework.domain.Book;
import ru.otus.homework.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    List<Comment> getAllCommentsByBookId(long bookId);

    Optional<Comment> getById(long id);

    void addComment(String text, Book book);

    void deleteComment(long id);

    void updateComment(long id, String text);
}
