package ru.otus.homework.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.homework.domain.Book;
import ru.otus.homework.domain.Comment;
import ru.otus.homework.dto.CommentDto;
import ru.otus.homework.exception.NotFoundException;
import ru.otus.homework.repository.BookRepository;

@Component
@RequiredArgsConstructor
public class CommentMapping {

    private final BookRepository bookRepository;

    public Comment toComment(CommentDto commentDto) {
        Book book = bookRepository.getById(commentDto.book_id())
                .orElseThrow(
                        () -> new NotFoundException("Not found book with id = " + commentDto.book_id())
                );
        return new Comment(commentDto.text(), book);
    }
}
