package ru.otus.homework.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.homework.domain.Comment;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public List<Comment> getAllCommentsByBookId(Long bookId) {
        return em.createQuery("select c from Comment c where c.book.id = :id", Comment.class)
                .setParameter("id", bookId)
                .getResultList();
    }

    @Override
    public Optional<Comment> getById(Long id) {
        return Optional.ofNullable(em.find(Comment.class, id));
    }

    @Override
    public void addComment(Comment comment) {
        em.persist(comment);
    }

    @Override
    public void deleteComment(Long id) {
        em.createQuery("delete from Comment c where c.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public void updateComment(Comment comment, String text) {
        comment.setName(text);
        em.merge(comment);
    }
}
