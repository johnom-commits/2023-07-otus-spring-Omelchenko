DROP TABLE IF EXISTS authors;
CREATE TABLE authors
(
    id bigserial PRIMARY KEY,
    name varchar(255) NOT NULL
);

DROP TABLE IF EXISTS genres;
CREATE TABLE genres
(
    id bigserial PRIMARY KEY,
    name varchar(255) NOT NULL UNIQUE
);

DROP TABLE IF EXISTS books;
CREATE TABLE books
(
    id bigserial PRIMARY KEY,
    title varchar(255) NOT NULL,
    author_id bigint REFERENCES authors (id) ON DELETE RESTRICT,
    genre_id bigint REFERENCES genres (id) ON DELETE RESTRICT
);

DROP TABLE IF EXISTS comments;
CREATE TABLE comments
(
    id bigserial PRIMARY KEY,
    book_id bigint REFERENCES books (id) ON DELETE CASCADE,
    name varchar(2000)
);