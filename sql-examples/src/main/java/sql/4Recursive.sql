CREATE TABLE first_table (
    id        BIGSERIAL PRIMARY KEY,
    data      VARCHAR NOT NULL,
    parent_id BIGINT
);

INSERT INTO first_table(data, parent_id)
VALUES ('root', NULL),
    ('child11', 1),
    ('child12', 1),
    ('child21', 2),
    ('child22', 2),
    ('child31', 3);

SELECT *
FROM first_table;

-- From root to leaves
WITH RECURSIVE children AS (
    -- non-recursive term
    SELECT ft.id AS id, ft.data AS data, ft.parent_id AS parent_id, 1::INT AS depth, ft.id::TEXT AS path
    FROM first_table ft
    WHERE ft.data = 'root'
    UNION
    -- recursive term
    SELECT ft.id, ft.data, ft.parent_id, ch.depth + 1 AS depth, (ch.path || '->' || ft.id::TEXT)
    FROM first_table ft
             INNER JOIN children ch ON ch.id = ft.parent_id
)
SELECT *
FROM children;

-- From leaf to root
WITH RECURSIVE children AS (
    -- non-recursive term
    SELECT ft.id AS id, ft.data AS data, ft.parent_id AS parent_id, 1::INT AS depth, ft.id::TEXT AS path
    FROM first_table ft
    WHERE ft.data = 'child31'
    UNION
    -- recursive term
    SELECT ft.id, ft.data, ft.parent_id, ch.depth + 1 AS depth, (ch.path || '->' || ft.id::TEXT)
    FROM first_table ft
             INNER JOIN children ch ON ch.parent_id = ft.id
)
SELECT *
FROM children;

DROP TABLE first_table;