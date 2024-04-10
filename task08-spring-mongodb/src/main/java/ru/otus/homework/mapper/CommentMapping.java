package ru.otus.homework.mapper;

import org.springframework.stereotype.Component;
import ru.otus.homework.domain.Book;
import ru.otus.homework.domain.Comment;
import ru.otus.homework.dto.CommentDto;

@Component
public class CommentMapping {

    public Comment toComment(CommentDto commentDto, Book book) {
        return new Comment(commentDto.text(), book);
    }
}
