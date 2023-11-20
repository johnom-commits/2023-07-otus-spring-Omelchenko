package ru.otus.homework.repository;

import ru.otus.homework.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    List<Comment> getAllCommentsByBookId(Long bookId);

    Optional<Comment> getById(Long id);

    void deleteComment(Long id);

    void updateComment(Comment comment, String text);

    void addComment(Comment comment);
}
