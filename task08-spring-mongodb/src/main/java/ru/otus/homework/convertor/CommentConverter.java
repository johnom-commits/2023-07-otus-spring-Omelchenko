package ru.otus.homework.convertor;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.otus.homework.domain.Comment;

@Component
public class CommentConverter implements Converter<Comment, String> {

    @Override
    public String convert(Comment comment) {
        return "%s. %s".formatted(comment.getId(), comment.getName());
    }
}
