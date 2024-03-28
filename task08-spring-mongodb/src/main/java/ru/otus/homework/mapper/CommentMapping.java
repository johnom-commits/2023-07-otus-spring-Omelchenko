package ru.otus.homework.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.homework.domain.Comment;
import ru.otus.homework.dto.CommentDto;

@Component
@RequiredArgsConstructor
public class CommentMapping {

    public Comment toComment(CommentDto commentDto) {
        return new Comment(commentDto.text());
    }
}
