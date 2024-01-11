MERGE INTO authors AS a
USING (SELECT 'Vysotskiy' AS name) AS n
ON a.name = n.name
WHEN NOT matched THEN
INSERT (name)
VALUES ('Vysotskiy');

MERGE INTO authors AS a
USING (SELECT 'Makarenko' AS name) AS n
ON a.name = n.name
WHEN NOT matched THEN
INSERT (name)
VALUES ('Makarenko');

MERGE INTO genres AS g
USING (SELECT 'poetry' AS name) AS n
ON g.name = n.name
WHEN NOT matched THEN
INSERT (name)
VALUES ('poetry');

MERGE INTO genres AS g
USING (SELECT 'novel' AS name) AS n
ON g.name = n.name
WHEN NOT matched THEN
INSERT (name)
VALUES ('novel');

MERGE INTO books b
USING (SELECT 'pedagogical poem' AS title, 2 AS author_id, 2 AS genre_id) AS n
ON b.title = n.title AND b.author_id = n.author_id
WHEN NOT matched THEN
INSERT (title, author_id, genre_id)
VALUES ('pedagogical poem', 2, 2);

MERGE INTO books b
USING (SELECT 'Nerv' AS title, 1 AS author_id, 1 AS genre_id) AS n
ON b.title = n.title AND b.author_id = n.author_id
WHEN NOT matched THEN
INSERT (title, author_id, genre_id)
VALUES ('Nerv', 1, 1);

MERGE INTO books b
USING (SELECT 'Nerv' AS title, 1 AS author_id, 1 AS genre_id) AS n
ON b.title = n.title AND b.author_id = n.author_id
WHEN NOT matched THEN
INSERT (title, author_id, genre_id)
VALUES ('Nerv', 1, 1);


MERGE INTO comments AS c
USING (SELECT 1 AS book_id, 'comment 1' AS name) AS n
ON c.name = n.name AND c.book_id = n.book_id
WHEN NOT matched THEN
INSERT (book_id, name)
VALUES (1, 'comment 1');

MERGE INTO comments AS c
USING (SELECT 1 AS book_id, 'comment 1.2' AS name) AS n
ON c.name = n.name AND c.book_id = n.book_id
WHEN NOT matched THEN
INSERT (book_id, name)
VALUES (1, 'comment 1.2');

MERGE INTO comments AS c
USING (SELECT 2 AS book_id, 'comment 2' AS name) AS n
ON c.name = n.name AND c.book_id = n.book_id
WHEN NOT matched THEN
INSERT (book_id, name)
VALUES (2, 'comment 2');
