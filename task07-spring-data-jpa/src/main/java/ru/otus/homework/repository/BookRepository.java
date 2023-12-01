package ru.otus.homework.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @EntityGraph(value = "book-author-genre-entity-graph", type = EntityGraph.EntityGraphType.FETCH)
    @Override
    List<Book> findAll();

    @EntityGraph(value = "book-author-genre-entity-graph", type = EntityGraph.EntityGraphType.FETCH)
    @Override
    Optional<Book> findById(Long id);

}
