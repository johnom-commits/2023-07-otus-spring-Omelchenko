package ru.otus.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.domain.Book;
import ru.otus.homework.domain.Comment;
import ru.otus.homework.exception.NotFoundException;
import ru.otus.homework.repository.BookRepository;
import ru.otus.homework.repository.CommentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final BookRepository bookRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getCommentByBookId(long bookId) {
        return commentRepository.getAllCommentsByBookId(bookId);
    }

    @Override
    @Transactional(readOnly = true)
    public Comment getById(long id) {
        return commentRepository.getById(id)
                .orElseThrow(() -> new NotFoundException("Not found comment with id = " + id));
    }

    @Override
    @Transactional
    public void createComment(String text, long id) {
        Book book = bookRepository.getById(id)
                .orElseThrow(() -> new NotFoundException("Not found book with id = " + id));
        commentRepository.addComment(text, book);
    }

    @Override
    @Transactional
    public void deleteComment(long id) {
        commentRepository.deleteComment(id);
    }

    @Override
    @Transactional
    public void updateComment(long id, String text) {
        commentRepository.updateComment(id, text);
    }
}
