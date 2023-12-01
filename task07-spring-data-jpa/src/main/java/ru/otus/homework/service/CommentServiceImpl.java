package ru.otus.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.domain.Book;
import ru.otus.homework.domain.Comment;
import ru.otus.homework.dto.CommentDto;
import ru.otus.homework.exception.NotFoundException;
import ru.otus.homework.mapper.CommentMapping;
import ru.otus.homework.repository.BookRepository;
import ru.otus.homework.repository.CommentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final BookRepository bookRepository;

    private final CommentRepository commentRepository;

    private final CommentMapping commentMapping;

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getByBookId(Long bookId) {
        return commentRepository.findByBookId(bookId);
    }

    @Override
    @Transactional(readOnly = true)
    public Comment getById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found comment with id = " + id));
    }

    @Override
    @Transactional
    public void create(CommentDto commentDto) {
        Book book = bookRepository.findById(commentDto.book_id())
                .orElseThrow(() -> new NotFoundException("Not found book with id = " + commentDto.book_id()));
        Comment comment = commentMapping.toComment(commentDto, book);
        commentRepository.save(comment);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(Long id, String text) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found comment with id = " + id));
        comment.setName(text);
        commentRepository.save(comment);
    }
}
