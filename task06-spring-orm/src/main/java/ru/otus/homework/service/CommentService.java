package ru.otus.homework.service;

import ru.otus.homework.domain.Comment;
import ru.otus.homework.dto.CommentDto;

import java.util.List;

public interface CommentService {
    List<Comment> getByBookId(Long bookId);

    Comment getById(Long id);

    void create(CommentDto commentDto);

    void delete(Long id);

    void update(Long l, String text);
}
