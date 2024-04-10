package ru.otus.homework.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.homework.domain.Comment;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CommentRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Test
    void test() {
        List<Comment> actual = commentRepository.findAll();

        assertThat(actual).isNotNull().hasSize(2);
    }
}
