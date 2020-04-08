CREATE TABLE first_table (
    id   BIGSERIAL PRIMARY KEY,
    data VARCHAR NOT NULL
);

CREATE TABLE second_table (
    id             BIGSERIAL PRIMARY KEY,
    first_table_id BIGINT,
    data           VARCHAR NOT NULL,
    CONSTRAINT second_table_first_table_id_first_table_id
        FOREIGN KEY (first_table_id)
            REFERENCES first_table(id)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);

INSERT INTO first_table (data)
VALUES ('data1'),
    ('data2'),
    ('data3');

INSERT INTO second_table (first_table_id, data)
VALUES (1, 'data1'),
    (NULL, 'data2'),
    (3, 'data3');

SELECT *
FROM first_table;
SELECT *
FROM second_table;

SELECT *
FROM first_table ft
         FULL JOIN second_table st ON ft.id = st.first_table_id;
SELECT *
FROM first_table ft
         INNER JOIN second_table st ON ft.id = st.first_table_id;
SELECT *
FROM first_table ft
         LEFT JOIN second_table st ON ft.id = st.first_table_id;
SELECT *
FROM first_table ft
         RIGHT JOIN second_table st ON ft.id = st.first_table_id;

SELECT ft.data
FROM first_table ft
UNION ALL
SELECT st.data
FROM second_table st;
SELECT ft.data
FROM first_table ft
UNION
SELECT st.data
FROM second_table st;
SELECT ft.data
FROM first_table ft
UNION
DISTINCT
SELECT st.data
FROM second_table st;

DROP TABLE second_table;
DROP TABLE first_table;