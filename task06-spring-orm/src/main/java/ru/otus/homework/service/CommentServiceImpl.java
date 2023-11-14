package ru.otus.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    private final CommentRepository commentRepository;

    private final CommentMapping commentMapping;

    private final BookRepository bookRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getByBookId(Long bookId) {
        return commentRepository.getAllCommentsByBookId(bookId);
    }

    @Override
    @Transactional(readOnly = true)
    public Comment getById(Long id) {
        return commentRepository.getById(id)
                .orElseThrow(() -> new NotFoundException("Not found comment with id = " + id));
    }

    @Override
    @Transactional
    public void create(CommentDto commentDto) {
        Comment comment = commentMapping.toComment(commentDto);
        commentRepository.addComment(comment);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        commentRepository.deleteComment(id);
    }

    @Override
    @Transactional
    public void update(Long id, String text) {
        Comment comment = commentRepository.getById(id)
                .orElseThrow(() -> new NotFoundException("Not found comment with id = " + id));
        commentRepository.updateComment(comment, text);
    }
}
