CREATE TABLE first_table (
    id   BIGSERIAL PRIMARY KEY,
    data VARCHAR NOT NULL
);

CREATE TABLE second_table (
    id             BIGSERIAL PRIMARY KEY,
    first_table_id BIGINT  NOT NULL,
    data           VARCHAR NOT NULL,
    CONSTRAINT second_table_first_table_id_first_table_id
        --[current_table_name][column][referenced_table_name][column]
        FOREIGN KEY (first_table_id)
            REFERENCES first_table(id)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);

CREATE TABLE third_table (
    id             BIGSERIAL PRIMARY KEY,
    first_table_id BIGINT  NOT NULL,
    data           VARCHAR NOT NULL
);
ALTER TABLE third_table
    ADD CONSTRAINT third_table_first_table_id_first_table_id
        --[current_table_name][column][referenced_table_name][column]
        FOREIGN KEY (first_table_id)
            REFERENCES first_table(id)
            ON DELETE CASCADE
            ON UPDATE CASCADE;

DROP TABLE third_table;
DROP TABLE second_table;
DROP TABLE first_table;