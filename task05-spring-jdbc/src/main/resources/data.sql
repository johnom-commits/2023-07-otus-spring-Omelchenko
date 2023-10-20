INSERT INTO authors (id, name) VALUES (1, 'Высоцкий Владимир Семенович');
INSERT INTO authors (id, name) VALUES (2, 'Макаренко Антон Семенович');

INSERT INTO genres (id, name) VALUES (1, 'поэзия');
INSERT INTO genres (id, name) VALUES (2, 'роман');

INSERT INTO books (title, authors_id, genres_id) VALUES ('Нерв', 1, 1);
INSERT INTO books (title, authors_id, genres_id) VALUES ('Педагогическая поэма', 2, 2);
INSERT INTO books (title, authors_id, genres_id) VALUES ('Флаги на башнях', 2, 2);

