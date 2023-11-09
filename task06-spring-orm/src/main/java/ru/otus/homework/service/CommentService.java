package ru.otus.homework.service;

import ru.otus.homework.domain.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getCommentByBookId(long bookId);

    Comment getById(long id);

    void createComment(String text, long id);

    void deleteComment(long id);

    void updateComment(long l, String text);
}
