package ru.otus.homework.repository;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.homework.domain.Book;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.repository.EntityGraph.EntityGraphType.FETCH;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Book create(Book book) {
        if (book.getId() == 0) {
            em.persist(book);
            return book;
        }
        return em.merge(book);
    }

    @Override
    public Optional<Book> getById(long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Override
    public List<Book> getAll() {
        EntityGraph<?> entityGraph = em.getEntityGraph("book-author-genre-entity-graph");
        TypedQuery<Book> query = em.createQuery("""
                    select distinct b from Book as b 
                    left join fetch b.comments""",
                Book.class);
        query.setHint(FETCH.getKey(), entityGraph);
        return query.getResultList();
    }

    @Override
    public boolean isExist(long id) {
        Book book = em.find(Book.class, id);
        return book != null;
    }

    @Override
    public void update(Book book) {
        em.createQuery("""
                        update Book as b
                        set b.title = :title, b.author = :author, b.genre = :genre
                        where b.id = :id
                        """)
                .setParameter("id", book.getId())
                .setParameter("title", book.getTitle())
                .setParameter("author", book.getAuthor())
                .setParameter("genre", book.getGenre())
                .executeUpdate();
    }

    @Override
    public void delete(long id) {
        em.createQuery("delete from Book b where b.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
