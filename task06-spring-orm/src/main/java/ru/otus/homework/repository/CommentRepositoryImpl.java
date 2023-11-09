package ru.otus.homework.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.homework.domain.Book;
import ru.otus.homework.domain.Comment;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public List<Comment> getAllCommentsByBookId(long bookId) {
        return em.createNativeQuery("""
                        SELECT id, name 
                        FROM comments 
                        WHERE book_id = :id
                        """, Comment.class)
                .setParameter("id", bookId)
                .getResultList();
    }

    @Override
    public Optional<Comment> getById(long id) {
        return Optional.ofNullable(em.find(Comment.class, id));
    }

    @Override
    public void addComment(String text, Book book) {
        book.addComment(new Comment(text));
        em.merge(book);
    }

    @Override
    public void deleteComment(long id) {
        em.createQuery("delete from Comment c where c.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public void updateComment(long id, String text) {
        em.createQuery("""
                        update Comment as c
                        set c.name = :text
                        where c.id = :id
                        """)
                .setParameter("id", id)
                .setParameter("text", text)
                .executeUpdate();
    }
}
