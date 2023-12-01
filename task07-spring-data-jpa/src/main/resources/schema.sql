CREATE TABLE IF NOT EXISTS authors
(
    id bigserial PRIMARY KEY,
    name varchar(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS genres
(
    id bigserial PRIMARY KEY,
    name varchar(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS books
(
    id bigserial PRIMARY KEY,
    title varchar(255) NOT NULL,
    author_id bigint REFERENCES authors (id) ON DELETE RESTRICT,
    genre_id bigint REFERENCES genres (id) ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS comments
(
    id bigserial PRIMARY KEY,
    book_id bigint REFERENCES books (id) ON DELETE CASCADE,
    name varchar(2000)
);