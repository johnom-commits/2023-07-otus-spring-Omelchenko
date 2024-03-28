package ru.otus.homework.service;

import ru.otus.homework.domain.Comment;
import ru.otus.homework.dto.CommentDto;

import java.util.List;

public interface CommentService {
    List<Comment> getByBookId(String bookId);

    Comment getById(String id);

    void create(CommentDto commentDto);
}
