DROP TABLE IF EXISTS authors;
CREATE TABLE authors
(
    id bigint PRIMARY KEY,
    name varchar(255) NOT NULL
);

DROP TABLE IF EXISTS genres;
CREATE TABLE genres
(
    id bigint PRIMARY KEY,
    name varchar(255) NOT NULL UNIQUE
);

DROP TABLE IF EXISTS books;
CREATE TABLE books
(
    id bigint auto_increment PRIMARY KEY,
    title varchar(255) NOT NULL,
    authors_id bigint REFERENCES authors (id) ON DELETE RESTRICT,
    genres_id bigint REFERENCES genres (id) ON DELETE RESTRICT
);