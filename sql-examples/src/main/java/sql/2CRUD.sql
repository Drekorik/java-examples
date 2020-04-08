CREATE TABLE first_table (
    id           BIGSERIAL PRIMARY KEY,
    data         VARCHAR NOT NULL,
    another_data INT
);

INSERT INTO first_table(data, another_data)
VALUES ('QWERTY', 1),
    ('QWERTY2', NULL);

SELECT *
FROM first_table;

UPDATE first_table
SET another_data = 0
WHERE another_data ISNULL;

SELECT *
FROM first_table;

DROP TABLE first_table;